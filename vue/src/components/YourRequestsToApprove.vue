<template>
  <div id="main">
      <h1>Your Requests to Approve:</h1>
      <div id="request-tile" v-for="request in requests" 
      v-bind:key="request.index"
      v-bind:request="request"
      v-bind:value="request"
       >
      <form id="info" v-on:submit.prevent="approvePlayDate(request.mateId, request.playDateId)">
        <h3>Playdate Details:</h3>
        <b>Date: </b> {{request.playdate.meetingDate}} <br>
        <b>Start Time:</b> {{request.playdate.startTime}} <br>
        <b>Play Time: </b>{{request.playdate.duration}} minutes <br>
        <b>Location: </b>{{request.playdate.locationStreetAddress}}
        <br>
        <h3>Potential Playmate:</h3>
        <b>Name: </b> {{request.mateName}} <br>
        <b>Breed: </b>{{request.mateBreed}} <br>
        <b>Size:</b> {{request.mateSize}} <br>
        <b>Year of Birth:</b> {{request.mateBirthYear}} <br>
        <b>Spayed/Neutered?</b> {{request.mateSpayedNeutered}} <br>
        <b>Temperament: </b>{{request.mateTemperament}} <br>

      <button class="button-submit">Pick this Pup!</button>
     </form>
      </div>

  </div>

</template>
<script>
import playDateService from "@/services/PlayDateService";
export default {
    data(){
        return {
            id: -1,
            requests: [],
           
        }
    },
    created(){
        this.id = this.$store.state.user.id;

        playDateService.getMyRequestsToApprove(this.id).then((response)=>{
            this.requests = response.data;
        })
        .catch((err) => {
        console.error(err + " nothing returned");
      });

    },
    methods: {
      approvePlayDate(idMate, idPlayDate) {
        
      
        
        playDateService.updateRequest(idPlayDate, idMate)
        .then((response) => {
          if(response.status >= 200 && response.status < 300) {
            window.alert("Your playdate is conFURRmed!")
            this.$router.go();
          }
        })
        .catch((error) => {
          if (error.response) {
            window.alert("Bad Dog!");
          } else if (error.request) {
            window.alert("Could not reach service");
          }
        });
      },
    }

}
</script>

<style scoped>
#request-tile {
  border: 5px white dotted;
  font-size: 20px;
  padding-left:20px;
  padding-bottom: 20px;
  background-color:  rgb(25, 149, 180);
  margin: 5px;
  width: 400px;
}
h3{
  font-size: 28px;
}
#main{
  display: flex;
  flex-direction:column;
  margin-left: 20px;
}
#info{
  line-height: 30px;
}
button {
  height: 30px;
  font-size: 18px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 5px white solid;
  margin-top:20px;
  font-family: 'Alegreya Sans', sans-serif;
}
h1{
  font-size:36px;
}
</style>