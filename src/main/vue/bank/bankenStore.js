import {LOADING_STATE} from "../core/store/loadingState";
import Vue from "vue";
import {CONFIG} from '../config';

const bankState = {
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
      state.bankenState.emptyText = successfullLoaded;
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
  async loadBanken(context) {
    context.commit('setBankenLadeState', LOADING_STATE.LOADING);
    let response = await fetch(CONFIG.host + '/api/banken');
    if(response.ok){
      let data = await response.json();
      context.commit('setBankenLadeState', LOADING_STATE.LOADED);
      context.commit('setBanken', data.data);
      context.commit('updateBankEmptyText', 'Leer');
    }else {
      context.commit('setBankenLadeState', LOADING_STATE.FAIL);
      context.commit('setBanken', []);
      context.commit('updateBankEmptyText', 'Laden fehlgeschlagen');
    }
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
  state: bankState,
  getters,
  mutations,
  actions
};
