<template>
  <div class="home">
    <h1 id ="title">Meet & Treat</h1>
    <aside>
      <router-link class="link" id="add-pet" v-bind:to="{name:'add-pet'}">Add a Pet</router-link>
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
    "title title title"
    "aside main sidebar"
    "footer footer footer"
    ;
    background-color: rgb(27, 76, 119);
    color: white;
    height: 100vh;
  }

  #title {
    grid-area: title;
    text-align: center;
    font-size: 50px;
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
