<template>
  <div class="container">
    <h1>Personen</h1>
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
    <b-pagination
      v-model="pagination.currentPage"
      :total-rows="pagination.items.length"
      :per-page="pagination.perPage"
      aria-controls="my-table"
    ></b-pagination>
  </div>
</template>

<script>
  import Vue from 'vue'
  import VueResource from 'vue-resource'
  Vue.use(VueResource);

  export default {
    data ()  {
      return {
        text: "Hello World",
        pagination: {
          perPage: 5,
          currentPage: 1,
          items: [{}
          ],
          fields: [
            {
              key: 'firstName',
              label: 'Vorname',
              sortable: true
            },
            {
              key: 'lastName',
              label: 'Nachname',
              sortable: false
            },
            {
              key: 'birthday',
              label: 'Geburtsdatum',
              sortable: true,
              // Variant applies to the whole column, including the header and footer
              variant: 'danger'
            },
            {
              key: 'Geburtsort',
              label: 'Person age',
              sortable: true,
              // Variant applies to the whole column, including the header and footer
              variant: 'danger'
            }
          ],
        }
      }
    },
    beforeCreate() {
      this.$http.get("api/personen")
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
