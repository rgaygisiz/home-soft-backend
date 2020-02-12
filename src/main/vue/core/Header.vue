<template>
  <b-container>
    <b-row>
      <b-col>
        <ul class="nav nav-pills">
          <li v-for="route in $router.options.routes">
            <b-button
              v-if="route.mainMenueItem"
              pill
              variant="outline-primary"
              size="sm"
              :to="route.path">{{route.name}}</b-button>
          </li>
        </ul>
      </b-col>
      <b-col>
        <b-button
          pill v-on:click="loginHandling"
          variant="outline-primary"
          size="sm" >{{ getLoginLabel }}</b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  import 'bootstrap-scss/bootstrap.scss';
  import 'bootstrap-scss/bootstrap-grid.scss';
  import { mapGetters, mapActions } from 'vuex';
  import { logingView, homeView} from './../core/routing/routes';


  export default{
    computed: {
      ...mapGetters([
        'getLoginLabel',
        'isLogin'
      ])
    },
    methods: {
      ...mapActions([
        'logout'
      ]),
      loginHandling(){
        if ( this.isLogin ){
          this.logout();
          if( !Object.is(this.$route.path, logingView.path) ){
            this.$router.push(logingView);
          }
        }else if( !Object.is(this.$route.path, logingView.path) ){
          this.$router.push(logingView);
        }else{
          this.$router.push(homeView);
        }
      }
    }
  }
</script>
