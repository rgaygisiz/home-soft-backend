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
          pill
          variant="outline-primary"
          size="sm"
          :to="loginRoute.path">{{loginRoute.name}}</b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  import 'bootstrap-scss/bootstrap.scss';
  import 'bootstrap-scss/bootstrap-grid.scss';
  import {logingView} from './routing/routes';

  export default{
    computed:{
      loginRoute(){
        return logingView;
      }
    }
  }
</script>
