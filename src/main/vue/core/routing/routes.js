import Bank from '../../bank/Bank.vue';
import Bankkonto from '../../bankkonto/Bankkonto.vue';
import Home from '../../home/Home.vue';
import Person from '../../person/Person.vue';
import Login from '../../login/Login.vue';
import Notfound from '../../notFound/NotFound.vue';
import {store} from '../store/store';
import VueRouter from 'vue-router';

let beforeEnter = (to, from, next) => {
  if( to.meta && to.meta.requiresAuth && !store.getters.isLogin() ){
    next('login?returnTo=' + to.path);
  }else{
    next();
  }
};

export const homeView = {
  path: '/home',
  component: Home,
  name: 'Home',
  meta: {
    requiresAuth: false,
    active: false
  },
  mainMenueItem: true
};

export const logingView = {
  path: '/login',
  component: Login,
  meta: {
    requiresAuth: false,
    active: false
  },
  name: 'Login'
};

let bankingView = {
  path: '/banking',
  component: Bank,
  name: 'Banken',
  mainMenueItem: true,
  meta: {
    requiresAuth: true,
    active: false
  },
  beforeEnter
};

let bankaccountView = {
  path: '/banking/accounts',
  component: Bankkonto,
  name: 'Bankkonten',
  mainMenueItem: true,
  meta: {
    requiresAuth: true,
    active: false
  },
  beforeEnter
};

let personView = {
  path: '/personen',
  component: Person,
  name: 'Personen',
  mainMenueItem: true,
  meta: {
    requiresAuth: true,
    active: false
  },
  beforeEnter};

let notFoundView = {
  path: '/notFound',
  component: Notfound,
  meta: {
    requiresAuth: false,
    active: false
  },
  name: 'Not Found'};

let root = {
  path: '/',
  meta: {
    requiresAuth: false,
    active: false
  },
  redirect: homeView.path};


let routes = [
  root,
  homeView,
  logingView,
  bankingView,
  bankaccountView,
  personView,
  notFoundView
];

export const router = new VueRouter({
  routes: routes,
  mode: 'history',
  base: '/homesoft',
  saveScrollPosition: true
});

router.afterEach((to, from) => {
  to.meta.active = true;
  from.meta.active = false;
})
