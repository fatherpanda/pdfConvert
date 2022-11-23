<template>
  <div class="container-fluid h-100">
    <base-ag-grid :default-data="defaultData" :config="config"></base-ag-grid>
  </div>
</template>

<script>
import { Ba04Store } from '/@store/ba04'
import {ref} from "vue";
import BaseAgGrid from '../components/BaseAgGrid.vue';
export default {
  name: "Aa01",
  components:{
    BaseAgGrid
  },
  setup() {
    const defaultData = [
      {
        headerName: '리포트 버전', field: 'ba04PK.toolVersion', isList: true, isWrite: true
        , listOption: { checkboxSelection: true, }
        , writeOption: { type: 'text' }
      },
      { headerName: 'DSSAD 리포트', field: 'ba04PK.reportVersion', isList: true, isWrite: true
        , writeOption: { type: 'text' } 
      },
      { headerName: '디스플레이순서', field: 'sortOrder', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '카테고리', field: 'subOrder', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '코드속성', field: 'attr', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '코드설명', field: 'codeDesc', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '카테고리', field: 'category', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '오리지널코드', field: 'orgCode', isList: true, isWrite: true, writeOption: { type: 'text' } },
      { headerName: '등록자', field: 'addUser', isList: true, isWrite: true, writeOption: { type: 'text' } },
    ]

    const ba04Store = Ba04Store()
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
        const result = ba04Store.getBa04List(config.value.params)
        result.then((response) => {
          ba04Store.ba04List = response.content
          config.value.rowData = ba04Store.ba04List
          config.value.totalPage = response.totalPages
          config.value.nowPage = response.number
        }).catch((error) => {
          console.log(error)

        })
      },
      write: ba04Store.write,
      delete: (selectData) => {
        const params = ref([])
        selectData.forEach((row) => {

        })
        const result = ba04Store.delete(gubn.value)
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