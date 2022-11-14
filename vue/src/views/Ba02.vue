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
import { Ba02Store } from '/@store/ba02'
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
          {headerName:'EDR 툴 버전', field:'ba02PK.toolVersion',checkboxSelection: true,},
          {headerName:'EDR 리포트', field:'ba02PK.reportVersion'},
          {headerName:'EDR 코드', field:'ba02PK.code'},
          {headerName:'필수항목', field:'reqfield'},
          {headerName:'추출방식', field:'absMethod'},
          {headerName:'제목', field:'title'},
          {headerName:'부제목', field:'subTitle'},
          {headerName:'항목명', field:'descName'},
          {headerName:'첫줄여부', field:'firstLine'},
          {headerName:'recordTime', field:'recordTime'},
          {headerName:'recordValue', field:'recordValue'},
          {headerName:'워크시트명', field:'worksname'},
          {headerName:'자료가공', field:'convType'},
          {headerName:'자동차회사', field:'carCompany'},
          {headerName:'차량종류', field:'carKind'},
          {headerName:'등록자', field:'addUser'},
        ]},
      defaultColDef: {
        resizable:true,
        sortable: true,
        filter: true,
      },


    })

    const rowData=reactive([])
    const ba02Store=Ba02Store()
    const params=ref({
      page:0,
      size:null,
      sort:""
    })


    onMounted(()=>{
      loadList()

    })
    const loadList=()=>{
      const result = ba02Store.getBa02List(params.value)
      result.then((response) => {
        ba02Store.ba02List=response.content
        rowData.value=ba02Store.ba02List
      }).catch((error) => {
        console.log(error)

      })
    }
    const deleteBtnClick=()=>{
      const params=ref([])
      config.value.gridApi.getSelectedRows().forEach((row)=> {
        params.value.push({
          toolVersion:row.ba02PK.toolVersion,
          reportVersion:row.ba02PK.reportVersion,
          code:row.ba02PK.code})
      })
      //console.log(config.value.gridApi.getSelectedRows())
      const result=ba02Store.delete(params.value)
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