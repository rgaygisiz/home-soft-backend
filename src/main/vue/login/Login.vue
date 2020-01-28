<template>
  <b-container>
    <h1>Login</h1>
    <b-row>
      <b-col>
        <b-form v-on:submit.prevent="onLogin">
          <b-form-group
            label="Benutzer oder Email"
            label-for="accountId">
            <b-form-input
              id="accountId"
              v-model="user.username"
              required
              type="text">
            </b-form-input>
          </b-form-group>
          <b-form-group
            label="Password"
            label-for="password">
            <b-form-input
              id="password"
              type="password"
              required
              v-model="user.password">
            </b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">Login</b-button>
 <!--         <b-button type="reset" variant="danger">Reset</b-button>-->
        </b-form>
      </b-col>
    </b-row>
    <b-row>

    </b-row>
  </b-container>
</template>


<script>
  import {mapMutations, mapGetters} from 'vuex';

  export default {
    data() {
      return {
        user: {}
      }
    },
    methods:{
      ...mapGetters(['getToken']),
      ...mapMutations(['setToken']),
      onLogin(){
        this.$http.post('/api/authentification', this.user)
        .then( response => response.json(), console.log)
        .then((payload) => {
          if( payload && payload.jwt ){
            this.setToken(payload.jwt);
            if(this.$route.query.returnTo){
              this.$router.push(this.$route.query.returnTo);
            }
          }
        })
      }
    }
  }

</script>
