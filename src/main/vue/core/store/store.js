import Vue from 'vue';
import Vuex from 'vuex';

//import { LOADING_STATE } from './loadingState';
import personenState from '../../person/personenStore';

Vue.use(Vuex);


export const store = new Vuex.Store({
  debug: true,
  modules:{
    personenState
  },
  state: {
    personEditor: {
      person: {},
      readable: false
    }
  },
  getters: {},
  mutations: {},
  actions: { }
});

