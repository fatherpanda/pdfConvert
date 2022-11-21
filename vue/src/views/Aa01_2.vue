<template>
<div class="container">

  <base-ag-grid :default-data="defaultData" :config="config"></base-ag-grid>
</div>
</template>

<script>
import {Aa01Store} from '/@store/aa01'
import {ref} from "vue";
import BaseAgGrid from "/@components/BaseAgGrid.vue";

export default {
  name: "Aa01_2",
  components:{
    BaseAgGrid
  },
  setup() {
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
    const aa01Store=Aa01Store()
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
        const result = aa01Store.getAa01List(config.value.params)
        result.then((response) => {
          aa01Store.aa01List=response.content
          config.value.rowData=aa01Store.aa01List
          config.value.totalPage=response.totalPages
          config.value.nowPage=response.number
        }).catch((error) => {
          console.log(error)

        })
      },
      write:aa01Store.write,
      delete:(selectData)=> {
        const gubn=ref([])
        selectData.forEach((row)=> {
          gubn.value.push(row.gubn)
        })
        const result=aa01Store.delete(gubn.value)
        result.then((response) => {
          if(response.result==true){
            loadList()
          }
        }).catch((error) => {
          console.log(error)
        })
      }
    })

    // const config=ref({
    //   pageSize:20,
    //   pageBtnSize:5,
    //   totalPage:0,
    //   nowPage:0,
    //   gridApi:null,
    //   onGridReady:(params)=>{
    //     config.value.gridApi = params.api
    //   },
    //   columnDefs:{value:[
    //       {headerName:'구분', field:'gubn',checkboxSelection: true,},
    //       {headerName:'사용유무', field: 'useyn', width:100},
    //       {headerName:'순서', field: 'sortOrder', width:100},
    //       {headerName:'속성', field: 'attr'},
    //       {headerName:'명칭1', field: 'cname'},
    //       {headerName:'속성1변환규칙', field: 'convType'},
    //       {headerName:'메모', field: 'memo'},
    //       {headerName:'등록자', field: 'addUser'},
    //       {headerName:'등록일시', field: 'addDate'},
    //       {headerName: '수정/삭제',pinned: 'right',
    //         cellRenderer: AgGridEditDelCell,
    //         width:130,
    //         cellRendererParams:{
    //           edit:rowEdit,
    //           del:rowDel
    //         }
    //       }
    //       // {headerName:'등록IP', field: 'addIp'},
    //       // {headerName:'수정자', field: 'updateUser'},
    //       // {headerName:'수정일시', field: 'updateDate'},
    //       // {headerName:'수정IP', field: 'updateIp'},
    //       // {headerName:'비고', field: 'bigo'}
    //     ]},
    //   defaultColDef: {
    //     resizable:true,
    //     sortable: true,
    //     filter: true,
    //   },
    //
    //
    // })

    return {
      defaultData,
      config
    }
  }
}
</script>

<style scoped>

</style>