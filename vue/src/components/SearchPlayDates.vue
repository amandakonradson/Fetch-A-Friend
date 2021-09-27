<template>
  <div id="main">
    <h1>Review available play dates below:</h1>
    <table>
      <thead>
        <tr>
          <th>Ideal Playmate</th>
          <th>City</th>
          <th>Zip Code</th>
          <th>Date</th>
          <th>Time</th>
          <th class="size">Size</th>
          <th>Temperament</th>
          <th>
            Spayed/ <br />
            Neutered?
          </th>
          <th>Pick This <br>Play Date</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td></td>
          <td>
            <input
              type="text"
              id="locationCityFilter"
              v-model="filter.locationCity"
            />
          </td>
          <td>
            <input
              type="text"
              id="locationZipcodeFilter"
              v-model="filter.locationZipcode"
            />
          </td>
          <td>
            <input type="text" id="dateFilter" v-model="filter.date" />
          </td>
          <td>
            <input type="text" id="timeFilter" v-model="filter.time" />
          </td>
          <td class="size">
            <input type="text" id="sizeFilter" v-model="filter.size" />
          </td>
          <td>
            <input
              type="text"
              id="temperamentFilter"
              v-model="filter.temperament"
            />
          </td>
          <td>
            <input
              type="text"
              id="spayedNeuteredFilter"
              v-model="filter.spayedNeutered"
            />
          </td>
          <td></td>
        </tr>

        <tr
          id="playDateInfo"
          v-for="playDate in filteredList"
          v-bind:key="playDate.id"
        >
          <td>
            Temperament:
            <div
              v-for="description in playDate.mateDescription"
              v-bind:key="description.index"
            >
              {{ description }}
            </div>
            <br />
            Size: {{ playDate.mateSize }}
          </td>
          <td>
            {{ playDate.locationStreetAddress }} <br />
            {{ playDate.locationCity }}
          </td>
          <td>{{ playDate.locationZipcode }}</td>
          <td>{{ playDate.meetingDate }}</td>
          <td>
            Start time: {{ playDate.startTime }} <br />
            Probable play time: {{ playDate.duration }} min.
          </td>
          <td>{{ playDate.size }}</td>
          <td>{{ playDate.temperament }}</td>
          <td>{{ playDate.spayedNeutered }}</td>
          <td>
            <router-link
              class="link"
              id="pd-signup"
              v-bind:to="{
                name: 'pd-signup',
                params: { id: playDate.playDateId },
              }"
              v-bind:key="playDate.playDateId"
              ><play-date-signup v-bind:playdate="playdate" />Click for <br>Details</router-link
            >
          </td>
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
        locationCity: "",
        locationZipcode: "",
        date: "",
        time: "",
        mateDescription: "",
        size: "",
        temperament: "",
        spayedNeutered: "",
      },
      availablePlaydates: [], //pull playDates from backend and put in created
    };
  },
  computed: {
    filteredList() {
      let filteredPlayDates = this.availablePlaydates;
      if (this.filter.locationCity != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.locationCity
            .toLowerCase()
            .includes(this.filter.locationCity.toLowerCase());
        });
      }
      if (this.filter.locationZipcode != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.locationZipcode.toString().includes(this.filter.locationZipcode);
        });
      }
      if (this.filter.date != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.meetingDate
            .toLowerCase()
            .includes(this.filter.date.toLowerCase());
        });
      }
      if (this.filter.time != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.startTime
            .toLowerCase()
            .includes(this.filter.time.toLowerCase());
        });
      }
      if (this.filter.size != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.size
            .toLowerCase()
            .includes(this.filter.size.toLowerCase());
        });
      }
      if (this.filter.temperament != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.temperament
            .toLowerCase()
            .includes(this.filter.temperament.toLowerCase());
        });
      }
      if (this.filter.spayedNeutered != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.spayedNeutered
            .toLowerCase()
            .includes(this.filter.spayedNeutered.toLowerCase());
        });
      }
      return filteredPlayDates;
    },
  },
  created() {
    playDateService
      .getAvailablePlayDates()
      .then((response) => {
        this.availablePlaydates = response.data;
      })
      .catch((error) => {
        if (error.response) {
          window.alert("Missing Play Dates");
        } else if (error.request) {
          window.alert("Could not reach service");
        }
      });
  },
};
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
  max-width: 100%;
  margin-left: auto;
  margin-right: auto;
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
td,
th {
  border: white, 5px, solid;
  text-align: center;
}
table,
th,
td {
  border: 2px dotted white;
}
.link {
  color: white;
  text-decoration: none;
  color: inherit;
  font-size: 24px;
}

input {
  max-width: 100%;
}
#sizeFilter{
  width:100px;
}
#dateFilter{
  width:125px;
}
#locationZipcodeFilter{
  width: 100px;
}
#spayedNeuteredFilter{
  width: 150px;
}
#pd-signup{
  font-weight: bold;
}

@media only screen and (max-width: 550px) {
}
</style>