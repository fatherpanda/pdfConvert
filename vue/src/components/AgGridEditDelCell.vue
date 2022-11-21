<template>
  <div>
    <button class="btn btn-outline-secondary btn-sm" @click="editClick">
      수정
    </button>&nbsp;
    <button class="btn btn-outline-secondary btn-sm"  @click="delClick">
      삭제
    </button>
  </div>

</template>

<script>
export default {
  name: "AgGridEditDelCell",
  setup(props){
    const data=props.params.data
    const  editClick=()=>{
      props.params.edit(data)
    }
    const delClick=()=>{
      props.params.del(data)
    }
    return {
      editClick,
      delClick,
    }
  }
}
</script>

<style scoped>

</style>