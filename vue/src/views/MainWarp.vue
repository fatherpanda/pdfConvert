<template>
  <div class="row min-vh-100">
    <div class="col-auto side-menu">
      <side-menu class="p-3"></side-menu>
    </div>
    <div class="col">
      <router-view class="p-3"></router-view>
    </div>
  </div>


</template>

<script>
import SideMenu from "/@components/SideMenu.vue";
export default {
  name: "MainWarp",
  components:{SideMenu},
  setup() {

  }
}
</script>

<style scoped>
.side-menu {
  width:200px;
  background-color: rgba(0,0,0,.3);
}
</style>