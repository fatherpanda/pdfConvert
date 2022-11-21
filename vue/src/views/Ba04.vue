<template>
<div class="container">
  <div class="btn-box mb-3">
    <button class="btn btn-primary">등록</button>
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

</div>
</template>

<script>
import { Ba04Store } from '/@store/ba04'
import {computed, onMounted, reactive, ref} from "vue";
import { AgGridVue } from "ag-grid-vue3"
import "ag-grid-community/styles/ag-grid.css"; // Core grid CSS, always needed
import "ag-grid-community/styles/ag-theme-alpine.css";

export default {
  name: "Aa01",
  components:{
    AgGridVue
  },
  setup() {
    const config=ref({
      gridApi:null,
      onGridReady:(params)=>{
        config.value.gridApi = params.api
      },
      columnDefs:{value:[
          {headerName:'리포트 버전', field:'ba04PK.toolVersion',checkboxSelection: true,},
          {headerName:'DSSAD 리포트', field:'ba04PK.reportVersion'},
          {headerName:'디스플레이순서', field:'sortOrder'},
          {headerName:'카테고리', field:'subOrder'},
          {headerName:'코드속성', field:'attr'},
          {headerName:'코드설명', field:'codeDesc'},
          {headerName:'카테고리', field:'category'},
          {headerName:'오리지널코드', field:'orgCode'},
          {headerName:'등록자', field:'addUser'},
        ]},
      defaultColDef: {
        resizable:true,
        sortable: true,
        filter: true,
      },


    })

    const rowData=reactive([])
    const ba04Store=Ba04Store()
    const params=ref({
      page:0,
      size:null,
      sort:""
    })


    onMounted(()=>{
      loadList()

    })

    const loadList=()=>{
      const result = ba04Store.getBa04List(params.value)
      result.then((response) => {
        ba04Store.ba04List=response.content
        rowData.value=ba04Store.ba04List
      }).catch((error) => {
        console.log(error)

      })
    }

    const deleteBtnClick=()=>{
      const params=ref([])
      config.value.gridApi.getSelectedRows().forEach((row)=> {
        params.value.push({
          toolVersion:row.ba04PK.toolVersion,
          reportVersion:row.ba04PK.reportVersion,
          code:row.ba04PK.code})
      })
      //console.log(config.value.gridApi.getSelectedRows())
      const result=ba04Store.delete(params.value)
      result.then((response) => {
        if(response.result==true){
          loadList()
        }
      }).catch((error) => {
        console.log(error)
      })
    }
    return {
      config,
      rowData,
      cellWasClicked: (event) => { // Example of consuming Grid Event
        console.log("cell was clicked", event);
      },
      deselectRows: () =>{
        gridApi.value.deselectAll()
      },
      deleteBtnClick
    }
  }
}
</script>

<style scoped>

</style>