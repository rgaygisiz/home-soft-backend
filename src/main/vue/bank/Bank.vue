<template>
  <div>
    <banken></banken>
    <br>
    <bank-editor></bank-editor>
  </div>
</template>


<script>
  import Banken from './Banken';
  import BankEditor from './BankEditor';

  export default {
    components: {
      Banken,
      BankEditor
    }
  }
</script>
