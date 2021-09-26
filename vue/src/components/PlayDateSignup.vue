<template>
  <div id="main">
{{ playDate.locationStreetAddress }} {{ playDate.locationCity }} {{ playDate.locationZipcode }} {{ playDate.meetingDate }} {{ playDate.startTime }} {{ playDate.duration }}
{{ playDate.mateDescription }} {{ playDate.mateSize }} {{ playDate.name }}  {{ playDate.breed }} {{ playDate.size }} {{ playDate.temperament }} {{ playDate.spayedNeutered }}


<div v-for="description in playDate.mateDescription" v-bind:key="description.index">
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