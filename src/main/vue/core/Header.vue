<template>
  <b-container>
    <b-row>
      <b-col>
        <b-button-group>
          <b-button
            v-for="routeItem in getRoutes"
            v-bind:key="routeItem.path"
            v-if="routeItem.mainMenueItem"
            variant="outline-primary"
            :pressed.sync="routeItem.meta.active"
            size="sm"
            :to="routeItem.path">{{routeItem.name}}</b-button>
        </b-button-group>
      </b-col>
      <b-col>
        <b-button
          v-on:click="loginHandling"
          variant="outline-primary"
          :pressed.sync="getLoginView.meta.active"
          size="sm" >{{ getLoginLabel }}</b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  import 'bootstrap-scss/bootstrap.scss';
  import 'bootstrap-scss/bootstrap-grid.scss';
  import { mapGetters, mapActions } from 'vuex';
  import { logingView } from './../core/routing/routes';


  export default{
    computed: {
      ...mapGetters([
        'getLoginLabel',
        'isLogin'
      ]),
      getRoutes(){
        return this.$router.options.routes;
      },
      getLoginView(){
        console.log(this.$router.options.routes);
        return this.$router.options.routes.find(item => item.path == logingView.path);
      }
    },
    methods: {
      ...mapActions([
        'logout'
      ]),
      loginHandling(){
        console.log("Header.isLogin()", this.isLogin())
        if ( this.isLogin() ){
          this.logout();
          if( !Object.is(this.$route.path, logingView.path) ){
            this.$router.push(logingView);
          }
        }else if( !Object.is(this.$route.path, logingView.path) ){
          this.$router.push(logingView);
        }
      }
    }
  }
</script>
