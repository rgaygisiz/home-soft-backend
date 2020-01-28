import Bank from '../../bank/Bank.vue';
import Home from '../../home/Home.vue';
import Person from '../../person/Person.vue';
import Login from '../../login/Login.vue';

import SessionManager from '../../login/SessionManager';

let notProtected = new Set([logingView, homeView]);

let beforeEnter = (to, from, next) => {
  if(notProtected.has(to)){
    next();
  }else if(SessionManager.hasToken()){
      next();
  } else {
      next('login?returnTo=' + to.path);
  }
};

let homeView = {
  path: '/',
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
  beforeEnter
};
let personView = { path: '/personen',
  component: Person,
  name: 'Personen',
  mainMenueItem: true,
  beforeEnter};

export const routes = [
  homeView,
  logingView,
  bankingView,
  personView
];
