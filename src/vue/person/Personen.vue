<template>
  <div class="container">
    <p>{{text}}</p>
    <b-table striped hover
             :items="pagination.items"
             :field="pagination.fields"
             :per-page="pagination.perPage"
             :total-rows="pagination.items.length"
             :current-page="pagination.currentPage"></b-table>
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
    created() {
      this.$http.get("api/personen")
      .then(
        (payload) => {
          console.log(payload.json());
          return payload.json();
        },
        (error) => {
          console.log(error)
        }
      ).then((payload) => {
        console.log(payload)
        this.pagination.items = payload.data;
      });
    }
  }
</script>
