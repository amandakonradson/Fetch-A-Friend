<template>
  <div>
      <h1>Upcoming Play Dates: </h1>
      <div ID="upcoming-tile" v-for="playDate in playDates"
      v-bind:key="playDate.index"
      v-bind:playDate="playDate"
      v-bind:value="playDate">
       <h3>Playdate Details:</h3>
        Date:  {{playDate.meetingDate}} <br>
        Start Time: {{playDate.startTime}} <br>
        Play Time: {{playDate.duration}} minutes <br>
        Location: {{playDate.locationStreetAddress}}
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
    border: 3px white dotted;
}

</style>