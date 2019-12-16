import Vue from 'vue'
import App from './App.vue'

import 'bootstrap-scss/bootstrap.scss'
import 'bootstrap-scss/bootstrap-grid.scss'


import BootstrapVue from 'bootstrap-vue'
Vue.use(BootstrapVue);

Vue.url.options.root="http://localhost:8081";

//filters
import VueFilterDateFormat from 'vue-filter-date-format'
Vue.use(VueFilterDateFormat);

new Vue({
  el: '#app',
  render: h => h(App)
})
