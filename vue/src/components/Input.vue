<template>

  <input v-bind:type="row.type" class="form-control form-control-sm" :value="getData()" @input="updateData"
    v-bind:required="row.required">
</template>
<script>
import { ref } from 'vue-demi'
export default {
  props: {
    row: {
      type: Object,
      default: {
        type: '',
        field: ''
      }
    },
    data:{
      type:Object,
      default: {
      }
    }
  },
  setup(props, {emit}) {
    const data = ref(props.data)
    const row = ref(props.row)
    
    const findField=()=> {
      let column = data.value
      let field = row.value.field
      let key =""
      if (field.indexOf(".") > -1) {

        key = field.substring(0, field.indexOf("."))
        field = field.substring(field.indexOf(".") + 1, field.length)

        column = column[key]
      }

      return column
    }
    const getData = () => {
      const column = findField()
      const field = row.value.field
      return column[field]
    }
    const updateData = (e) => {
      const column = findField()
      const field = row.value.field
      column[field] = e.target.value
      emit('update:data',data)
    }
    return {
      row,
      data,

      getData,
      updateData
    }
  },
}
</script>