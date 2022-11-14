<template>
<div class="container">
  <div class="btn-box mb-3">
    <button class="btn btn-primary" @click="siteConfig.isPopup=true">등록</button>
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
  <popup v-if="siteConfig.isPopup" v-model:isOpen="siteConfig.isPopup">
    <form ref="writeForm" @submit="writeSubmit">
      <form-box :write-field="fieldConfig.write" :data="data"></form-box>
      <div class="text-end">
        <button class="btn btn-secondary">등록</button>
      </div>
    </form>

  </popup>
</div>
</template>

<script>
import { Aa02Store } from '/@store/aa02'
import {computed, onMounted, reactive, ref} from "vue";
import { AgGridVue } from "ag-grid-vue3"
import "ag-grid-community/styles/ag-grid.css"; // Core grid CSS, always needed
import "ag-grid-community/styles/ag-theme-alpine.css";
import Popup from "/@components/Popup.vue";
import FormBox from "/@components/FormBox.vue";
export default {
  name: "Aa02",
  components:{
    AgGridVue,
    Popup,
    FormBox
  },
  setup() {
    const siteConfig=ref({
      isPopup:false
    })
    const defaultData=[
      {headerName:'구분', field:'aa02PK.gubn', isList:true, isWrite:true,
        listOption:{checkboxSelection: true,}
        , writeOption:{type:'text'}
      },
      {headerName:'코드', field:'aa02PK.code', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'사용유무', field: 'useyn', isList:true, isWrite:true
        , writeOption:{type:'radio', data:[{name:'yes', val:'y'},{name:'no', val:'n'}]}},
      {headerName:'디스플레이 순서', field: 'sortOrder', isList:true, isWrite:true
        , writeOption:{type:'number'}},
      {headerName:'속성', field: 'attr', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'명칭', field: 'cname', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'속성1', field:'attr1', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'코드1', field:'code1', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'명칭1', field:'desc1', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'속성2', field:'attr2', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'코드2', field:'code2', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'명칭2', field:'desc2', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'속성3', field:'attr3', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'코드3', field:'code3', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'명칭3', field:'desc3', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'속성4', field:'attr4', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'코드4', field:'code4', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'명칭4', field:'desc4', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'속성5', field:'attr5', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'코드5', field:'code5', isList:true, isWrite:true
        , writeOption:{type:'text'}
      },
      {headerName:'명칭5', field:'desc5', isList:true, isWrite:true
        , writeOption:{type:'text'}
      },
      {headerName:'등록자', field: 'addUser', isList:true, isWrite:false
      },
      {headerName:'등록일시', field: 'addDate', isList:true, isWrite:false
      },
      {headerName:'등록IP', field: 'addIp', isList:false, isWrite:false
      },
      {headerName:'수정자', field: 'updateUser', isList:false, isWrite:false
      },
      {headerName:'수정일시', field: 'updateDate', isList:false, isWrite:false
      },
      {headerName:'수정IP', field: 'updateIp', isList:false, isWrite:false},
      {headerName:'비고', field: 'bigo', isList:false, isWrite:true, writeOption:{type:'text'}}
    ]
    const fieldConfig=ref({
      list:[],
      write:[],
      dataDefault:{}
    })

    const extractField=()=>{

      defaultData.forEach((row)=>{
        if(row.isList) {
          const line=Object.assign({headerName:row.headerName,field:row.field}, row.listOption)
          fieldConfig.value.list.push(line)
        }
        if(row.isWrite) {
          const write=Object.assign({headerName:row.headerName,field:row.field}, row.writeOption)
          let dataDefault=fieldConfig.value.dataDefault
          let field=row.field
          if(field.indexOf(".")>-1) {

            let key=field.substring(0, field.indexOf("."))
            field=field.substring(field.indexOf(".")+1,field.length)
            if(fieldConfig.value.dataDefault[key]==undefined) {
              fieldConfig.value.dataDefault[key] = {}
            }
            dataDefault=fieldConfig.value.dataDefault[key]
          }
          fieldConfig.value.write.push(write)
          if(row.writeOption.type==='text' || row.writeOption.type==='radio') {
            dataDefault[field]=''
          } else if(row.writeOption.type==='number') {
            dataDefault[field]=0
          }


        }
      })
      console.log(fieldConfig.value.dataDefault)
    }
    extractField()
    const config=ref({
      gridApi:null,
      onGridReady:(params)=>{
        config.value.gridApi = params.api
      },
      columnDefs:{value:fieldConfig.value.list},
      defaultColDef: {
        resizable:true,
        sortable: true,
        filter: true,
      },


    })

    const rowData=reactive([])
    const aa02Store=Aa02Store()
    const params=ref({
      page:0,
      size:null,
      sort:""
    })


    onMounted(()=>{
      loadList()
    })
    const loadList= ()=>{
      const result = aa02Store.getAa02List(params.value)
      result.then((response) => {
        aa02Store.aa02List=response.content
        rowData.value=aa02Store.aa02List
      }).catch((error) => {
        console.log(error)
     })
    }

    const deleteBtnClick=()=>{
      const params=ref([])
      config.value.gridApi.getSelectedRows().forEach((row)=> {
        params.value.push({gubn:row.aa02PK.gubn, code:row.aa02PK.code})
      })
      //console.log(config.value.gridApi.getSelectedRows())
      const result=aa02Store.delete(params.value)
      result.then((response) => {
        if(response.result==true){
          loadList()
        }
      }).catch((error) => {
        console.log(error)
      })
    }

    const dataDefault=fieldConfig.value.dataDefault
    const data=ref(Object.assign({},dataDefault))
    const writeForm=ref(null)
    const writeSubmit=(e)=>{
      e.preventDefault()
      console.log(data.value)
      // const result=aa02Store.write(data.value)
      // result.then((response) => {
      //   data.value=Object.assign({},dataDefault)
      //   loadList()
      //   siteConfig.value.isPopup=false
      // }).catch((error) => {
      //   console.log(error)
      // })
    }
    return {
      siteConfig,
      config,
      rowData,
      cellWasClicked: (event) => { // Example of consuming Grid Event
        console.log("cell was clicked", event);
      },
      deselectRows: () =>{
        gridApi.value.deselectAll()
      },
      loadList,
      deleteBtnClick,
      fieldConfig,
      data,
      writeForm,
      writeSubmit

    }
  }
}
</script>

<style scoped>

</style>