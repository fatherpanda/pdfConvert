<template>
  <div>
    <div class="btn-box mb-3">
      <button class="btn btn-primary" @click="writeBtnClick">등록</button>
      <button class="btn btn-primary" @click="deleteBtnClick">삭제</button>
    </div>
    <ag-grid-vue
        class="ag-theme-alpine"
        style="height: 500px"
        :columnDefs="baseConfig.columnDefs.value"
        :rowData="config.rowData"
        :defaultColDef="baseConfig.defaultColDef"
        rowSelection="multiple"
        animateRows="true"
        @cell-clicked="cellWasClicked"
        @grid-ready="baseConfig.onGridReady"
    >
    </ag-grid-vue>
    <pagination :page="config.params.page" :size="config.params.size" :total-page="config.totalPage" v-on:change="changePage" v-if="config.totalPage>0"/>
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
import { AgGridVue } from "ag-grid-vue3"
import "ag-grid-community/styles/ag-grid.css"; // Core grid CSS, always needed
import "ag-grid-community/styles/ag-theme-alpine.css";
import Popup from "/@components/Popup.vue";
import FormBox from "/@components/FormBox.vue";
import Pagination from "/@components/Pagination.vue";
import {onMounted, reactive, ref} from "vue";
import AgGridEditDelCell from "/@components/AgGridEditDelCell.vue";

export default {
  name: "BaseAgGrid",
  components:{
    AgGridVue,
    Popup,
    FormBox,
    Pagination,
  },
  props:{
    defaultData: {
      type:Object
    },
    config:{
      type:Object
    }

  },
  setup(props) {
    const siteConfig=ref({
      isPopup:false
    })
    const config=ref(props.config)
    const rowEdit=(rowData) =>{
      data.value=rowData
      writeBtnClick()
    }
    const rowDel=(data) =>{
      console.log(data)
    }
    const writeBtnClick=()=>{
      siteConfig.value.isPopup=true
    }
    const writeSubmit=(e)=>{
      e.preventDefault()
      const result=config.value.write(data.value)
      result.then((response) => {
        data.value=Object.assign({},dataDefault)
        config.value.loadList()
        siteConfig.value.isPopup=false
      }).catch((error) => {
        console.log(error)
      })
    }
    const deleteBtnClick=()=>{
      config.value.delete(baseConfig.value.gridApi.getSelectedRows())
    }

    const defaultData=props.defaultData
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
      fieldConfig.value.list.push(
          {headerName: '수정/삭제',pinned: 'right',
            cellRenderer: AgGridEditDelCell,
            width:130,
            cellRendererParams:{
              edit:rowEdit,
              del:rowDel
            }
          }
      )
    }
    extractField()
    const data=ref(Object.assign({},fieldConfig.value.dataDefault))
    const baseConfig=ref({
      gridApi:null,
      onGridReady:(params)=>{
        baseConfig.value.gridApi = params.api
      },
      columnDefs:{value:fieldConfig.value.list},
      defaultColDef: {
        resizable:true,
        sortable: true,
        filter: true,
      },


    })
    const writeForm=ref(null)
    const rowData=reactive(config.value.rowData)
    const cellWasClicked=(e)=>{

    }
    const changePage=(page) =>{
      console.log(page)
      config.value.params.page=page
      config.value.loadList()
    }
    onMounted(()=>{
      config.value.loadList()
      console.log(writeForm)
    })
    return {
      siteConfig,
      baseConfig,
      fieldConfig,
      rowData,
      data,
      cellWasClicked,
      changePage,
      writeBtnClick,
      deleteBtnClick
    }
  }
}
</script>

<style scoped>

</style>