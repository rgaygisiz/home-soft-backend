<template>
  <div>
    <h1>Personen</h1>
    <hr>
    <personen></personen>
  </div>
</template>


<script>
  import Personen from './Personen';

  export default {
    components: {
      Personen
    },
    beforeCreate() {
      this.$store.dispatch('loadPersonen');
    }
  }

</script>
