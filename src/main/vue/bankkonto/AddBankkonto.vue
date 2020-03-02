<template>
  <b-container>
    <h1>Add Bankkonto</h1>
    <form action="">
      <b-row>
        <div class="col-xs12 col-sm-12 col-md-12">
          <b-form-group>
            <label for="bankkontoIban">IBAN</label>
            <input
              autocomplete="off"
              type="text"
              id="bankkontoIban"
              class="form-control"
              v-model="bankkonto.iban">
          </b-form-group>
          <b-form-group>
            <label for="bankkontoBic">BIC</label>
            <b-input
              autocomplete="off"
              id="bankkontoBic"
              v-model="bankkonto.bicAuswahl"
              required
              list="bankenListe"
              type="text"
              size="sm"
              @input="bicInput"
              @change="bicChange">
            </b-input>
            <b-datalist
              id="bankenListe"
              :options="loadBanken"
              value-field="name"
              text-field="bic">
            </b-datalist>
          </b-form-group>
          <b-form-group>
            <label for="bankkontoBesitzer">Besitzer</label>
            <b-input
              autocomplete="off"
              id="bankkontoBesitzer"
              v-model="bankkonto.partnerAuswahl"
              required
              list="personenList"
              type="text"
              size="sm"
              @input="personInput"
              @change="personChange">
            </b-input>
            <b-form-datalist
              id="personenList"
              :options="loadPersonen"
              value="fullNmae"
              text-field="fullName">
            </b-form-datalist>
          </b-form-group>
        </div>
      </b-row>
    </form>
  </b-container>
</template>

<script>
  import { CONFIG } from '../config';

  export default {
    name: "AddBankkonto",
    data(){
      return {
        newBankkonto: {},
        bankenToSelect:[],
        personenToSelect:[]
      }
    },
    computed: {
      bankkonto(){
        return  this.useData ? this.useData : this.newBankkonto;
      },
      loadBanken(){
        return this.bankenToSelect;
      },
      loadPersonen(){
        return this.personenToSelect;
      }
    },
    props: ['useData'],
    methods:{
      bicChange(value){
        this.bankkonto.bank = this.bankenToSelect.find(bank => bank.name === value);
      },
      bicInput(value){
        if(this.bankkonto.bicAuswahl && this.bankkonto.bicAuswahl.length >= 3){
          this.bicLength = this.bankkonto.bicAuswahl.length;
          setTimeout( async () => {
            if(this.bicLength == this.bankkonto.bicAuswahl.length ){
              let response = await fetch(CONFIG.host + '/api/banken?bicFragment=' + this.bankkonto.bicAuswahl);
              if(response.ok){
                let data = await response.json();
                this.bankenToSelect = data.data;
              }else {
                return [];
              }open
            }
          },750);
        }else {
          this.bankenToSelect = [];
        }
      },
      personChange(value){
        this.bankkonto.besitzer  = this.personenToSelect.find(person => person.fullName === value);
      },
      personInput(value){
        if ( this.bankkonto.partnerAuswahl && this.bankkonto.partnerAuswahl.length >= 3 ){
          this.partnerAuswahlLength = this.bankkonto.partnerAuswahl.length;
          setTimeout(async () => {
            if(this.partnerAuswahlLength == this.bankkonto.partnerAuswahl.length){
              let response = await fetch(CONFIG.host + '/api/personen?personenFragment=' + this.bankkonto.partnerAuswahl);
              if(response.ok){
                let data = await response.json();
                data.data.forEach(person => person.fullName = person.lastName+", " +  person.firstName);
                this.personenToSelect = data.data;
              }else {
                return [];
              }
            }
          },750);
        }else{
          return [];
        }
      }
    }
  }
</script>

<style >

</style>
