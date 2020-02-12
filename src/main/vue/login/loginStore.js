// import {LOADING_STATE} from "../core/store/loadingState";
import Vue from "vue";
import { logingView, homeView} from './../core/routing/routes';

const state = {
  session: {
    token: undefined,
    loginLabel: 'Login'
  }
};


const getters = {
  isLogin: state => {
    return typeof state.session.token !== 'undefined';
  },
  getToken: state => {
    return state.session.token;
  },
  getLoginLabel: state => {
    return state.session.loginLabel;
  }
};

const mutations= {
  mutateLogin(state, jwt){
    state.session.token = jwt;
    state.session.loginLabel = 'Logout';
  },
  mutateLogout(state){
    delete state.session.token;
    state.session.loginLabel = 'Login';
  }
};

const actions = {
  login(context,login){
    Vue.http.post('/api/authentification', login)
    .then( response => response.json(), console.log)
    .then((payload) => {
      if( payload && payload.jwt ){
        context.commit('mutateLogin', payload.jwt);
        if(Vue.router.currentRoute.query.returnTo){
          Vue.router.push(Vue.router.currentRoute.query.returnTo);
        }else{
          Vue.router.push(homeView);
        }
      }
    })
  },
  logout(context){
    context.commit('mutateLogout');
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
