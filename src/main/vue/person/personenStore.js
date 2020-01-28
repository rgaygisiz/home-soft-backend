import {LOADING_STATE} from "../core/store/loadingState";
import Vue from "vue";


const personState = {
  personenState: {
    loadingState: LOADING_STATE.UNUSED,
    items: [],
    newPerson: {},
    emptyText: 'Leere'
  }
};


const getters = {
  personenState(state) {
    return state.personenState;
  },
  newPerson(state) {
    return state.personenState.newPerson;
  },
  isPersonenLoading(state) {
    return state.personenState.loadingState === LOADING_STATE.LOADING;
  }
};

const mutations= {
  setLoading(state, loadingState) {
    if( loadingState ){
      state.personenState.loadingState = loadingState;
    } else {
      state.personenState.loadingState = LOADING_STATE.FAIL;
    }
  },
  setPersonen(state, personen) {
    if(Array.isArray(personen)){
      state.personenState.items = personen;
    }
  },
  updateEmptyText(state, successfullLoaded){
    if(successfullLoaded){
      state.personenState.emptyText = 'Leer';
    } else {
      state.personenState.emptyText = 'Laden fehlgeschlagen';
    }
  },
  addNewPerson(state, person){
    if(person){
      state.personenState.items.push(person);
    }
  },
  resetNewPrerson(state){
    state.personenState.newPerson = {};
  }
};

const actions = {
  loadPersonen (context) {
    context.commit('setLoading', LOADING_STATE.LOADING);
    Vue.http.get("api/personen")
    .then(
      payload => payload.json(),
      error => error.json())
    .then(
      (payload) => {
        if(Array.isArray(payload.data)){
          context.commit('setPersonen', payload.data);
          context.commit('setLoading', LOADING_STATE.LOADED);
          context.commit('updateEmptyText', 'Leer')
        } else{
          context.commit('setPersonen', []);
          context.commit('setLoading', LOADING_STATE.FAIL);
          context.commit('updateEmptyText', 'Fehlerhaftes laden');
        }
      })
  },
  savePerson(context){
    Vue.http.post('api/personen', context.getters.newPerson)
    .then( response => response.json(), console.log)
    .then((payload) => {
      context.commit('addNewPerson', payload.data);
    })
  },
    deletePerson(context, ids){
    if( ids ){
      Vue.http.delete('/api/personen/' + ids)
        .then(response => response.json(), console.log)
        .then(value => context.dispatch('loadPersonen'));
    }
  }
};

export default {
  state: personState,
  getters,
  mutations,
  actions
};
