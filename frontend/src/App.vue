<template>
  <div id="app">
    <div class="container">
      <tk-home-page />
    </div>
  </div>
</template>

<script>
import TKHomePage from "@components/TKHomePage.vue";

export default {
  name: "app",
  components: {
    "tk-home-page": TKHomePage,
  },
};
</script>

<style>
* {
  min-width: 0px;
  min-height: 0px;
  margin: 0;
  padding: 0;
  border: 0 solid;
  box-sizing: border-box;
  outline: none;
}

html,
body {
  height: 100vh;
  overflow: hidden;
}

ul,
ol {
  list-style: none;
}

#app {
  --main-usage-color-01: #41B883;
  --main-usage-color-02: #35495e;

  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: black;
  height: 100%;
}

.container {
  width: 100%;
  height: 100%;
}
</style>
