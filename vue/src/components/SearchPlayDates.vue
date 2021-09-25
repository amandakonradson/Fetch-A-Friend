<template>
  <div>
      <table>
          <thead>
              <tr>
                  <th>Location</th>
                  <th>Date</th>
                  <th>Time</th>
                  <th>Name and Breed</th>
                  <th>Size</th>
                  <th>Temperament</th>
                  <th>Spayed/Neutered?</th>
              </tr>
          </thead>
          <tbody>
              <tr>
                  <td> 
                    <input type="text" id="locationFilter" v-model="filter.location"/>
                  </td>
                  <td>
                      <input type="text" id="dateFilter" v-model="filter.date"/>                    
                  </td>
                  <td>
                      <input type="text" id="timeFilter" v-model="filter.time"/>
                  </td>
                  <td>
                      <input type="text" id="breedFilter" v-model="filter.breed"/>
                  </td>
                  <td>
                      <input type="text" id="sizeFilter" v-model="filter.size"/>
                  </td>
                  <td>
                      <input type="text" id="temperamentFilter" v-model="filter.temperament"/>
                  </td>
                  <td>
                      <input type="text" id="spayedNeuteredFilter" v-model="filter.spayedNeutered"/>
                  </td>
              </tr>
              <tr v-for="playDate in filteredList" v-bind:key="playDate.id">
                  <td> {{ playDate.locationStreetAddress }} <br/> {{ playDate.locationCity }} <br/> {{ playDate.locationZipcode }} </td>
                  <td> {{ playDate.date }} </td>
                  <td> {{ playDate.time }} {{ playDate.duration }} </td>
                  <td> {{ playDate.breed }} </td>
                  <td> {{ playDate.size }} </td>
                  <td> {{ playDate.temperament }} </td>
                  <td> {{ playDate.spayedNeutered }} </td>
              </tr>
          </tbody>
      </table>
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";

export default {
    data() {
        return {
            filter: {
                location:'',
                date:'',
                time:'',
                breed:'',
                size:'',
                temperament:'',
                spayedNeutered:''
            },
            playDates: {
                availablePlaydates: [],
                hostDog: [],
            },
        }
    },
    computed: {
        filteredList() {
            let filteredPlayDates = this.playDates;
           if (this.filter.location != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.availablePlaydates.location.toLowerCase().includes(this.filter.location.toLowerCase());
               });
           }
           if (this.filter.date != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.availablePlaydates.date.toLowerCase().includes(this.filter.date.toLowerCase());
               });
           }
           if (this.filter.time != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.availablePlaydates.time.toLowerCase().includes(this.filter.time.toLowerCase());
               });
           }
           if (this.filter.breed != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.hostDog.breed.toLowerCase().includes(this.filter.breed.toLowerCase());
               });
           }
           if (this.filter.size != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.hostDog.size.toLowerCase().includes(this.filter.size.toLowerCase());
               });
           }
           if (this.filter.temperament != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.hostDog.temperament.toLowerCase().includes(this.filter.temperament.toLowerCase());
               });
           }
           if (this.filter.spayedNeutered != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.hostDog.spayedNeutered.toLowerCase().includes(this.filter.spayedNeutered.toLowerCase());
               });
           }
           return filteredPlayDates;
        }
    }

}
</script>

<style>

</style>