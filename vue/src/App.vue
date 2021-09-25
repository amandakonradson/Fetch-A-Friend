<template>
  <div id="app">
    <img  id = "logo-banner" src="../images/logobanner.png" alt="logo">
    <div id="nav">
      <router-link class="link" v-bind:to="{ name: 'home' }">Home |</router-link>   <!--&nbsp;|&nbsp;-->
      <router-link class="link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"> Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>
  #app {
  background-color: rgb(2, 59, 109);
  color: white; 
  height: 100vh;
  
  }
  .link {
    color: white;
    text-decoration: none; color: inherit;
    font-size: 30px;
  }
  #logo-banner{
    width: 100vw;
  }
</style> rgb(27, 76, 119);