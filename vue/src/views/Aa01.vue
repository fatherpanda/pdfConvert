<template>
<div class="container">
  <div class="btn-box mb-3">
    <button class="btn btn-primary" @click="writeBtnClick">등록</button>
    <button class="btn btn-primary" @click="deleteBtnClick">삭제</button>
  </div>
  <ag-grid-vue
      class="ag-theme-alpine"
      style="height: 500px"
      :columnDefs="config.columnDefs.value"
      :rowData="rowData.value"
      :defaultColDef="config.defaultColDef"
      rowSelection="multiple"
      animateRows="true"
      @cell-clicked="cellWasClicked"
      @grid-ready="config.onGridReady"
  >
  </ag-grid-vue>
  {{config.totalPage}}
  <pagination :page="params.page" :size="config.pageSize" :total-page="config.totalPage" v-on:change="changePage" v-if="config.totalPage>0"/>
  <popup v-if="siteConfig.isPopup" v-model:isOpen="siteConfig.isPopup">
    <form ref="writeForm" @submit="writeSubmit">
      <form-box :write-field="writeField" :data="data"></form-box>
      <div class="text-end">
        <button class="btn btn-secondary">등록</button>
      </div>
    </form>

  </popup>


</div>
</template>

<script>
import {Aa01Store} from '/@store/aa01'
import {computed, onMounted, reactive, ref} from "vue";
import { AgGridVue } from "ag-grid-vue3"
import "ag-grid-community/styles/ag-grid.css"; // Core grid CSS, always needed
import "ag-grid-community/styles/ag-theme-alpine.css";
import Popup from "/@components/Popup.vue";
import FormBox from "/@components/FormBox.vue";
import Pagination from "/@components/Pagination.vue";
import {keys} from 'ts-transformer-keys'

export default {
  name: "Aa01",
  components:{
    AgGridVue,
    Popup,
    FormBox,
    Pagination
  },
  setup() {
    const siteConfig=ref({
      isPopup:false
    })
    const config=ref({
      pageSize:20,
      pageBtnSize:5,
      totalPage:0,
      nowPage:0,
      gridApi:null,
      onGridReady:(params)=>{
        config.value.gridApi = params.api
      },
      columnDefs:{value:[
          {headerName:'구분', field:'gubn',checkboxSelection: true,},
          {headerName:'사용유무', field: 'useyn'},
          {headerName:'디스플레이 순서', field: 'sortOrder'},
          {headerName:'속성', field: 'attr'},
          {headerName:'명칭1', field: 'cname'},
          {headerName:'속성1변환규칙', field: 'convType'},
          {headerName:'메모', field: 'memo'},
          {headerName:'등록자', field: 'addUser'},
          {headerName:'등록일시', field: 'addDate'},
          // {headerName:'등록IP', field: 'addIp'},
          // {headerName:'수정자', field: 'updateUser'},
          // {headerName:'수정일시', field: 'updateDate'},
          // {headerName:'수정IP', field: 'updateIp'},
          // {headerName:'비고', field: 'bigo'}
        ]},
      defaultColDef: {
        resizable:true,
        sortable: true,
        filter: true,
        flex: 1
      },


    })

    const rowData=reactive([])
    const aa01Store=Aa01Store()
    const params=ref({
      page:0,
      size:config.value.pageSize,
      sort:""
    })



    const loadList=()=>{
      const result = aa01Store.getAa01List(params.value)
      result.then((response) => {
        aa01Store.aa01List=response.content
        rowData.value=aa01Store.aa01List
        config.value.totalPage=response.totalPages
        config.value.nowPage=response.number
      }).catch((error) => {
        console.log(error)

      })
    }
    const deleteBtnClick=()=>{
      const gubn=ref([])
      config.value.gridApi.getSelectedRows().forEach((row)=> {
        gubn.value.push(row.gubn)
      })
      //console.log(config.value.gridApi.getSelectedRows())
      const result=aa01Store.delete(gubn.value)
      result.then((response) => {
        if(response.result==true){
          loadList()
        }
      }).catch((error) => {
        console.log(error)
      })
    }
    const writeBtnClick=()=>{
      siteConfig.value.isPopup=true
    }
    const dataDefault={
      gubn: '',
      useyn: '',
      sortOrder: 0,
      attr: '',
      cname: '',
      convType: '',
      memo: '',
    }
    const data=ref(Object.assign({},dataDefault))

    const writeField=ref([{headerName:'구분', field:'gubn', required:true, type:'text'},
        {headerName:'사용유무', field: 'useyn', required:true, type:'radio', data:[{name:'yes', val:'y'},{name:'no', val:'n'}]},
        {headerName:'디스플레이 순서', field: 'sortOrder', required:true, type:'number'},
        {headerName:'속성', field: 'attr', required:true, type:'text'},
        {headerName:'명칭1', field: 'cname', required:true, type:'text'},
        {headerName:'속성1변환규칙', field: 'convType', required:true, type:'text'},
        {headerName:'메모', field: 'memo', required:false, type:'text'},
        ])

    const writeForm=ref(null)


    const writeSubmit=(e)=>{
      e.preventDefault()
      const result=aa01Store.write(data.value)
      result.then((response) => {
        data.value=Object.assign({},dataDefault)
        loadList()
        siteConfig.value.isPopup=false
      }).catch((error) => {
        console.log(error)
      })
    }
    const changePage=(page) =>{
      console.log(page)
      params.value.page=page
      loadList()
    }
    onMounted(()=>{
      loadList()
      console.log(writeForm)
    })
    return {
      siteConfig,
      config,
      params,
      rowData,
      cellWasClicked: (event) => { // Example of consuming Grid Event
        console.log("cell was clicked", event);
      },
      deselectRows: () =>{
        gridApi.value.deselectAll()
      },
      loadList,
      deleteBtnClick,
      writeBtnClick,
      data,
      writeField,
      writeForm,
      writeSubmit,
      changePage
    }
  }
}
</script>

<style scoped>

</style>