import Bank from '../../bank/Bank.vue';
import Home from '../../home/Home.vue';
import Person from '../../person/Person.vue';
import Login from '../../login/Login.vue';
import Notfound from '../../notFound/NotFound.vue';
import {store} from '../store/store';

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
  mainMenueItem: true
};

export const logingView = {
  path: '/login',
  component: Login,
  name: 'Login'
};

let bankingView = {
  path: '/banking',
  component: Bank,
  name: 'Banken',
  mainMenueItem: true,
  meta: { requiresAuth: true},
  beforeEnter
};

let personView = {
  path: '/personen',
  component: Person,
  name: 'Personen',
  mainMenueItem: true,
  meta: { requiresAuth: true},
  beforeEnter};

let notFoundView = {
  path: '/notFound',
  component: Notfound,
  name: 'Not Found'};

let root = {
  path: '/',
  redirect: homeView.path};


export const routes = [
  root,
  homeView,
  logingView,
  bankingView,
  personView,
  notFoundView
];
