<template>
    <div >
        <personen></personen>
        <br>
        <person-editor></person-editor>
    </div>
</template>


<script>
  import Personen from './Personen';
  import PersonEditor from './PersonEditor';


  export default {
   components:{
     Personen,
     PersonEditor
   }
  }

</script>
