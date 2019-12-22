<template>
    <div class="container">
      <h1>Banking</h1>
      <form action="">
        <div class="row">
          <div class="col-xs12 col-sm8 col-sm-offset-2 col-md-6 col-md-offset-3">
            <div class="form-group">
              <label for="bankName">Bank Name</label>
              <input
                type="text"
                id="bankName"
                class="form-control"
                v-model="bank.name">
            </div>
            <div class="form-group">
              <label for="bic">BIC</label>
              <input
                type="text"
                id="bic"
                class="form-control"
                v-model="bank.bic">
            </div>
            <div class="form-group">
              <label for="blz">BLZ</label>
              <input
                type="text"
                id="blz"
                class="form-control"
                v-model="bank.blz">
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-xs12 col-sm8 col-sm-offset-2 col-md-6 col-md-offset-3">
            <button
              class="btn btn-primary"
              @click.prevent="speicherBank">Speichern</button>
          </div>
        </div>

      </form>
    </div>
</template>

<script>
  //components
  import Vue from 'vue'
  import VueResource from 'vue-resource'
  Vue.use(VueResource);

  export default {
    data (){
      return {
        bank: {}
      }
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
