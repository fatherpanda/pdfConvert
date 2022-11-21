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
  name: "Aa01",
  components:{
    BaseAgGrid
  },
  setup() {

    const defaultData=[
      {headerName:'구분', field:'gubn', isList:true, isWrite:true,
        listOption:{checkboxSelection: true,}
        , writeOption:{type:'text'}
      },
     {headerName:'사용유무', field: 'useyn', isList:true, isWrite:true
        , writeOption:{type:'radio', data:[{name:'yes', val:'y'},{name:'no', val:'n'}]}
        , listOption:{ width:100 }
      },
      {headerName:'순서', field: 'sortOrder', isList:true, isWrite:true
        , writeOption:{type:'number'}},
      {headerName:'속성', field: 'attr', isList:true, isWrite:true
        , writeOption:{type:'text'}},
      {headerName:'속성', field: 'attr', isList:true, isWrite:true, writeOption:{type:'text'}},
      {headerName:'명칭1', field: 'cname', isList:true, isWrite:true, writeOption:{type:'text'}},
      {headerName:'속성1변환규칙', field: 'convType', isList:true, isWrite:true, writeOption:{type:'text'}},
      {headerName:'메모', field: 'memo', isList:true, isWrite:true, writeOption:{type:'text'}},
      {headerName:'등록자', field: 'addUser', isList:true, isWrite:false},
      {headerName:'등록일시', field: 'addDate', isList:true, isWrite:false},
      {headerName:'등록IP', field: 'addIp', isList:false, isWrite:false, },
      {headerName:'수정자', field: 'updateUser', isList:false, isWrite:false,},
      {headerName:'수정일시', field: 'updateDate', isList:false, isWrite:false,},
      {headerName:'수정IP', field: 'updateIp', isList:false, isWrite:false,},
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
            config.value.loadList()
          }
        }).catch((error) => {
          console.log(error)
        })
      }
    })
    return {
      defaultData,
      config
    }
  }
}
</script>

<style scoped>

</style>