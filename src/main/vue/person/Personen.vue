<template>
  <b-container>
    <b-row>
      <b-col lg="12">
        <b-table
          show-empty
          :empty-text="personenState.emptyText"
          striped
          hover
          :busy="isPersonenLoading"
          :items="personenState.items"
          :fields="pagination.fields"
          :per-page="pagination.perPage"
          :total-rows="personenState.items.length"
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
      </b-col>
    </b-row>
    <b-row>
      <b-col lg="6">
        <b-pagination
          v-model="pagination.currentPage"
          :total-rows="personenState.items.length"
          :per-page="pagination.perPage"
          aria-controls="my-table"></b-pagination>
      </b-col>
      <b-col lg="6">
        <b-button v-b-modal.modal-1>New Person</b-button>
      </b-col>
    </b-row>
    <b-modal
      id="modal-1"
      title="Person eingabe"
      size="lg"
      ok-title="Speichern"
      cancel-title="Abbrechen"
      @ok="savePerson"
      @show="resetModal"
      @hidden="resetModal">

      <add-person></add-person>
    </b-modal>
     </b-container>
</template>

<script>
  import { mapGetters, mapActions, mapMutations } from 'vuex';
  import AddPerson from './AddPerson';

  export default {
    components:{
      AddPerson
    },
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
        'newPerson',
        'personenState',
        'isPersonenLoading'
      ])
    },
    methods:{
      ...mapMutations([
        'resetModal'
      ]),
      ...mapActions([
        'savePerson'
      ])
    }
  }
</script>
