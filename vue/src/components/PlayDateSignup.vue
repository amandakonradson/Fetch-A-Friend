<template>
  <div id="main">
      <div>
          <h1>Play Date Details: </h1>
          <h3>Location: </h3>
          {{ playDate.locationStreetAddress }} <br> {{ playDate.locationCity }}, {{ playDate.locationZipcode }} <br> 
          <h3>Date</h3>
          {{ playDate.meetingDate }} Beginning at:  {{ playDate.startTime }} Anticipated Length: {{ playDate.duration }} minutes <br>
          <h3>Host Information: </h3> Name: {{ playDate.name }} Breed: {{ playDate.breed }} Size: {{ playDate.size }} Temperament: {{ playDate.temperament }} Spayed/Neutered: {{ playDate.spayedNeutered }} <br>
          {{ playDate.name }} plays well with dogs who are: <span v-for="description in playDate.mateDescription" v-bind:key="description.index">{{ playDate.mateDescription }}</span>
      </div>
 {{ playDate.mateSize }}     


<!--<div v-for="description in playDate.mateDescription" v-bind:key="description.index">-->
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";
export default {
    data(){
        return{
            playDateId: -1,
            playDate:{}
        }
    },
    created(){
    this.playDateId = this.$route.params.id;
    console.log(this.playDateId);
    playDateService.getAvailablePlayDatesByPlayDateId(this.playDateId)
  
        .then((response) => {
            this.playDate = response.data;
        })
        .catch((error) => {
            if (error.response) {
                window.alert("Missing Play Date");
            } else if (error.request) {
                window.alert("Could not reach service");
            }
})

}
}

</script>

<style scoped>
#main {
  background-color: rgb(2, 59, 109);
  color: white;
  padding-bottom: 50px;
}
</style>