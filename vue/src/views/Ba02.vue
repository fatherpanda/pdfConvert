<template>
  <div class="container-fluid h-100">
    <base-ag-grid :default-data="defaultData" :config="config"></base-ag-grid>
  </div>
</template>

<script>
import { Ba02Store } from '/@store/ba02'
import {ref} from "vue";
import BaseAgGrid from '../components/BaseAgGrid.vue';
export default {
  name: "Aa01",
  components:{
    BaseAgGrid
  },
  setup() {
    const defaultData=[
      { headerName: 'EDR 툴 버전', field: 'ba02PK.toolVersion', isList: true, isWrite: true
        , listOption: { checkboxSelection: true, }
        , writeOption: { type: 'text' } 
      },
      { headerName: 'EDR 리포트', field: 'ba02PK.reportVersion', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: 'EDR 코드', field: 'ba02PK.code', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '필수항목', field: 'reqfield', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '추출방식', field: 'absMethod', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '제목', field: 'title', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '부제목', field: 'subTitle', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '항목명', field: 'descName', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '첫줄여부', field: 'firstLine', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: 'recordTime', field: 'recordTime', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: 'recordValue', field: 'recordValue', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '워크시트명', field: 'worksname', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '자료가공', field: 'convType', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '자동차회사', field: 'carCompany', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '차량종류', field: 'carKind', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '등록자', field: 'addUser', isList: true, isWrite: false },
    ]
    const ba02Store = Ba02Store()
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
        const result = ba02Store.getBa02List(config.value.params)
        result.then((response) => {
          ba02Store.ba02List = response.content
          config.value.rowData = ba02Store.ba02List
          config.value.totalPage = response.totalPages
          config.value.nowPage = response.number
        }).catch((error) => {
          console.log(error)

        })
      },
      write: ba02Store.write,
      delete: (selectData) => {
        const params = ref([])
        selectData.forEach((row) => {
          
        })
        const result = ba02Store.delete(gubn.value)
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
    }
  }
}
</script>

<style scoped>

</style>