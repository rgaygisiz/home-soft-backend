import Vue from 'vue'
import App from './App.vue'


//components
import VueResource from 'vue-resource'

//ui-components
import Datepicker from 'vuejs-datepicker'

//filters
import VueFilterDateFormat from 'vue-filter-date-format'

Vue.use(VueResource);
Vue.use(VueFilterDateFormat);
Vue.component('datepicker', Datepicker);

new Vue({
  el: '#app',
  render: h => h(App)
})
