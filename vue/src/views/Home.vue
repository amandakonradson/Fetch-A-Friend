<template>
  <div class="home">
    <aside>
      <router-link class="link" id="add-pet" v-bind:to="{name:'add-pet'}">Add a Pet </router-link> <br>     <!--&nbsp;|&nbsp; -->
      <router-link class="link" id="create-playdate" v-bind:to="{name:'play-date-view'}">Create A Playdate</router-link> <br>
      <router-link class="link" id="search-play-dates" v-bind:to="{name: 'play-date-search'}">Search Playdates</router-link>
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
    
    
  }
  aside {
    grid-area: aside;
    color: white;
    border-right: white 5px dotted;
    height: 100vh;
    margin-top: 50px;
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
  }
  

</style>
