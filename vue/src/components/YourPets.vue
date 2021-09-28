<template>
  <div id="main">
      <h1>Your Pets: </h1>
      <div id="pet-tile" v-for="pet in pets" 
      v-bind:key="pet.index"
      v-bind:pet="pet"
      v-bind:value="pet"
       >
       <h3>Pet DeTAILS: </h3> 
        Name: {{pet.name}}        
      </div>

  </div>
</template>

<script>
import petService from "@/services/PetService";
export default {
    data() {
        return {
            id: -1, 
            pets: []
        }
    }, 
    created() {
        this.id = this.$store.state.user.id;

        petService.listUserPets(this.id).then((response)=>{
            this.pets = response.data;
        })
        .catch((err) => {
        console.error(err + " nothing returned");
      });
    }

}
</script>

<style scoped>
#pet-tile {
  border: 3px white dotted;
}

</style>