<template>
  <div id="body">
    <form  id="create-play-date" v-on:submit.prevent="">
        <h1 id="title">Create Your Play Date:</h1>
      <label  for="host-pet-id"
        >Which Of Your Pets Is Looking For A Playmate:     </label
      >
      <select class = 'form' id="host-pet-id" v-model="playDate.hostPetId" >
        <option value="Male" selected></option>
      </select>
      <br />
      <label  for="location">Location Description:     </label>
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
      <label for="date">Date:     </label>
      <input class = 'form'  type="date" v-model="playDate.date" required />
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
      <label  for="description">Perferred Playmate Temperament:     </label>
      <select class = 'form'
        name="description"
        id="description"
        v-model="playDate.description"
        required
      >
        <option value="highEnergy">
          High Energy: These dogs never tire and can chase a ball for hours!
        </option>
        <option value="timid">
          Timid: These pups take a little longer to warm up to new company but
          once they feel comfortable they make great playmates!
        </option>
        <option value="friendly">
          Friendly: These dogs love everyone they encounter and have never met a
          stranger!
        </option>
        <option value="protective">
          Protective: These are the dogs devoted to their humans and may prefer
          to socialize from a distance!
        </option>
        <option value="dominant">
          Dominant: These are the leaders of the pack and like to be in charge!
        </option>
        <option value="curious">
          Curious: These dogs will follow their noses wherever they lead!
        </option>
        <option value="laidBack">
          Laid Back: These pups are content to lounge around and soak in the
          sun!
        </option>
        <option value="submissive">
          Submissive: These dogs are happy to let others take the lead. They're
          just along for the ride.
        </option>
      </select>
      <br />
      <label  for="mate-size">Perferred Playmate Size:     </label>
      <select class = 'form'
        name="mate-size"
        id="mate-size"
        v-model="playDate.mateSize"
        required
      >
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
export default {
  data() {
    return {
      playDate: {
        hostPetId: "",
        date: "",
        startTime: "",
        duration: "",
        description: "",
        mateSize: "",
      },
      location: {
        zipCode: "",
        description: "",
      },
    };
  },
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
</style>