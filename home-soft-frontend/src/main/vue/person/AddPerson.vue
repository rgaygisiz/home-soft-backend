<template>
  <b-container>
    <form action="">
      <b-row>
        <div class="col-xs12 col-sm-12 col-md-13">
          <b-form-group>
            <label for="firstName">Vorname</label>
            <input
              type="text"
              id="firstName"
              class="form-control"
              v-model="newPerson.firstName">
          </b-form-group>
          <b-form-group>
            <label for="lastName">Nachname</label>
            <input
              type="text"
              id="lastName"
              class="form-control"
              v-model="newPerson.lastName">
          </b-form-group>
          <b-form-group>
            <label for="birthday">Geburtsdatum</label>
            <b-form-input
              id="birthday"
              v-model="newPerson.birthday"
              required
              type="date">
            </b-form-input>
          </b-form-group>
          <b-form-group>
            <label for="birtplace">Geburtsort</label>
            <input
              type="text"
              id="birtplace"
              class="form-control"
              v-model="newPerson.birthplace">
          </b-form-group>
        </div>
      </b-row>
    </form>
  </b-container>
</template>

<script>
  //components
  import Vue from 'vue'
  import VueResource from 'vue-resource'
  import { mapGetters, mapActions } from 'vuex';

  Vue.use(VueResource);

  //ui-components
  import Datepicker from 'vuejs-datepicker'
  Vue.component('datepicker', Datepicker);

  import {de} from 'vuejs-datepicker/dist/locale'

  export default {
    data () {
      return {
        language: de
      }
    },
    computed: {
      ...mapGetters([
        'newPerson'
      ]),
    },
    methods:{
      ...mapActions([
        'savePerson'
      ])
    }
  }
</script>
