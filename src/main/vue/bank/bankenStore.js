import {LOADING_STATE} from "../core/store/loadingState";
import Vue from "vue";

const state = {
  bankenState: {
    loadingState: LOADING_STATE.UNUSED,
    items: [],
    newItem: {},
    emptyText: 'Leere'
  }
};

const getters = {
  bankenState(state) {
    return state.bankenState
  },
  newBank(state){
    return state.bankenState.newItem;
  },
  isBankenLoading(state) {
    return state.bankenState.loadingState === LOADING_STATE.LOADING;
  }
};

const mutations = {
  setBanken(state, banken) {
    if (Array.isArray(banken)) {
      state.bankenState.items = banken;
    }
  },
  updateBankEmptyText(state, successfullLoaded){
    if(successfullLoaded){
      state.bankenState.emptyText = 'Leer';
    } else {
      state.bankenState.emptyText = 'Laden fehlgeschlagen';
    }
  },
  setBankenLadeState(state, loadingState) {
    if (loadingState) {
      state.bankenState.loadingState = loadingState;
    } else {
      state.bankenState.loadingState = LOADING_STATE.FAIL
    }
  },
  resetNewBank(state){
    state.bankenState.newItem= {};
  },
  addNewBank(state, bank){
    state.bankenState.items.push(bank);
  }
};

const actions = {
  loadBanken(context) {
    context.commit('setBankenLadeState', LOADING_STATE.LOADING);
    Vue.http.get("api/banken")
    .then(
      payload => payload.json(),
      error => error.json())
    .then((payload) => {
      if (payload.data) {
        context.commit('setBankenLadeState', LOADING_STATE.LOADED);
        context.commit('setBanken', payload.data);
        context.commit('updateBankEmptyText', 'Leer')
      } else {
        context.commit('setBankenLadeState', LOADING_STATE.FAIL);
        context.commit('setBanken', []);
        context.commit('updateBankEmptyText', 'Laden fehlgeschlagen')
      }
    });
  },
  saveBank(context){
    Vue.http.post('/api/banken', context.getters.newBank)
    .then( response => response.json(), console.log)
    .then((payload) => {
      context.commit('addNewBank', payload.data);
    })
  },
  deletePerson(context, ids){
    if( ids ){
      Vue.http.delete('/api/banken/' + ids)
      .then(response => response.json(), console.log)
      .then(value => context.dispatch('loadBanken'));
    }
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
