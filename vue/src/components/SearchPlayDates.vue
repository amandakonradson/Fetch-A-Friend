<template>
  <div>
      <h1> Hello </h1>
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
                  <td> {{ playDate.meetingDate }} </td>
                  <td> {{ playDate.startTime }} {{ playDate.duration }} </td>
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
            availablePlaydates: [], //pull playDates from backend and put in created
               
        }
    },
    computed: {
        filteredList() {
            let filteredPlayDates = this.availablePlaydates;
           if (this.filter.location != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.location.toLowerCase().includes(this.filter.location.toLowerCase());
               });
           }
           if (this.filter.date != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.date.toLowerCase().includes(this.filter.date.toLowerCase());
               });
           }
           if (this.filter.time != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.time.toLowerCase().includes(this.filter.time.toLowerCase());
               });
           }
           if (this.filter.breed != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.breed.toLowerCase().includes(this.filter.breed.toLowerCase());
               });
           }
           if (this.filter.size != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.size.toLowerCase().includes(this.filter.size.toLowerCase());
               });
           }
           if (this.filter.temperament != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.temperament.toLowerCase().includes(this.filter.temperament.toLowerCase());
               });
           }
           if (this.filter.spayedNeutered != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.spayedNeutered.toLowerCase().includes(this.filter.spayedNeutered.toLowerCase());
               });
           }
           return filteredPlayDates;
        }
    },
    created() {
        playDateService.getAvailablePlayDates()
        .then((response) => {
            this.availablePlaydates = response.data;

        })
        .catch((error) => {
            if (error.response) {
                window.alert("Missing Play Dates");
            } else if (error.request) {
                window.alert("Could not reach service");
            }
            
        })

    }

}
</script>

<style>
h1 {
    color: white;
}

</style>