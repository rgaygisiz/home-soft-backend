<template>
  <div class="container">
    <h1>
      Person Editor
    </h1>
    <form action="">
      <hr>
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
            @click.prevent="speicherPerson">Speichern</button>
        </div>
      </div>
    </form>
  <hr>
  <div class="row">
    <div class="col-xs12 col-sm8 col-sm-offset-2 col-md-6 col-md-offset-3">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4>Your Data</h4>
        </div>
        <div class="panel-body">
          <p>Vorname: {{ person.firstName }}</p>
          <p>Nachname: {{ person.lastName }}</p>
          <p>Geburtdatum: {{ person.birthday }}</p>
          <p>Geburtsort: {{ person.birthplace }}</p>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
  import 'bootstrap-scss/bootstrap.scss'
  import 'bootstrap-scss/bootstrap-grid.scss'
  import {de} from 'vuejs-datepicker/dist/locale'

  export default {
    data () {
      return {
        language: de,
        person: {
        }
      }
    },
    methods:{
      speicherPerson(){
        console.log(this.person);
        this.$http.post('http://localhost:8081/personen',this.person)
        .then(
          (a) => {
            console.log(a)
          },
          (b) => {
            console.log(b)
          }
        )
      }
    }
  }
</script>

<style lang="scss">
  /*!* Shrinking the sidebar from 250px to 80px and center aligining its content*!*/
  /*#sidebar.active {*/
  /*  min-width: 80px;*/
  /*  max-width: 80px;*/
  /*  text-align: center;*/
  /*}*/

  /*!* Toggling the sidebar header content, hide the big heading [h3] and showing the small heading [strong] and vice versa*!*/
  /*#sidebar .sidebar-header strong {*/
  /*  display: none;*/
  /*}*/
  /*#sidebar.active .sidebar-header h3 {*/
  /*  display: none;*/
  /*}*/
  /*#sidebar.active .sidebar-header strong {*/
  /*  display: block;*/
  /*}*/

  /*#sidebar ul li a {*/
  /*  text-align: left;*/
  /*}*/

  /*#sidebar.active ul li a {*/
  /*  padding: 20px 10px;*/
  /*  text-align: center;*/
  /*  font-size: 0.85em;*/
  /*}*/

  /*#sidebar.active ul li a i {*/
  /*  margin-right:  0;*/
  /*  display: block;*/
  /*  font-size: 1.8em;*/
  /*  margin-bottom: 5px;*/
  /*}*/

  /*!* Same dropdown links padding*!*/
  /*#sidebar.active ul ul a {*/
  /*  padding: 10px !important;*/
  /*}*/

  /*!* Changing the arrow position to bottom center position,*/
  /*   translateX(50%) works with right: 50%*/
  /*   to accurately  center the arrow *!*/
  /*#sidebar.active .dropdown-toggle::after {*/
  /*  top: auto;*/
  /*  bottom: 10px;*/
  /*  right: 50%;*/
  /*  -webkit-transform: translateX(50%);*/
  /*  -ms-transform: translateX(50%);*/
  /*  transform: translateX(50%);*/
  /*}*/
</style>
