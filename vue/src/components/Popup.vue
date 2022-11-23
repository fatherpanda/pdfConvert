<template>
  <div class="popup">
    <div class="popup-header">
      <div class="row h-100 justify-content-between align-items-center">
        <div class="col-auto">{{ $props.title }}</div>
        <div class="col-auto">
          <button class="btn-close" @click="$emit('update:isOpen', false)"></button>
        </div>
      </div>
    </div>
    <div class="container">
      
      <slot></slot>
    </div>
    <div class="popup-footer">
      
    </div>
  </div>
</template>

<script>
export default {
  name: "Popup",
  props:{
    title:{
      type:String,
      default:''
    }
  }
}
</script>

<style scoped>

</style>