<template>
  <div>
    <form v-on:submit.prevent="sendRequest">
      <label> Which of Your Pets Would You Like to Edit: </label>
      <select class="form" v-model="dog.petId">
        <option
          v-for="dog in dogsByUserId"
          v-bind:key="dog.petId"
          v-bind:petId="petId"
          v-bind:value="dog.petId"
          required
          selected
        >
          {{ dog.name }}
        </option>
        >
        </select>
        <br>
        <label for="name">Name: </label>
        <input type="text" id="name" v-model="dog.name" required />
        <br />
        <br />

        <label for="breed">Breed: </label>
        <select id="breed" v-model="dog.breed" required>
          <option
            v-for="breed in this.$store.state.breeds"
            v-bind:key="breed"
            v-bind:breed="breed"
            v-bind:value="breed"
            selected
          >
            {{ breed }}
          </option>
        </select>
        <br />
        <br />

        <label for="birthYear">Year of Birth: </label>
        <input
          type="number"
          min="2000"
          max="current"
          step="1"
          id="birthYear"
          v-model="dog.birthYear"
          required
        />
        <br />
        <br />

        <label for="gender">Gender: </label>
        <select id="dogGender" v-model="dog.gender" required>
          <option value="Male" selected>Male</option>
          <option value="Female" selected>Female</option>
        </select>
        <br />
        <br />

        <label for="size">Approximate size: </label>
        <select id="dogSize" v-model="dog.size" required>
          <option value="extra small" selected>
            Extra Small (ex. Chihuahua, Maltese, Yorkshire Terrier)
          </option>
          <option value="small" selected>
            Small (ex. Beagle, French Bulldog, Boston Terrier)
          </option>
          <option value="medium" selected>
            Medium (ex. Border Collie, Corgi, Spaniel)
          </option>
          <option value="large" selected>
            Large (ex. Lab, Golden Retriever, Australian Shepherd)
          </option>
          <option value="extra large" selected>
            Extra Large (ex. Great Dane, Saint Bernard, Newfoundland)
          </option>
        </select>
        <br />
        <br />

        <label for="spayedNeutered">Spayed/Neutered? </label>
        <select
          id="spayedNeuteredSelection"
          v-model="dog.spayedNeutered"
          required
        >
          <option value="yes" selected>Yes</option>
          <option value="no" selected>No</option>
        </select>
        <br />
        <br />

        <label for="temperament">Temperament: </label>
        <select
          name="temperaments"
          id="temperaments"
          v-model="dog.temperament"
          required
        >
          <option value="energetic">
            High Energy: These dogs can chase a ball for hours!
          </option>
          <option value="timid">
            Timid: Once these pups feel comfortable, they make great playmates!
          </option>
          <option value="friendly">
            Friendly: These dogs love everyone they encounter!!
          </option>
          <option value="protective">
            Protective: These human devoted pups and may prefer to socialize
            from a distance!
          </option>
          <option value="dominant">
            Dominant: These are the leaders of the pack and like to be in
            charge!
          </option>
          <option value="curious">
            Curious: These dogs are most interested in exploring their
            surroundings!
          </option>
          <option value="relaxed">
            Laid Back: These pups are content to lounge around and soak in the
            sun!
          </option>
          <option value="submissive">
            Submissive: These dogs are happy to let others take the lead!
          </option>
        </select>
        <br />
        <br />
        <input id="vaccination" type="checkbox" required />
        <label for="vaccination" id="vaccine"
          >By checking this box, you certify your pet is up to date on all
          legally required vaccinations.</label
        >
      <br /><br /><button class="button-submit">Submit</button>
      <button class="button-cancel">
        <router-link id="cancelBtn" to="/play-date/available/"
          >Cancel</router-link
        >
      </button>
    </form>
  </div>
</template>

<script>
import petService from "@/services/PetService";
export default {
  data() {
    return {
      dogsByUserId: [],
      dog: {
        name: "",
        breed: "",
        birthYear: "",
        gender: "",
        size: "",
        spayedNeutered: "",
        temperament: "",
      },
      id: -1,
    };
  },
  created() {
    this.id = this.$store.state.user.id;

    petService
      .listUserPets(this.id)
      .then((dogData) => {
        this.dogsByUserId = dogData.data;
      })
      .catch((err) => {
        console.error(err + " nothing returned");
      });
  },
};
</script>

<style>
</style>