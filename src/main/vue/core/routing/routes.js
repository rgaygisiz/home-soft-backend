import Bank from '../../bank/Bank.vue'
import Home from '../../home/Home.vue'
import Person from '../../person/Person.vue'

export const routes = [
  { path: '', component: Home},
  { path: '/banking', component: Bank},
  { path: '/personen', component: Person}
]
