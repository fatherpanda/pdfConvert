<template>
<div class="container">
  <div class="btn-box mb-3">
    <button class="btn btn-primary">등록</button>
    <button class="btn btn-primary">삭제</button>
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
import { Aa04Store } from '/@store/aa04'
import {computed, onMounted, reactive, ref} from "vue";
import { AgGridVue } from "ag-grid-vue3"
import "ag-grid-community/styles/ag-grid.css"; // Core grid CSS, always needed
import "ag-grid-community/styles/ag-theme-alpine.css";

export default {
  name: "Aa04",
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
          {headerName:'입력일시', field:'errdate'},
          {headerName:'문서종류', field:'docKind'},
          {headerName:'사건번호', field:'accNo'},
          {headerName:'이벤트번호', field:'eventNo'},
          {headerName:'코드', field:'code'},
          {headerName:'코드순번', field:'codeSeq'},
          {headerName:'에러코드', field:'errno'},
          {headerName:'에러메시지', field:'errmsg'},
          {headerName:'페이지번호', field:'pageno'},
          {headerName:'프로그램명', field:'pgmid'},
          {headerName:'테이블 ID', field:'tableid'},
          {headerName:'구분', field:'gubn'},
          {headerName:'코드', field:'code'},
          {headerName:'사용유무', field: 'useyn'},
          {headerName:'디스플레이 순서', field: 'sortOrder'},
          {headerName:'속성', field: 'attr'},
          {headerName:'명칭', field: 'cname'},
          {headerName:'속성1', field:'attr1'},
          {headerName:'코드1', field:'code1'},
          {headerName:'명칭1', field:'desc1'},
          {headerName:'속성2', field:'attr2'},
          {headerName:'코드2', field:'code2'},
          {headerName:'명칭2', field:'desc2'},
          {headerName:'속성3', field:'attr3'},
          {headerName:'코드3', field:'code3'},
          {headerName:'명칭3', field:'desc3'},
          {headerName:'속성4', field:'attr4'},
          {headerName:'코드4', field:'code4'},
          {headerName:'명칭4', field:'desc4'},
          {headerName:'속성5', field:'attr5'},
          {headerName:'코드5', field:'code5'},
          {headerName:'명칭5', field:'desc5'},
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
      },


    })

    const rowData=reactive([])
    const aa04Store=Aa04Store()
    const params=ref({
      page:0,
      size:null,
      sort:""
    })


    onMounted(()=>{
      const result = aa04Store.getAa04List(params.value)
      result.then((response) => {
        aa04Store.aa04List=response.content
        rowData.value=aa04Store.aa04List
      }).catch((error) => {
        console.log(error)

      })

    })
    return {
      config,
      rowData,
      cellWasClicked: (event) => { // Example of consuming Grid Event
        console.log("cell was clicked", event);
      },
      deselectRows: () =>{
        gridApi.value.deselectAll()
      }
    }
  }
}
</script>

<style scoped>

</style>