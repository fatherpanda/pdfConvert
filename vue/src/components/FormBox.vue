<template>
  <div class="row form-row">
    <template v-for="row in $props.writeField" :key="row">
      <div class="col-2 col-form-label">{{row.headerName}}</div>
      <div class="col-4 col-form-label">
        <template v-if="row.type!=='radio'">
          <input-vue :row="row" :data="data"/>
        </template>
        <template v-else>
          <div class="form-check-inline" v-for="(rowData,i) in row.data" :key="rowData">
            <input class="form-check-input" type="radio" :id="row.field+'_'+i" v-bind:value="rowData.val" @click="$props.data[row.field]=rowData.val" v-bind:checked="rowData.val==$props.data[row.field]">
            <label class="form-check-label" :for="row.field+'_'+i">
              {{ rowData.name }}
            </label>
          </div>

        </template>

      </div>
    </template>

  </div>
</template>

<script>
import { ref } from 'vue-demi'
import InputVue from './Input.vue'

export default {
  name: "FormBox",
  components:{
    InputVue
  },
  props:{
    writeField:{
      type:Object,
    },
    data:{
      type:Object
    },
  },
  setup (props) {
    const data=ref(props.data)
    const getData=(row)=> {

      let column = data.value
      let field = row.field
      if (field.indexOf(".") > -1) {

        let key = field.substring(0, field.indexOf("."))
        field = field.substring(field.indexOf(".") + 1, field.length)

        column = column[key]
      }
      return {
        data: column[field], row: row
      }
    }

    return {
      getData,

    }
  }

}
</script>

<style scoped>

</style>