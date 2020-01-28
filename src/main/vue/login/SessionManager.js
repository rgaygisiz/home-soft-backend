import {store} from '../core/store/store';
import Vue from 'vue'

export default {
  hasToken(){
    let token = store.getters.getToken;
    return token !== undefined;
  }
};
