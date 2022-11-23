<template>
<div class="container-fluid h-100">
  <base-ag-grid :default-data="defaultData" :config="config"></base-ag-grid>
 </div>
</template>

<script>
import { Aa02Store } from '/@store/aa02'
import {ref} from "vue";
import BaseAgGrid from '../components/BaseAgGrid.vue';
export default {
  name: "Aa02",
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

    const aa02Store = Aa02Store()
    const config = ref({
      defaultPageSize: 20,
      pageBtnSize: 5,
      totalPage: 0,
      nowPage: 0,
      rowData: [],
      params: {
        page: 0,
        size: 20,
        sort: ""
      },
      loadList: () => {
        const result = aa02Store.getAa02List(config.value.params)
        result.then((response) => {
          aa02Store.aa02List = response.content
          config.value.rowData = aa02Store.aa02List
          config.value.totalPage = response.totalPages
          config.value.nowPage = response.number
        }).catch((error) => {
          console.log(error)

        })
      },
      write: aa02Store.write,
      delete: (selectData) => {
        const params = ref([])
        selectData.forEach((row) => {

          params.value.push({ gubn: row.aa02PK.gubn, code: row.aa02PK.code })
        })
        const result = aa02Store.delete(params.value)
        result.then((response) => {
          if (response.result == true) {
            config.value.loadList()
          }
        }).catch((error) => {
          console.log(error)
        })
      }
    })
  

    
    return {
      config,
      defaultData,
      // cellWasClicked: (event) => { // Example of consuming Grid Event
      //   console.log("cell was clicked", event);
      // },
      // deselectRows: () =>{
      //   gridApi.value.deselectAll()
      // },
      // loadList,
      // deleteBtnClick,
      // fieldConfig,
      // data,
      // writeForm,
      // writeSubmit

    }
  }
}
</script>

<style scoped>

</style>