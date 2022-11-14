<template>
  <nav aria-label="Page" class="mt-3">
    <ul class="pagination justify-content-center">
      <li class="page-item">
        <a class="page-link" href="#" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <template v-for="pageInt in showPage" v-if="showPage.length>0">
        <li class="page-item"><a class="page-link" v-bind:class="{'active':page===(pageInt-1)}" @click="clickPage(pageInt)">{{pageInt}}</a></li>

      </template>
     <li class="page-item" v-if="nextBtn">
        <a class="page-link" @click="clickPage(endPage)" aria-label="Next">
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
      default:3
    },
    totalPage:{
      type:Number,
      default: 1
    }
  },
  setup(props, {emit}){
    const nextBtn=ref(false)
    const page=ref(0)
    const startPage=ref(0)
    const endPage=ref(0)
    const showPage=ref([])
    const endCount=ref(0)
    const firstCount=ref(0)


    const processPage=(pageInt)=>{
      startPage.value=Math.floor(pageInt / (props.pageBtnSize+1))
      endPage.value=startPage.value * props.pageBtnSize
      showPage.value=[]
      endCount.value=props.pageBtnSize
      firstCount.value=endPage.value+1
      console.log(startPage.value, endPage.value, props.totalPage, firstCount.value)
      if(endPage.value>0 || endPage.value<props.totalPage) {
        nextBtn.value=true
      }
      while(endCount.value>0 && firstCount.value<=props.totalPage) {
        showPage.value.push(firstCount.value++)
        endCount.value--

      }
      console.log(showPage.value)
    }

    const clickPage=(pageInt)=>
    {
      processPage(pageInt)
      emit('change', (pageInt-1))
    }
    onMounted(()=>{
      processPage(page.value)
    })
    return {
      nextBtn,
      showPage,
      endCount,
      endPage,
      clickPage,
    }


  }
}
</script>

<style scoped>

</style>