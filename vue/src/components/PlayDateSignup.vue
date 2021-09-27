<template>
  <div id="main">
    <div id="play-date-details">
      <h1>Play Date Details:</h1>
      <h3>Location:</h3>
      <div class="info" id="address">
        {{ playDate.locationStreetAddress }} <br />
        {{ playDate.locationCity }}, {{ playDate.locationZipcode }} <br />
      </div>
      <h3>Date</h3>
      <div class="info" id="date">
        {{ playDate.meetingDate }} Beginning at: {{ playDate.startTime }}
        <br />Anticipated Length: {{ playDate.duration }} minutes <br />
      </div>
      <h3>Host Information:</h3>
      <div class="info" id="host">
        Name: {{ playDate.name }}<br />
        Breed: {{ playDate.breed }}<br />
        Size: {{ playDate.size }} <br />Temperament: {{ playDate.temperament }}
        <br />Spayed/Neutered: {{ playDate.spayedNeutered }} <br /><br />
        {{ playDate.name }} plays well with dogs who are:
        <span
          v-for="description in playDate.mateDescription"
          v-bind:key="description.index"
          >{{ description }}</span
        >
        and are {{ playDate.mateSize }} in size.
      </div>
      <br />
      <h3>Request to Join:</h3>
      <label>
        Which of Your Pets Will Be Attending:
      </label>
      <select class="form" v-model="playDate.matePetId">
        <option
          v-for="dog in dogsByUserId"
          v-bind:key="dog.petId"
          v-bind:hostPetId="hostPetId"
          v-bind:value="dog.petId"
          required
          selected>{{dog.name}}</option>
        ></select>
      <br /><br><button class="button-submit">Submit</button>
      <button class="button-cancel">
        <router-link id="cancelBtn" to="/play-date/available/">Cancel</router-link></button>
    </div>

    <!--<div v-for="description in playDate.mateDescription" v-bind:key="description.index">-->
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";
import petService from "@/services/PetService"
export default {
  data() {
    return {
      playDateId: -1,
      playDate: {},
      id: -1,
      dogsByUserId: [],
    };
  },
  created() {
    this.playDateId = this.$route.params.id;
    console.log(this.playDateId);
    playDateService
      .getAvailablePlayDatesByPlayDateId(this.playDateId)

      .then((response) => {
        this.playDate = response.data;
      })
      .catch((error) => {
        if (error.response) {
          window.alert("Missing Play Date");
        } else if (error.request) {
          window.alert("Could not reach service");
        }
      });

    //Logged in user's ID
    this.id = this.$store.state.user.id;
    console.log(this.id);

    petService
      .listUserPets(this.id)
      .then((dogData) => {
        this.dogsByUserId = dogData.data;
      })
      .catch((err) => {
        console.error(err + " nothing returned");
      });
  },
};
</script>

<style scoped>
#main {
  background-color: rgb(2, 59, 109);
  color: white;
  padding-bottom: 50px;
}
#play-date-details {
  margin-left: auto;
  margin-right: auto;
  width: 600px;
  border: 5px white dotted;
  padding-bottom: 20px;
}
h1 {
  font-size: 48px;
  margin-left: 20px;
}
h3 {
  font-size: 32px;
  margin-left: 20px;
}
.info {
  font-size: 24px;
  margin-left: 20px;
}
label{
    font-size: 24px;
  margin-left: 20px;
}
button{
    height: 40px;
  font-size: 18px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 5px white solid;
  margin-left:20px;
}
#cancelBtn{
   height: 30px;
  font-size: 18px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 5px white solid;
  text-decoration: none;
}
option{
    font-size: 18px;
    
}
</style>