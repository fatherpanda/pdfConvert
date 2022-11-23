<template>
  <div class="grid-container">
    <div class="main-menu-container">
      <button class="btn btn-primary" @click="writeBtnClick"><i class="fa-regular fa-file"></i> 등록</button>
      <button class="btn btn-primary" @click="deleteBtnClick"><i class="fa-solid fa-trash-can"></i> 삭제</button>
    </div>
  <base-ag-grid-wrap :default-data="defaultData" :config="config"></base-ag-grid-wrap>
  <popup v-if="siteConfig.isPopup" v-model:is-open="siteConfig.isPopup" :title="siteConfig.popupTitle">
    <da01-write @complete="uploadComplete"></da01-write>
  </popup>
</div>
</template>

<script>
import {Da01Store} from '/@store/da01'
import {ref} from "vue";
import baseAgGridWrap from "/@components/BaseAgGridWrap.vue";
import popup from "/@components/Popup.vue";
import da01Write from "/@views/Da01Write.vue";
export default {
  name: "Da01",
  components:{
    baseAgGridWrap,
    popup,
    da01Write

  },
  setup() {
    const siteConfig = ref({
      isPopup:false,
      popupTitle:''
    })
    const writeBtnClick=()=>{
      siteConfig.value.isPopup=true
      siteConfig.value.popupTitle = 'DSSAD 등록'
    }
    const defaultData=[
    {headerName:'사고번호', field:'accNo', isList:true, isWrite:false,
      listOption:{checkboxSelection: true,}
    },
    {headerName:'제조사', field:'prodName', isList:true, isWrite:false},
    {headerName:'차종정보', field:'carDesc', isList:true, isWrite:false},
    {headerName:'차대번호', field:'vin', isList:true, isWrite:false},
    {headerName:'사용자', field:'userName', isList:true, isWrite:false},
    {headerName:'기록날자', field:'writeDate', isList:true, isWrite:false},
    {headerName:'툴버전', field:'toolVersion', isList:true, isWrite:false},
    {headerName:'리포트버전', field:'reportVersion', isList:true, isWrite:false},
    {headerName:'상태 Flag', field:'statusFlag', isList:true, isWrite:false},
    {headerName:'변환파일 path', field:'docPath', isList:true, isWrite:false},
    {headerName:'등록자', field: 'addUser', isList:true, isWrite:false},
    {headerName:'등록일시', field: 'addDate', isList:true, isWrite:false},
    {headerName:'등록IP', field: 'addIp', isList:false, isWrite:false, },
    {headerName:'수정자', field: 'updateUser', isList:false, isWrite:false,},
    {headerName:'수정일시', field: 'updateDate', isList:false, isWrite:false,},
    {headerName:'수정IP', field: 'updateIp', isList:false, isWrite:false,},
    {headerName:'비고', field: 'bigo', isList:false, isWrite:true, writeOption:{type:'text'}}
        ]


    const da01Store=Da01Store()
    const config=ref({
      defaultPageSize: 20,
      pageBtnSize: 5,
      totalPage: 0,
      nowPage: 0,
      rowData:[],
      params:{
        page:0,
        size:20,
        sort:""
      },
      loadList:()=>{
        const result = da01Store.getDa01List(config.value.params)
        result.then((response) => {
          da01Store.da01List=response.content
          config.value.rowData=da01Store.da01List
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
        const result=da01Store.delete(gubn.value)
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
      writeBtnClick,
      uploadComplete
    }
  }
}
</script>

<style scoped>

</style>