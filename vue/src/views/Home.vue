<template>
  <div class="home">
    <aside>
      <br><br>
      <div class = "action-link">
      <router-link class="link" id="add-pet" v-bind:to="{name:'add-pet'}">Add a Pet </router-link> <br> </div>    <!--&nbsp;|&nbsp; -->
      <div class = "action-link">
      <router-link class="link" id="create-playdate" v-bind:to="{name:'play-date-view'}">Create A Playdate</router-link><br></div> 
      <div class = "action-link">
      <router-link class="link" id="search-play-dates" v-bind:to="{name: 'play-date-search'}">Search Playdates</router-link></div>
    </aside>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style scoped>
  .home {
    display: grid;
    grid-template-columns:200px 1fr 1fr;
    grid-template-areas: 
    "aside main main"
    "footer footer footer"
    ;
    background-color: rgb(2, 59, 109);
    color: white;
    height: 100vh;
    
    
  }
  aside {
    grid-area: aside;
    color: white;
    border-right: white 5px dotted;
    
    
  }
  main {
    grid-area: main;
  }
  nav.sidebar {
    grid-area: sidebar;
  }
  footer {
    grid-area: footer;
  
  }
  .link{
    color: white;
    text-decoration: none; color: inherit;
    font-size: 24px;
    margin-left: 10px;
    
  }
  #add-pet{
    padding-bottom: 90px;
  }
  .action-link{
  margin-bottom:15px;
  }

</style>
