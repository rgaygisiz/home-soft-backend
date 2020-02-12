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
  export default {
    data() {
      return {
        user: {}
      }
    },
    methods:{
      onLogin(){
        this.$store.dispatch('login',this.user);
      }
    }
  }

</script>
