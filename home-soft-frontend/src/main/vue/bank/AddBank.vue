<template>
    <b-container>
      <form action="">
        <b-row>
          <div class="col-xs12 col-sm-12 col-md-12">
            <b-form-group>
              <label for="bankName">Bank Name</label>
              <input
                type="text"
                id="bankName"
                class="form-control"
                v-model="newBank.name">
            </b-form-group>
            <b-form-group>
              <label for="bic">BIC</label>
              <input
                type="text"
                id="bic"
                class="form-control"
                v-model="newBank.bic">
            </b-form-group>
            <b-form-group>
              <label for="blz">BLZ</label>
              <input
                type="text"
                id="blz"
                class="form-control"
                v-model="newBank.blz">
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
  import { mapGetters } from 'vuex';
  Vue.use(VueResource);

  export default {
    data (){
      return {}
    },
    computed: {
      ...mapGetters([
        'newBank'
      ])
    },
    methods:{
      speicherBank(){
        this.$http.post('api/banken',this.bank)
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
