<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form"> 
      <label for="username" class="sr-only">Username </label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      /><br />
      <label for="email" class="sr-only">Email Address </label>
      <input
        type="email"
        id="email"
        class="form-control"
        placeholder="Email"
        v-model="user.email"
        required
        autofocus
      /><br />
      
      <label for="password" class="sr-only">Password </label>
      <input
        type="password"
        id="password"
        class="password"
        placeholder="Password"
        v-model="user.password"
        required
        pattern="(?=.*\d)(?=.*[A-Z]).{8,}"
      />
      <input
        type="password"
        id="confirmPassword"
        class="password"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <p id="password-guide">
        *Must contain at least 8 characters, 1 number, and 1 uppercase
        letter.
      </p><br /><br>
      <router-link id="button" class="sr-only" :to="{ name: 'login' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
      Create Account
      </button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        email: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "An account with this email address already exists. Please log in.",         //"There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "An account with that username already exists.";        //"Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
.form-control{
  margin-top: 10px;
  margin-bottom: 10px;
}
form{
  font-size: 20px;
}
button{
  margin-left: 20px;
}
#password-guide{
  margin-bottom: 10px;
}
.password{
  margin-top:10px;
  
}
#button {
    color: white;
    text-decoration: none; color: inherit;
}
#register {
    background-color:  rgb(2, 59, 109);
    color: white;
    height: 100vh;
}
</style>
