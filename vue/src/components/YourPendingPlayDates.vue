<template>
  <div id="main">
      <h1>Play Dates Awaiting Host Approval: </h1>
      <div id="pending-tile" v-for="playDate in playDates" 
      v-bind:key="playDate.index"
      v-bind:playDate="playDate"
      v-bind:value="playDate"
      >
          <h3>Playdate Details:</h3>
        Date:  {{playDate.meetingDate}} <br>
        Start Time: {{playDate.startTime}} <br>
        Play Time: {{playDate.duration}} minutes <br>
        Location: {{playDate.locationStreetAddress}}
        <br>
      </div>
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";
export default {
    data() {
        return {
            id: -1,
            playDates: []
        }
    },
    created() {
        this.id = this.$store.state.user.id;

        playDateService.getPendingPlayDates(this.id).then((response)=>{
            this.playDates = response.data;
        })
        .catch((err) => {
        console.error(err + " nothing returned");
      });

    },
    }


</script>

<style scoped>
#pending-tile {
  border: 3px white dotted;
}

</style>