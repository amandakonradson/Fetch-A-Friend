<template>
  <div id="main">
<h1>Add Your Pet</h1>
<form v-on:submit.prevent="saveDog">
<label for="name">Enter your pet's name: </label>  
<input type="text" id="name" v-model="dog.name" /> 
<br /> <br />

<label for="breed">Enter your pet's breed: </label> 
<input type="text" id="breed" v-model="dog.breed" /> 
<br /> <br />

<label for="birthYear">Enter your pet's birth year: </label>
<input type="number" min="2000" max=current step="1" id="birthYear" v-model="dog.birthYear" /> 
<br /> <br />

<label for="gender">Enter your pet's gender: </label>
<select id="dogGender" v-model="dog.gender"> 
          <option value="Male" selected>Male</option>
          <option value="Female">Female</option>
        </select> 
        <br /> <br />

<label for="size">Select your pet's size: </label>
<select id="dogSize" v-model="dog.size"> 
          <option value="extraSmall" selected>Extra Small (for example: Chihuahua, Maltese, Yorkshire Terrier)</option>
          <option value="small" selected>Small (for example: Beagle, French Bulldog, Boston Terrier)</option>
          <option value="medium" selected>Medium (for example: Border Collie, Corgi, Spaniel)</option>
          <option value="large" selected>Large (for example: Lab, Golden Retriever, Australian Shepherd)</option>
          <option value="extraLarge" selected>Extra Large (for example: Great Dane, St. Bernard, Newfoundland)</option>

        </select> 
        <br /> <br />

<label for="spayedNeutered">Is your dog spayed or neutered? </label>
<select id="spayedNeuteredSelection" v-model="dog.spayedNeutered"> 
          <option value="yes" selected>Yes </option>
          <option value="no" selected>No </option>

        </select> 
        <br /> <br />        

<label for="temperament">What is your dog's temperament? (select the most applicable option): </label>
<select name="temperaments" id="temperaments" v-model="dog.temperament"> 
    <option value="highEnergy">High Energy: These dogs never tire and can chase a ball for hours! </option>
    <option value="timid">Timid: These pups take a little longer to warm up to new company but once they feel comfortable they make great playmates! </option>
    <option value="friendly">Friendly: These dogs love everyone they encounter and have never met a stranger!</option>
    <option value="protective">Protective: These are the dogs devoted to their humans and may prefer to socialize from a distance! </option>
    <option value="dominant">Dominant: These are the leaders of the pack and like to be in charge!</option>
    <option value="curious">Curious: These dogs will follow their noses wherever they lead! </option>
    <option value="laidBack">Laid Back: These pups are content to lounge around and soak in the sun! </option>
    <option value="submissive">Submissive: These dogs are happy to take others take the lead. They're just along for the ride. </option>
</select>
<br> <br>
<input type="file" @change>
<button @click ="onUpload">Upload</button>
<input id="vaccination" type="checkbox" required/>
<label for ="vaccination">By checking this box, you certify that your pet is up to date on all legally required vaccinations</label>

<br> <br>
<button class="button-submit">Submit</button>
<button class="button-cancel">Cancel</button>
</form>
  </div>
</template>

<script>
import petService from "@/services/PetService";

export default {
    data() {
        return {
            selectedFile: null,
            dog: {
                name: "",
                breed: "",
                birthYear: "",
                gender: "",
                size: "",
                spayedNeutered: "",
                temperament: "",
            },
        }
    },
    methods: {
        onFileSelected(event) {
            this.selectedFile= event.target.files[0]
        },
        onUpload() {
            const fd= new FormData();
            fd.append('image', this.selectedFile, this.selectedFile.name)
            axios.post('', fd
            .then((respsone) => {
                if(response.status === 201) {
                    this.$router.push("/") //need to update once landing page is created
                }
            }).catch((error) => {
                if(error.response) {
                    window.alert('Bad Request');
                }
                else if (error.request) {
                    window.alert('Could not reach service');
                }
            })
            }
        },
        saveDog() {
            const newDog = {
                name: this.dog.name,
                breed: this.dog.breed,
                birthYear: this.dog.birthYear,
                gender: this.dog.gender,
                size: this.dog.size,
                spayedNeutered: this.dog.spayedNeutered,
                temperament: this.dog.temperament,
            }
            petService.createPet(newDog)
            .then((response) => {
                if(response.status === 201) {
                    this.$router.push("/") //need to update once landing page is created
                }
            }).catch((error) => {
                if(error.response) {
                    window.alert('Bad Request');
                }
                else if (error.request) {
                    window.alert('Could not reach service');
                }
            })
        }
    }

}
</script>

<style scoped>
</style>