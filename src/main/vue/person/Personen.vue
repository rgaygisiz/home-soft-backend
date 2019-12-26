<template>
  <div class="row">
    <h1>Personen</h1>
    <hr>
    <b-table
      show-empty
      :empty-text="personen.emptyText"
      striped
      hover
      :busy="isPersonenLoading"
      :items="personen.items"
      :fields="pagination.fields"
      :per-page="pagination.perPage"
      :total-rows="personen.items.length"
      :current-page="pagination.currentPage">
      <template v-slot:table-busy>
        <div class="text-center text-danger my-2">
          <b-spinner class="align-middle"></b-spinner>
          <strong> Lade...</strong>
        </div>
      </template>

      <template v-slot:cell(actions)="row">
        <b-button size="sm" @click="info(row.item, row.index, $event.target)" class="mr-1">
          Info modal
        </b-button>
        <b-button size="sm" @click="row.toggleDetails">
          {{ row.detailsShowing ? 'Hide' : 'Show' }} Details
        </b-button>
      </template>
    </b-table>
    <b-pagination
      v-model="pagination.currentPage"
      :total-rows="personen.items.length"
      :per-page="pagination.perPage"
      aria-controls="my-table"
    ></b-pagination>
  </div>
</template>

<script>
  import { loadState } from '../core/store/store';
  import { mapGetters } from 'vuex';

  export default {
    data ()  {
      return {
        text: "Hello World",
        pagination: {
          perPage: 5,
          currentPage: 1,
          fields: [
            {
              key: 'firstName',
              label: 'Vorname',
              sortable: true
            },
            {
              key: 'lastName',
              label: 'Nachname',
              sortable: true
            },
            {
              key: 'birthday',
              label: 'Geburtsdatum',
              sortable: false
            },
            {
              key: 'birthplace',
              label: 'Geburtsort',
              sortable: false
            },
            { key: 'actions', label: 'Aktions' }
          ],
        }
      }
    },
    computed: {
      ...mapGetters([
        'personen',
        'isPersonenLoading'
      ])
    }
  }
</script>
