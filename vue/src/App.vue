<template>
  <div id="app">
    <div id="nav">
      <router-link class="link" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link class="link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>
  #app {
  background-color: rgb(27, 76, 119);
  color: white; 
  }
  .link {
    color: white;
    text-decoration: none; color: inherit;
    
  }
</style>