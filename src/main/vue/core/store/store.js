import Vue from 'vue';
import Vuex from 'vuex';

//import { LOADING_STATE } from './loadingState';
import personenState from '../../person/personenStore';
import bankenState from '../../bank/bankenStore';
import loginState from '../../login/loginStore';

Vue.use(Vuex);


export const store = new Vuex.Store({
  debug: true,
  modules:{
    loginState,
    personenState,
    bankenState
  },
  getters: {},
  mutations: {},
  actions: { }
});

