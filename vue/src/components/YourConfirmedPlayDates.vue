<template>
  <div id="main">
      <h1>Upcoming Playdates: </h1>
      <div id="upcoming-tile" v-for="playDate in playDates"
      v-bind:key="playDate.index"
      v-bind:playDate="playDate"
      v-bind:value="playDate">
       <h3>Playdate Details:</h3>
        <b>Date: </b> {{playDate.meetingDate}} <br>
        <b>Start Time: </b> {{playDate.startTime}} <br>
        <b>Play Time:</b>  {{playDate.duration}} minutes <br>
        <b>Location: </b> {{playDate.locationStreetAddress}}<br>
        {{playDate.locationCity}}, {{playDate.locationZipcode}}
        <br>
      </div>
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";
export default {
    data() {
        return{
            id: -1,
            playDates: []
        }; 
    },
    created() {
        this.id = this.$store.state.user.id; 

        playDateService.getAllConfirmedPlayDates(this.id)
        .then((response) => {
            this.playDates = response.data;
        })
        .catch((error) => {
            if (error.response) {
                window.alert("Bad Dog!");
            } else if (error.request) {
                window.alert("Could not reach service");
            }
        });
    }

}
</script>

<style scoped>
#upcoming-tile {
    border: 5px white dotted;
  font-size: 20px;
  padding-left:20px;
  padding-bottom: 20px;
   line-height: 30px;
   background-color: rgb(63, 182, 212);
     margin: 5px;
     width: 400px;
}
#main{
  display: flex;
  flex-direction:column;
  margin-left: 20px;
}
h1{
  font-size:36px;
}
</style>