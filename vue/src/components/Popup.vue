<template>
  <div class="popup">
    <div class="container">
      <button class="btn-close" @click="$emit('update:isOpen', false)"></button>
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "Popup"
}
</script>

<style scoped>

</style>