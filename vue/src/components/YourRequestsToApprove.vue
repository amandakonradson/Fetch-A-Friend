<template>
  <div id="main">
      <h1>Your Requests to Approve:</h1>
      <div id="request tile" v-for="request in requests" 
      v-bind:key="request.requestId"
      v-bind:request="request"
      v-bind:value="request">
        {{request.meetingDate}}
      </div>
    

  </div>
</template>
<script>
import playDateService from "@/services/PlayDateService";
export default {
    data(){
        return {
            id: -1,
            requests: []
        }
    },
    created(){
        this.id = this.$store.state.user.id;

        playDateService.getMyRequestsToApprove(this.id).then((response)=>{
            this.requests = response.data;
        })
        .catch((err) => {
        console.error(err + " nothing returned");
      });

    }

}
</script>

<style>

</style>