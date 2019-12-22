<template>
  <div class="container">
    <div class="row">
      <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
        <h1>Test router</h1>
        <app-header></app-header>
      </div>
    </div>
        <router-view></router-view>
  </div>
</template>

<script>
  import Header from './core/Header.vue';

  export default {
    components: {
      appHeader: Header
    }
  }
</script>

<style lang="scss">

</style>
