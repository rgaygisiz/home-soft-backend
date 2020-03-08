<template>
  <b-container>
    <b-row>
      <b-col lg="12">
        <b-table
          show-empty
          :empty-text="bankenState.emptyText"
          striped
          hover
          :busy="isBankenLoading"
          :items="bankenState.items"
          :fields="bankenPagination.fields"
          :per-page="bankenPagination.perPage"
          :total-rows="bankenState.items.length"
          :current-page="bankenPagination.currentPage">
          <template v-slot:table-busy>
            <div class="text-center text-danger my-2">
              <b-spinner class="align-middle"></b-spinner>
              <strong> Lade...</strong>
            </div>
          </template>
          <template v-slot:cell(actions)="row">
            <b-button size="sm" @click="deleteBank(row.item, row.index, $event.target)" class="mr-1">Löschen</b-button>
          </template>
        </b-table>
      </b-col>
    </b-row>
    <b-row>
      <b-col lg="6">
        <b-pagination
          v-model="bankenPagination.currentPage"
          :total-rows="bankenState.items.length"
          :per-page="bankenPagination.perPage"
          aria-controls="my-table"></b-pagination>
      </b-col>
      <b-col lg="6">
        <b-button v-b-modal.add-bank>Bank erfassen</b-button>
      </b-col>
    </b-row>
    <b-modal
      id="add-bank"
      title="Bank eingabe"
      size="md"
      ok-title="Speichern"
      cancel-title="Abbrechen"
      @ok="saveBank"
      @show="resetNewBank"
      @hidden="resetNewBank">
      <add-bank></add-bank>
    </b-modal>
  </b-container>
</template>

<script>
  import {mapGetters, mapActions, mapMutations} from 'vuex';
  import Vue from 'vue'
  import VueResource from 'vue-resource'
  import AddBank from './AddBank';

  Vue.use(VueResource);

  export default {
    components: {
      AddBank
    },
    data() {
      return {
        bankenPagination: {
          perPage: 5,
          currentPage: 1,
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
            },
            { key: 'actions', label: 'Aktions' }
          ]
        }
      }
    },
    computed: {
      ...mapGetters([
        'bankenState',
        'isBankenLoading'
      ])
    },
    methods:{
      ...mapMutations([
        'resetNewBank'
        ]),
      ...mapActions([
        'saveBank',
        'loadBanken'
        ]),
      deleteBank(item, index, event){
        this.$store.dispatch("deletePerson", item.id);
      }
    },
    beforeCreate() {
      this.$store.dispatch('loadBanken');
    }
  }

</script>
