<template>
  <div id="main">
      <h1> Review available play dates below: </h1>
      <table>
          <thead>
              <tr>
                  <th>Pick This Play Date</th>
                  <th>Location</th>
                  <th>Date</th>
                  <th>Time</th>
                  <th>Ideal Playmate</th>
                  <th>Name and Breed</th>
                  <th class = "size">Size</th>
                  <th>Temperament</th>
                  <th>Spayed/ <br> Neutered?</th>
              </tr>
          </thead>
          <tbody>
              <tr>
                  <td></td>
                  <td> 
                    <input type="text" id="locationFilter" v-model="filter.location" />
                  </td>
                  <td>
                      <input type="text" id="dateFilter" v-model="filter.date"/>                    
                  </td>
                  <td>
                      <input type="text" id="timeFilter" v-model="filter.time"/>
                  </td>
                  <td>
                      <input type="text" id="mateDescFilter" v-model="filter.mateDescription"/>
                  </td>
                  <td>
                      <input type="text" id="breedFilter" v-model="filter.breed"/>
                  </td>
                  <td class="size">
                      <input type="text" id="sizeFilter" v-model="filter.size"/>
                  </td>
                  <td>
                      <input type="text" id="temperamentFilter" v-model="filter.temperament"/>
                  </td>
                  <td>
                      <input type="text" id="spayedNeuteredFilter" v-model="filter.spayedNeutered"/>
                  </td>
              </tr>
              
              <tr id="playDateInfo" v-for="playDate in filteredList" v-bind:key="playDate.id">
                  <td ><router-link class="link" id="pd-signup" v-bind:to="{name:'pd-signup', params:{id: playDate.playDateId}}" v-bind:key="playDate.playDateId"><play-date-signup v-bind:playdate="playdate"/>Join</router-link></td>
                  <td> {{ playDate.locationStreetAddress }} <br/> {{ playDate.locationCity }} <br/> {{ playDate.locationZipcode }} </td>
                  <td> {{ playDate.meetingDate }} </td>
                  <td> Start time:  {{ playDate.startTime }} <br> Probable play time: {{ playDate.duration }} min. </td>
                  <td> Temperament:<div v-for="description in playDate.mateDescription" v-bind:key="description.index"> {{ description }}</div> <br> Size: {{ playDate.mateSize }} </td>
                  <td> <b>{{ playDate.name }}</b> <br> {{ playDate.breed }} </td>
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
                mateDescription:'',
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
                   return playDate.meetingDate.toLowerCase().includes(this.filter.date.toLowerCase());
               });
           }
           if (this.filter.time != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.startTime.toLowerCase().includes(this.filter.time.toLowerCase());
               });
           }
           if (this.filter.mateDescription != "") {
               filteredPlayDates = filteredPlayDates.filter((playDate) => {
                   return playDate.mateDescription.toLowerCase().includes(this.filter.mateDescription.toLowerCase());
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

<style scoped>
h1 {
    color: white;
    margin-left: 20px;
}
#main {
    background-color: rgb(2, 59, 109);
}
table {
    border: white, 5px, solid;
    width: 100px;
    margin-left: auto;
    margin-right:auto;
}
th {
    text-align: left;
    font-size: 28px;
    font-weight: bold; 
}
td {
    font-size: 20px;
}
#playDateInfo {
    margin-bottom: 30px;
}
td, th {
    border: white, 5px, solid;
    text-align: center;
}
table, th, td {
    border: 2px dotted white; 
}
.link{
    color: white;
    text-decoration: none; color: inherit;
    font-size: 24px;
}

@media only screen and (max-width:550px){
    
        
    }


</style>