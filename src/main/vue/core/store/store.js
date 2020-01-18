import Vue from 'vue';
import Vuex from 'vuex';

//import { LOADING_STATE } from './loadingState';
import personenState from '../../person/personenStore';
import bankenState from '../../bank/bankenStore';

Vue.use(Vuex);


export const store = new Vuex.Store({
  debug: true,
  modules:{
    personenState,
    bankenState
  },
  getters: {},
  mutations: {},
  actions: { }
});

