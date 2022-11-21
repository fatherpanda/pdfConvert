<template>
<ul class="side-menu">
  <li>
    <router-link to="/aa01">표준코드</router-link>
  </li>
  <li>
    <router-link to="/aa02">표준코드상세</router-link>

  </li>
  <li>
    <router-link to="/aa04">에러로그</router-link>

  </li>
  <li>
    <router-link to="/ba02">EDR Rule</router-link>

  </li>
  <li>
    <router-link to="/ba04">DSSAD Rule</router-link>

  </li>
  <li>
    <router-link to="/da01">DSSAD </router-link>

  </li>
    <li>
      <router-link to="/da02">DSSAD 상세</router-link>
    
    </li>
<!--  <li>-->
<!--    <router-link to="/da01Write">DSSAD 등록</router-link>-->

<!--  </li>-->
</ul>
</template>

<script>
export default {
  name: "SideMenu"
}
</script>

<style scoped>

</style>