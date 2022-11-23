<template>
  <div class="grid-container">
    <div class="main-menu-container">
      <div class="row">
        <!-- <div class="col-4">
      <button class="btn btn-primary" @click="writeBtnClick"><i class="fa-regular fa-file"></i> 등록</button>
      <button class="btn btn-primary" @click="deleteBtnClick"><i class="fa-solid fa-trash-can"></i> 삭제</button>

        </div> -->
        <div class="col">
          <div class="row g-0">
            <div class="col-auto text-white col-form-label pe-3">
              사고번호
            </div>
            <div class="col-2 pe-4">
              <input type="text" class="form-control" v-model="config.params.accNo">
            </div>
            <div class="col-auto text-white col-form-label pe-3">
              이벤트번호
            </div>
            <div class="col-2 pe-4">
              <select class="form-select" v-model="config.params.eventNo">
                <option value="">선택하세요</option>
                <template v-if="eventNo.length>0">
                  <option v-for="no in eventNo" :key="no">{{ no }}</option>
                </template>
              </select>
            </div>
            <div class="col-auto text-white col-form-label pe-3">
              상태Flag
            </div>
            <div class="col-2 pe-4">
              <input type="text" class="form-control">
            </div>
            <div class="col-auto ms-auto"><button class="btn btn-primary" @click="config.loadList">검색</button></div>
          </div>
          

        </div>
      </div>
    </div>
  <base-ag-grid-wrap :default-data="defaultData" :config="config"></base-ag-grid-wrap>
  
  <popup v-if="siteConfig.isPopup" v-model:is-open="siteConfig.isPopup">
    <da01-write @complete="uploadComplete"></da01-write>
  </popup>
</div>
</template>

<script>
import {Da02Store} from '/@store/da02'
import {computed, ref} from "vue";
import baseAgGridWrap from "/@components/BaseAgGridWrap.vue";
import popup from "/@components/Popup.vue";
import da01Write from "/@views/Da01Write.vue";
import Pagination from '../components/Pagination.vue';
export default {
  name: "Da01",
  components:{
    baseAgGridWrap,
    popup,
    da01Write,
    Pagination

  },
  setup() {
    const siteConfig = ref({
      isPopup:false
    })
    const writeBtnClick=()=>{
      siteConfig.value.isPopup=true
    }
    
    const defaultData= [
    {
        headerName: '사건번호', field: 'da02PK.accNo', isList: true, isWrite: false,
        
    },
    {
      headerName: '이벤트코드', field: 'da02PK.eventNo', isList: true, isWrite: false,
      listOption: { width:120 }
    },
    {
      headerName: 'DSSAD 코드', field: 'da02PK.dssadCode', isList: true, isWrite: false
    },
    {
      headerName: '정렬순서', field: 'sortOrder', isList: true, isWrite: false,
      listOption: { width: 120 }
    },
    {
      headerName: '서브순서', field: 'subOrder', isList: true, isWrite: false,
      listOption: { width: 120 }
    },
    {
      headerName: '속성', field: 'attr', isList: true, isWrite: false
    },
    {
      headerName: '기록시간', field: 'recodeTime', isList: true, isWrite: false
    },
    {
      headerName: '기록값', field: 'recodeValue', isList: true, isWrite: false
    },
    {
      headerName: '등록자', field: 'addUser', isList: false, isWrite: false
    },
    {
      headerName: '등록일시', field: 'addDate', isList: false, isWrite: false
    },
    {
      headerName: '등록IP', field: 'addIp', isList: false, isWrite: false
    },
    {
      headerName: '수정자', field: 'updateUser', isList: false, isWrite: false
    },
    {
      headerName: '수정일시', field: 'updateDate', isList: false, isWrite: false
    },
    {
      headerName: '수정IP', field: 'updateIp', isList: false, isWrite: false
    },
    {
      headerName: '비고', field: 'bigo', isList: false, isWrite: false
    }
  ]


    const da02Store = Da02Store()
    da02Store.getDa02EventNo()
    const eventNo=computed(()=>da02Store.eventNo)
    const config=ref({
      defaultPageSize: 20,
      pageBtnSize: 5,
      totalPage: 0,
      nowPage: 0,
      rowData:[],
      mountLoad:false,
      params:{
        accNo:"",
        eventNo:"",

      },
      loadList:()=>{
        console.log(config.value.params)
        const result = da02Store.getDa02ListAll(config.value.params)
        result.then((response) => {
          da02Store.da02List=response
          config.value.rowData=da02Store.da02List
          config.value.totalPage=response.totalPages
          config.value.nowPage=response.number
        }).catch((error) => {
          console.log(error)

        })
      },
      write:null,
      delete:(selectData)=> {
        const gubn=ref([])
        selectData.forEach((row)=> {
          gubn.value.push(row.gubn)
        })
        const result=da02Store.delete(gubn.value)
        result.then((response) => {
          if(response.result==true){
            config.value.loadList()
          }
        }).catch((error) => {
          console.log(error)
        })
      }
    })

    const uploadComplete=()=>{
      siteConfig.value.isPopup=false
      config.value.loadList()
    }

    return {
      defaultData,
      config,
      siteConfig,
      eventNo,
      writeBtnClick,
      uploadComplete,
    }
  }
}
</script>

<style scoped>

</style>