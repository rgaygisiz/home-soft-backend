<template>
  <b-container>
    <b-row>
      <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
        <h1>Home Soft</h1>
        <br>
        <app-header></app-header>
        <br>
      </div>
    </b-row>
    <router-view></router-view>
  </b-container>
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
