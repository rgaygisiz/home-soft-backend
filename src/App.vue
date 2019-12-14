<template>
  <div>
    <personen></personen>
    <br>
    <person></person>
  </div>

</template>

<script>
  import Person from './vue/person/Person'
  import Personen from './vue/person/Personen'

  export default {
    components: {
      Person,
      Personen
    }
  }
</script>

<style lang="scss">

</style>
