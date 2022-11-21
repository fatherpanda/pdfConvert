<template>
  <nav aria-label="Page" class="mt-3">
    <ul class="pagination justify-content-center">
      <li class="page-item" v-if="prevBtn">
        <a class="page-link" @click="clickPage(startPage -1)" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <template v-for="pageInt in showPage" v-if="showPage.length>0">
        <li class="page-item"><a class="page-link" v-bind:class="{'active':page===(pageInt-1)}" @click="clickPage(pageInt)">{{pageInt}}</a></li>

      </template>
     <li class="page-item" v-if="nextBtn">
        <a class="page-link" @click="clickPage(endPage + 1)" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<script>
import {onMounted, ref} from "vue";

export default {
  name: "Pagination",
  props:{
    size:{
      type:Number,
      default:20
    },
    page:{
      type:Number,
      default:0
    },
    pageBtnSize:{
      type:Number,
      default:5
    },
    totalPage:{
      type:Number,
      default: 1
    }
  },
  setup(props, {emit}){
    const nextBtn=ref(false)
    const prevBtn=ref(false)
    const page=ref(0)
    const startPage=ref(0)
    const endPage=ref(0)
    const showPage=ref([])
    const endCount=ref(0)
    const firstCount=ref(0)


    const processPage=(pageInt)=>{

      startPage.value=Math.floor(pageInt / props.pageBtnSize) * props.pageBtnSize +1
      endPage.value=startPage.value + props.pageBtnSize -1
      if (startPage.value <= 0) {
        startPage.value = 1;

      }
      if (startPage.value <= props.pageBtnSize) {
        prevBtn.value = false
      } else {
        prevBtn.value = true
      }
      if(endPage.value>=props.totalPage) {
        endPage.value=props.totalPage
        nextBtn.value=false
      } else {
        nextBtn.value=true
      }

      showPage.value=[]
      for(let i=startPage.value; i<=endPage.value; i++) {
           showPage.value.push(i)
      }
      // endCount.value=props.pageBtnSize
      // firstCount.value=startPage.value+1
      //  sconsole.log(startPage.value, endPage.value, props.totalPage, firstCount.value)
      // if(endPage.value>0 || endPage.value<props.totalPage) {
      //   nextBtn.value=true
      // }
      // while(endCount.value>0 && firstCount.value<=props.totalPage) {
      //   showPage.value.push(firstCount.value++)
      //   endCount.value--
      //
      // }
      // console.log(showPage.value)
    }

    const clickPage=(pageInt)=>
    {
      processPage(pageInt-1)
      emit('change', (pageInt-1))
    }
    onMounted(()=>{
      processPage(page.value)
    })
    return {
      nextBtn,
      prevBtn,
      showPage,
      endCount,
      startPage,
      endPage,
      clickPage,
    }


  }
}
</script>

<style scoped>

</style>