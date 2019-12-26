import App from './App.vue';
import BootstrapVue from 'bootstrap-vue';
import {routes} from './core/routing/routes';
import Vue from 'vue';
import VueRouter from 'vue-router';


import {store} from './core/store/store';

import 'bootstrap-scss/bootstrap.scss';
import 'bootstrap-scss/bootstrap-grid.scss';



Vue.use(BootstrapVue);
Vue.use(VueRouter);

const  router = new VueRouter({
  routes,
  mode: 'history'
});

Vue.url.options.root="http://localhost:8081";

//filters
import VueFilterDateFormat from 'vue-filter-date-format'
Vue.use(VueFilterDateFormat);

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
});
