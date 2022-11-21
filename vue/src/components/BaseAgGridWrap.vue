<template>
  <div>

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

  </div>

</template>

<script>
import { AgGridVue } from "ag-grid-vue3"
import "ag-grid-community/styles/ag-grid.css"; // Core grid CSS, always needed
import "ag-grid-community/styles/ag-theme-alpine.css";
import {onMounted, reactive, ref} from "vue";
import AgGridEditDelCell from "/@components/AgGridEditDelCell.vue";

export default {
  name: "BaseAgGrid",
  components:{
    AgGridVue,
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
      // console.log(page)
      config.value.params.page=page
      config.value.loadList()
    }
    onMounted(()=>{
      console.log(config.value.mountLoad)
      if (config.value.mountLoad == undefined || config.value.mountLoad) {
        config.value.loadList()
      }
      // console.log(writeForm)
    })
    return {
      siteConfig,
      baseConfig,
      fieldConfig,
      rowData,
      data,
      cellWasClicked,
      changePage,
    }
  }
}
</script>

<style scoped>

</style>