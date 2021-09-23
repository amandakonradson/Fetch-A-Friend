<template>
  <div class="home">
    <aside>
      <router-link class="link" id="add-pet" v-bind:to="{name:'add-pet'}">Add a Pet</router-link>
      <router-link class="link" id="create-playdate" v-bind:to="{name:'play-date-view'}">Create A Playdate</router-link>
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
    grid-template-columns:1fr 1fr 1fr;
    grid-template-areas: 
    "aside main sidebar"
    "footer footer footer"
    ;
    background-color: rgb(27, 76, 119);
    color: white;
    height: 100vh;
  }
  aside {
    grid-area: aside;
    color: white;
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

</style>
