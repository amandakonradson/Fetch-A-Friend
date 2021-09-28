<template>
  <div id="main">
      <h1>Your Requests to Approve:</h1>
      <div id="request-tile" v-for="request in requests" 
      v-bind:key="request.index"
      v-bind:request="request"
      v-bind:value="request"
       >
      <form v-on:submit.prevent="approvePlayDate(request.mateId, request.playDateId)">
        <h3>Playdate Details:</h3>
        Date:  {{request.playdate.meetingDate}} <br>
        Start Time: {{request.playdate.startTime}} <br>
        Play Time: {{request.playdate.duration}} minutes <br>
        Location: {{request.playdate.locationStreetAddress}}
        <br>
        <h3>Potential Playmate:</h3>
        Name: {{request.mateName}} <br>
        Breed: {{request.mateBreed}} <br>
        Size: {{request.mateSize}} <br>
        Year of Birth: {{request.mateBirthYear}} <br>
        Spayed/Neutered? {{request.mateSpayedNeutered}} <br>
        Temperament: {{request.mateTemperament}} <br>

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
  border: 3px white dotted;
}

</style>