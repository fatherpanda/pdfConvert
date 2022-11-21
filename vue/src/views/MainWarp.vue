<template>
  <div class="row min-vh-100 g-0">
    <div class="col-auto side-menu">
      <div class="logo-container">
            <router-link to="/"><div class="logo"></div></router-link>
        
      </div>
      <side-menu class="p-0"></side-menu>
    </div>
    <div class="col">
      <div class="header">
        <div class="header-title"><i class="fa fa-tachometer" aria-hidden="true" style="color:orange"></i> 한국교통안전공단 사고정보 데이터관리 시스템</div>
      </div>
      <router-view style="padding: 0;margin:0"></router-view>
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
  width:230px;
  background-color: var(--soft-blue);
}
</style>