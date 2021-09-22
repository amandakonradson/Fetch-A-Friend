<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert-alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Login Failed: Invalid username and password!</div>
      <div
        class="alert-alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <div id="log-in">
      <label for="username" class="sr-only">Username   </label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      /><br>
      <label for="password" class="sr-only">Password   </label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      /><br>
      <router-link id="accountBtn" :to="{ name: 'register' }">Need an account?</router-link>
      <button id= "button" type="submit">Sign in</button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.form-control{
  margin-top: 10px;
  margin-bottom: 10Px;
}
button{
  margin-left: 10px;
}
#log-in{
  font-size:20px;
  
}
.alert-alert-success{
  font-size: 20px;
}
.alert-alert-danger{
  font-size: 20px;
}
#accountBtn {
    color: white;
    text-decoration: none; color: inherit;
}
#login {
    background-color: rgb(27, 76, 119);
    color: white;
    height: 100vh;
}
</style>
