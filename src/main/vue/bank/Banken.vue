<template>
  <div class="row">
    <h1>Banken</h1>
    <hr>
    <b-table
      show-empty
      hover
      :items="pagination.items"
      :field="pagination.fields"
      :per-page="pagination.perPage"
      :total-rows="pagination.items.length"
      :current-page="pagination.currentPage">

    </b-table>
  </div>
</template>

<script>
  import Vue from 'vue'
  import VueResource from 'vue-resource'
  Vue.use(VueResource);

  export default {
    data () {
      return {
        pagination: {
          perPage: 5,
          currentPage: 1,
          items: [],
          fields: [
            {
              key: 'name',
              label: 'Bank Name',
              sortable: true
            },
            {
              key: 'bic',
              label: 'BIC',
              sortable: false
            },
            {
              key: 'blz',
              label: 'BLZ',
              sortable: true,
            }
          ],
        }
      }
    },
    beforeCreate() {
      this.$http.get("api/banken")
      .then(
        (payload) => {
          console.log(payload.json());
          let result = payload.json();
          return result;
        },
        (error) => {
          console.log(error)
          let result = payload.json();
          return result;
        }
      ).then((payload) => {
        if(payload.data != undefined){
          console.log(payload);
          this.pagination.items = payload.data;
        }
        console.log(payload)
      });
    }
  }

</script>
