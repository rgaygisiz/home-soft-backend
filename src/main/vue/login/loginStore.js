// import {LOADING_STATE} from "../core/store/loadingState";
import Vue from "vue";


const state = {
  session: {
    token: undefined,
  }
};


const getters = {
  getToken: state => {
    return state.session.token;
  }
};

const mutations= {
  setToken(state, jwt){
    state.session.token = jwt;
  }
};

const actions = {
  login(context,login){
    Vue.http.post('/api/authentification', login)
    .then( response => response.json(), console.log)
    .then((payload) => {
      if( payload && payload.jwt ){
        context.commit('setToken', payload.jwt);
        Vue.http.interceptors.push((request, next) => {
          if(context.getters.getToken){
            request.headers.set('Authorization', 'Bearer ' + context.getters.getToken)
          }
          request.headers.set('Accept', 'application/json')
        })
      }
    })
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
