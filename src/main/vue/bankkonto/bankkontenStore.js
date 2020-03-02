import {LOADING_STATE} from "../core/store/loadingState";
import {CONFIG} from "../config";

const bankkontoState = {
  bankkontenState:{
    loadingState: LOADING_STATE.UNUSED,
    items: [],
    newItem: {},
    emptyText: 'Leere'
  }
};


const REST_PATH = '/api/banken/konten';

const getters = {
  bankkontenState(state) {
    return state.bankkontenState;
  },
  isBankkontenLoading(state){
    return state.bankkontenState.loadingState == LOADING_STATE.LOADING;
  }
};
const mutations = {
  switchLoadState(state, loadState){
    if( loadState ){
      state.bankkontenState.loadingState = loadState;
    }else{
      state.bankkontenState.loadingState = LOADING_STATE.FAIL;
    }
  },
  mutateBankkontenData(state, data){
    if(Array.isArray(data)){
      state.bankkontenState.items = data;
    }
  },
  updateBankkontenEmptyText(state, successfullLoaded){
    if(successfullLoaded){
      state.bankkontenState.emptyText = successfullLoaded;
    } else {
      state.bankkontenState.emptyText = 'Laden fehlgeschlagen';
    }
  },
  addNewBankAccount(state, account){
    state.bankkontenState.items.push(account);
  }
};
const actions = {
  async loadBankkonten(context){
    context.commit('switchLoadState', LOADING_STATE.LOADING);
    let response = await fetch(CONFIG.host + REST_PATH);
    if(response.ok){
      let data = await response.json();
      data.data.forEach( item => item.besitzer.fullName = item.besitzer.firstName + ", " + item.besitzer.lastName);
      context.commit('switchLoadState', LOADING_STATE.LOADED);
      context.commit('mutateBankkontenData', data.data);
      context.commit('updateBankkontenEmptyText', 'Leer');
    }else {
      context.commit('switchLoadState', LOADING_STATE.FAIL);
      context.commit('mutateBankkontenData', []);
      context.commit('updateBankkontenEmptyText', 'Laden fehlgeschlagen');
    }
  },
  async saveBankkonto(context, bankkonto){
    let response = await fetch(CONFIG.host + REST_PATH, {
      method: 'POST',
      body: JSON.stringify(bankkonto),
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      }
    });
    if(response.ok){
      let newBankkonto = await response.json();
      context.commit('addNewBankAccount', bankkonto);
    }

  }
};

export default {
  state: bankkontoState,
  getters,
  mutations,
  actions
};
