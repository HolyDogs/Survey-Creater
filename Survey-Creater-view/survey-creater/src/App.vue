<template>
  <div>
    <router-view class="view" 
    keep-alive
    transition
    transition-mode="out-in"></router-view>
  </div>
</template>
<script type="text/javascript" src="../../style/survey.vue.min.js"/>
<script>

export default {
  components:{}
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  height: 100%;
}
</style>
