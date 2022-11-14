<template>
  <div class="row">
    <template v-for="row in $props.writeField" :key="row">
      <div class="col-2 col-form-label">{{row.headerName}}</div>
      <div class="col-4 col-form-label">
        <template v-if="row.type!=='radio'">
          <input v-bind:type="row.type" class="form-control" v-model="$props.data[row.field]" v-bind:required="row.required">
        </template>
        <template v-else>
          <div class="form-check-inline" v-for="(rowData,i) in row.data" :key="rowData">
            <input class="form-check-input" type="radio" :id="row.field+'_'+i" v-bind:value="rowData.val" @click="$props.data[row.field]=rowData.val">
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
export default {
  name: "FormBox",
  props:{
    writeField:{
      type:Object,
    },
    data:{
      type:Object
    }
  }
}
</script>

<style scoped>

</style>