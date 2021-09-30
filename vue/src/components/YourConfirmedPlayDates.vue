<template>
  <div id="main">
      <h1>Upcoming Playdates: </h1>
      <div id = "tile">
      <div id="upcoming-tile" v-for="playDate in playDates"
      v-bind:key="playDate.index"
      v-bind:playDate="playDate"
      v-bind:value="playDate">
        <b>Date: </b><div class="data"> {{changeDateFormat(playDate.meetingDate)}} </div><br>
        <b>Start Time: </b> <div class="data">{{playDate.startTime}} </div><br>
        <b>Play Time:</b> <div class="data"> {{playDate.duration}} minutes </div><br>
        <b>Location: </b> <div class="data">{{playDate.locationStreetAddress}}<br>
       <div id = address> {{playDate.locationCity}}, {{playDate.locationZipcode}} </div> </div></div>
        <br>
      </div>
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";
export default {
    data() {
        return{
            id: -1,
            playDates: []
        }; 
    },
    created() {
        this.id = this.$store.state.user.id; 

        playDateService.getAllConfirmedPlayDates(this.id)
        .then((response) => {
            this.playDates = response.data;
        })
        .catch((error) => {
            if (error.response) {
                window.alert("Bad Dog!");
            } else if (error.request) {
                window.alert("Could not reach service");
            }
        });
    }, 
    methods:{
        changeDateFormat(insertDate){
 let myArr= insertDate.split('-');
 return myArr[1]+"-"+myArr[2]+"-"+myArr[0];
    }

}
}
</script>

<style scoped>
#upcoming-tile {
    border: 5px white dotted;
  font-size: 30px;
  padding-left:20px;
  padding-bottom: 20px;
  padding-top: 40px;
     line-height: 30px;
   background-color: rgb(25, 149, 180);
     margin: 5px;
     width: 350px;
     
}
#main{
  display: flex;
  flex-direction:column;
  margin-left: 20px;
}
#tile{
    display:flex;
    flex-wrap: wrap;
}
h1{
  font-size:42px;
}
.data{
    margin-left: 10px;
    display:inline;
    font-size: 26px;
}
#address{
    margin-left: 125px;
}
</style>