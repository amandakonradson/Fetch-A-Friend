<template>
  <div id="body">
    <form  id="create-play-date" v-on:submit.prevent="savePlayDate">
        <h1 id="title">Create Your Play Date:</h1>
      <label  for="host-pet-id"
        >Which Of Your Pets Is Looking For A Playmate:     </label>
      <select class ='form' v-model="playDate.hostPetId" >
        <option v-for="dog in dogsByUserId" v-bind:key="dog.petId" v-bind:hostPetId='hostPetId' v-bind:value='dog.petId' required selected> {{ dog.name }} </option>
      </select>
      <br />
      <label  for="location">Location Address:     </label>
      <input class = 'form'  type="text" v-model="location.description" required />
      <br />
      <label  for="location">Location Zip Code:     </label>
      <input class = 'form'
        type="text"
         pattern="(?=.*\d).{5,}"
        v-model="location.zipCode"
        required
      />
      <br />
      <label for="meetingDate">Meeting Date: </label>
      <input class = 'form' type="date" v-model="playDate.meetingDate" v-bind:min="this.currentDate" required /> 
       <br />
      <label   for="start-time">Start Time:     </label>
      <input class = 'form' type="time" min="06:00" max="20:00" step="600" v-model="playDate.startTime" required />
      <br />
      <label  for="duration">Duration In Minutes:     </label>
      <input class = 'form'
        type="number"
        min="30"
        max="120"
        step="15"
        v-model="playDate.duration"
        required
      />
      <br />
      <label id="temperament" for="description">Preferred Playmate Temperament(s):     </label>
      <br>
      <input class = 'form' type=checkbox
        value="high-energy"
        id="description"
        v-model="playDate.description"
        required /> <label class="checkbox" for="high-energy">High Energy: These dogs never tire and can chase a ball for hours!</label>
      <br>
      <input class = 'form' type=checkbox
        value="timid"
        id="description"
        v-model="playDate.description"
        required /> <label class="checkbox" for="timid">Timid: These pups take a little longer to warm up to new company but once they feel comfortable they make great playmates!</label>
      <br>
      <input class = 'form' type=checkbox
        value="friendly"
        id="description"
        v-model="playDate.description"
        required /> <label class="checkbox" for="friendly">Friendly: These dogs love everyone they encounter and have never met a stranger!</label>
      <br>
      <input class = 'form' type=checkbox
        value="protective"
        id="description"
        v-model="playDate.description"
        required /> <label class="checkbox" for="protective">Protective: These are the dogs devoted to their humans and may prefer to socialize from a distance!</label>
      <br> 
      <input class = 'form' type=checkbox
        value="dominant"
        id="description"
        v-model="playDate.description"
        required /> <label class="checkbox" for="dominant">Dominant: These are the leaders of the pack and like to be in charge!</label>
      <br>  
      <input class = 'form' type=checkbox
        value="curious"
        id="description"
        v-model="playDate.description"
        required /> <label class="checkbox" for="curious">Curious: These dogs will follow their noses wherever they lead!</label>
      <br>  
      <input class = 'form' type=checkbox
        value="laid-back"
        id="description"
        v-model="playDate.description"
        required /> <label class="checkbox" for="laid-back">Laid Back: These pups are content to lounge around and soak in the sun!</label>
      <br>  
      <input class = 'form' type=checkbox
        value="submissive"
        id="description"
        v-model="playDate.description"
        required /> <label class="checkbox" for="submissive">Submissive: These dogs are happy to let others take the lead. They're just along for the ride.</label>
      <br />
        <input class = 'form' type=checkbox
        value="nopreference"
        id="description"
        v-model="playDate.description"
        required /> <label class="checkbox" for="nopreference">No Preference</label>
      <br />

      <label  for="mate-size">Preferred Playmate Size:     </label>
      <select class = 'form'
        name="mate-size"
        id="mate-size"
        v-model="playDate.mateSize"
        required>
        <option value="extraSmall" selected>
          Extra Small (for example: Chihuahua, Maltese, Yorkshire Terrier)
        </option>
        <option value="small" selected>
          Small (for example: Beagle, French Bulldog, Boston Terrier)
        </option>
        <option value="medium" selected>
          Medium (for example: Border Collie, Corgi, Spaniel)
        </option>
        <option value="large" selected>
          Large (for example: Lab, Golden Retriever, Australian Shepherd)
        </option>
        <option value="extraLarge" selected>
          Extra Large (for example: Great Dane, St. Bernard, Newfoundland)
        </option>
        <option value="noPreference" selected>No Preference</option>
      </select>
      <br />
      <br />
      <button class="button-submit">Submit</button>
      <button class="button-cancel">Cancel</button>
    </form>
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";
import petService from "@/services/PetService";

export default {
  data() {
  
  return {

      currentDate:"",
     
      dogsByUserId: [],

      id: -1,

      playDate: {
        hostPetId: "",
        meetingDate: "",
        startTime: "",
        duration: "",
        description: [],
        mateSize: "",
      },
      location: {
        zipCode: "",
        description: "",
      },
    };
  },
  methods: {
    savePlayDate() {
      const newPlayDate = {
        hostPetId: this.playDate.hostPetId,
        meetingDate: this.playDate.meetingDate,
        startTime: this.playDate.startTime,
        duration: this.playDate.duration,
        description: this.playDate.description,
        mateSize: this.playDate.mateSize,
      }
      

      const newLocation = {
        zipCode: this.location.zipCode,
        description: this.location.description
      }
      playDateService.createPlayDate(newPlayDate, newLocation)
      .then((response) => {
        if (response.status === 201) {
          window.alert("Play Date Added!");   //change to landing page once established
        }
      })
      .catch((err) => {
        if (err.response) {
          window.alert("Bad Request")
        }
        else if (err.request) {
          window.alert("Could not reach service");
        }
      });

    }
  },
  created() {
    //Current Date from Server
    playDateService.getCurrentDate().then((response)=>{
      this.currentDate=response.data;

    })
    

    //Logged in user's ID
    this.id = this.$store.state.user.id;
    console.log(this.id)
    
    petService.listUserPets(this.id)
    .then((dogData) => {
      this.dogsByUserId = dogData.data;
    })
    .catch((err) => {
      console.error(err + " nothing returned");
    });
    
  }
};
</script>

<style scoped>
#body{
    background-color: rgb(27, 76, 119);
    color: white;
    height: 100vh;
}
#create-play-date{
    border: white 5px dotted;
    padding: 20px;
    font-size: 30px;
}
.form{
    margin-bottom: 20px;
}
#title{
    font-size: 36px;
    font-weight: bold;
}
.checkbox{
  font-size: 18px;
}
#temperament{
  margin-bottom: 10px;
}
</style>