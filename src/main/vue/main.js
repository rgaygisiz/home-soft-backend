import App from './App.vue';
import BootstrapVue from 'bootstrap-vue';

import {store} from './core/store/store';
import {CONFIG} from './config';
import {routes} from './core/routing/routes';
import Vue from 'vue';

import VueRouter from 'vue-router';

import 'bootstrap-scss/bootstrap.scss';
import 'bootstrap-scss/bootstrap-grid.scss';


Vue.use(BootstrapVue);
Vue.use(VueRouter);

const  router = new VueRouter({
  routes: routes,
  mode: 'history',
  saveScrollPosition: true
});
Vue.router = router;

Vue.url.options.root=CONFIG.host;
Vue.http.interceptors.push((request, next) => {
  if(store.getters.isLogin()){
    request.headers.set('Authorization', 'Bearer ' + store.getters.getToken())
  }else{
    next();
  }
});

//filters
import VueFilterDateFormat from 'vue-filter-date-format'
Vue.use(VueFilterDateFormat);

const app = new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
});
