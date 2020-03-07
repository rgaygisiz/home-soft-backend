<template>
  <b-container>
    <b-row>
      <b-col lg="12">
        <b-table
          show-empty
          :empty-text="bankkontenState.emptyText"
          striped
          hover
          :busy="isBankkontenLoading"
          :items="bankkontenState.items"
          :fields="bankkontenPagination.fields"
          :per-page="bankkontenPagination.perPage"
          :total-rows="bankkontenState.items.length"
          :current-page="bankkontenPagination.currentPage">
        </b-table>
      </b-col>
    </b-row>
    <b-row>
      <b-col lg="6">
        <b-pagination
          v-model="bankkontenPagination.currentPage"
          :total-rows="bankkontenState.items.length"
          :per-page="bankkontenPagination.perPage"
          aria-controls="may-Table">
        </b-pagination>
      </b-col>
      <b-col lg="6">
        <b-button v-b-modal.add-bankkonto>Bankkonto erfassen</b-button>
      </b-col>
    </b-row>
    <b-modal
      id="add-bankkonto"
      title="Bankkonto eingabe"
      size="md"
      ok-title="Speichern"
      cancel-title="Abbrechen"
      @ok="saveBankkonto(newBankkonto)"
      @show="resetNewBankkonto"
      @hidden="resetNewBankkonto">
      <add-bankkonto :useData="newBankkonto"></add-bankkonto>
    </b-modal>
  </b-container>
</template>

<script>
  import { mapGetters,mapActions } from 'vuex';
  import AddBankkonto from './AddBankkonto';

  export default {
    name: "Bankkonten",
    components: {AddBankkonto},
    component: {
      AddBankkonto
    },
    data() {
      return {
        newBankkonto:{
        },
        bankkontenPagination: {
          perPage: 5,
          currentPage: 1,
          fields: [
            {
              key: 'iban',
              label: 'Kontonummer',
              sortable: true
            },
            {
              key: 'bank.name',
              label: 'Bank Name',
              sortable: true
            },
            {
              key: 'besitzer.fullName',
              label: 'Person',
              sortable: true
            }
          ]
        }
      };
    },
    computed:{
      ...mapGetters([
          'bankkontenState',
          'isBankkontenLoading'
      ])
    },
    methods:{
      ...mapActions([
        'saveBankkonto'
      ]),
      resetNewBankkonto(){
        this.newBankkonto = {};
      }
    },
    beforeCreate() {
      this.$store.dispatch('loadBankkonten');
    }
  }
</script>

<style scoped>

</style>
