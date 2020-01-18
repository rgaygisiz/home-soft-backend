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
          :fields="personenPagination.fields"
          :per-page="personenPagination.perPage"
          :total-rows="personenState.items.length"
          :current-page="personenPagination.currentPage">
          <template v-slot:table-busy>
            <div class="text-center text-danger my-2">
              <b-spinner class="align-middle"></b-spinner>
              <strong> Lade...</strong>
            </div>
          </template>
          <template v-slot:cell(actions)="row">
            <b-button size="sm" @click="deletePerson(row.item, row.index, $event.target)" class="mr-1">Löschen</b-button>
          </template>
        </b-table>
      </b-col>
    </b-row>
    <b-row>
      <b-col lg="6">
        <b-pagination
          v-model="personenPagination.currentPage"
          :total-rows="personenState.items.length"
          :per-page="personenPagination.perPage"
          aria-controls="my-table"></b-pagination>
      </b-col>
      <b-col lg="6">
        <b-button v-b-modal.add-person>Person Erfassen</b-button>
      </b-col>
    </b-row>
    <b-modal
      id="add-person"
      title="Person eingabe"
      size="lg"
      ok-title="Speichern"
      cancel-title="Abbrechen"
      @ok="savePerson"
      @show="resetNewPrerson"
      @hidden="resetNewPrerson">
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
        personenPagination: {
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
        'personenState',
        'isPersonenLoading'
      ])
    },
    methods:{
      ...mapMutations([
        'resetNewPrerson'
      ]),
      ...mapActions([
        'savePerson'
      ]),
      deletePerson(item, index, event){
       this.$store.dispatch("deletePerson", item.id);
      }
    }
  }
</script>
