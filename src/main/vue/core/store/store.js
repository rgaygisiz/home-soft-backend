import Vue from 'vue';
import Vuex from 'vuex';

//import { LOADING_STATE } from './loadingState';
import personenState from '../../person/personenStore';
import bankenState from '../../bank/bankenStore';
import loginState from '../../login/loginStore';
import bankkontenState from '../../bankkonto/bankkontenStore';

Vue.use(Vuex);


export const store = new Vuex.Store({
  debug: true,
  modules:{
    bankkontenState,
    loginState,
    personenState,
    bankenState
  },
  getters: {},
  mutations: {},
  actions: { }
});

