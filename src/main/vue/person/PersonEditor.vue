<template>
  <div class="container">
    <h1>Person Editor</h1>
    <form action="">
      <div class="row">
        <div class="col-xs12 col-sm8 col-sm-offset-2 col-md-6 col-md-offset-3">
          <div class="form-group">
            <label for="firstName">Vorname</label>
            <input
              type="text"
              id="firstName"
              class="form-control"
              v-model="person.firstName">
          </div>
          <div class="form-group">
            <label for="lastName">Nachname</label>
            <input
              type="text"
              id="lastName"
              class="form-control"
              v-model="person.lastName">
          </div>
          <div class="form-group">
            <label for="birthday">Geburtsdatum</label>
            <datepicker
              id="birthday"
              placeholder="Bitte wählen"
              :language="language"
              format="dd.MM.yyyy"
              input-class="form-control"
              :bootstrap-styling="true"
              v-model="person.birthday"></datepicker>
          </div>
          <div class="form-group">
            <label for="birtplace">Geburtsort</label>
            <input
              type="text"
              id="birtplace"
              class="form-control"
              v-model="person.birthplace">
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs12 col-sm8 col-sm-offset-2 col-md-6 col-md-offset-3">
          <button
            class="btn btn-primary"
            @click.prevent="savePerson">Speichern</button>
        </div>
      </div>
    </form>
  </div>
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
        'person'
      ]),
    },
    methods:{
      ...mapActions([
        'savePerson'
      ])
    }
  }
</script>
