import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const loadState = Object.freeze({
  UNUSED:       Symbol("unused"),
  LOADED:       Symbol("loaded"),
  TO_REFRESH:   Symbol("toRedresh"),
  DISCARDED:    Symbol("discarded"),
  LOADING:      Symbol("loading"),
  FAIL:         Symbol("fail")
});


export const store = new Vuex.Store({
  debug: true,
  state: {
    personen: {
      loadState: loadState.UNUSED,
      items: [],
      emptyText: 'Leere'
    },
    personEditor: {
      person: {},
      readable: false
    }
  },
  getters: {
    person(state) {
      return state.personEditor.person;
    },
    personen(state){
      return state.personen;
    },
    isPersonenLoading(state) {
      return state.personen.loadState === loadState.LOADING;
    }
  },
  mutations: {
    personenLoadState(state, loadingState) {
      if( loadingState ){
        state.personen.loadState = loadingState;
      } else {
        state.personen.loadState = loadState.FAIL;
      }
    },
    personenUpdate(state, personen) {
      if(Array.isArray(personen)){
        state.personen.items = personen;
      }
    },
    personenEmptyText(state,successfullLoaded){
      if(successfullLoaded){
        state.personen.emptyText = 'Leer';
      } else {
        state.personen.emptyText = 'Laden fehlgeschlagen'
      }
    },
    addNewPerson(state, person){
      if(person){
        state.personen.items.push(person);
      }
    }
  },
  actions: {
    loadPersonen (context) {
      context.commit('personenLoadState', loadState.LOADING);
      Vue.http.get("api/personen")
      .then(
        (payload) => {
          return payload.json();
        },
        (error) => {
          return payload.json();
        }
      ).then(
        (payload) => {
        if(Array.isArray(payload.data)){
          context.commit('personenUpdate', payload.data);
          context.commit('personenLoadState', loadState.LOADED);
          context.commit('personenEmptyText', 'Leer')
        } else{
          context.commit('personenUpdate', []);
          context.commit('personenLoadState', loadState.FAIL);
          context.commit('personenEmptyText', 'Fehlerhaftes laden');
        }
      })
    },
    savePerson(context){
      Vue.http.post('api/personen',context.getters.person)
      .then(
        (payload) => {
          console.log("Success save call:", payload);
          return payload.json();
        },
        (payload) => {
          console.log("Faild Call", payload)
        }
      )
      .then((payload) => {
        context.commit('addNewPerson', payload.data);
      })
    }
  }
});

