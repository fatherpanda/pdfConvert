<template>
<div class="container-fluid h-100">
  <base-ag-grid :default-data="defaultData" :config="config"></base-ag-grid>
</div>
</template>

<script>
import { Aa04Store } from '/@store/aa04'
import {ref} from "vue";
import BaseAgGrid from '../components/BaseAgGrid.vue';
export default {
  name: "Aa04",
  components:{
    BaseAgGrid
  },
  setup() {
    const defaultData=[
      { headerName: '입력일시', field: 'errdate', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '문서종류', field: 'docKind', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '사건번호', field: 'accNo', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '이벤트번호', field: 'eventNo', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '코드', field: 'code', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '코드순번', field: 'codeSeq', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '에러코드', field: 'errno', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '에러메시지', field: 'errmsg', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '페이지번호', field: 'pageno', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '프로그램명', field: 'pgmid', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '테이블 ID', field: 'tableid', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '구분', field: 'gubn', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '코드', field: 'code', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '사용유무', field: 'useyn', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '디스플레이 순서', field: 'sortOrder', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '속성', field: 'attr', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '명칭', field: 'cname', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '속성1', field: 'attr1', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '코드1', field: 'code1', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '명칭1', field: 'desc1', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '속성2', field: 'attr2', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '코드2', field: 'code2', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '명칭2', field: 'desc2', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '속성3', field: 'attr3', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '코드3', field: 'code3', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '명칭3', field: 'desc3', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '속성4', field: 'attr4', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '코드4', field: 'code4', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '명칭4', field: 'desc4', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '속성5', field: 'attr5', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '코드5', field: 'code5', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '명칭5', field: 'desc5', isList: true, isWrite: true, writeOption: { type: 'text' }, },
      { headerName: '등록자', field: 'addUser', isList: true, isWrite: false, },
      { headerName: '등록일시', field: 'addDate', isList: true, isWrite: false, },
      { headerName: '등록IP', field: 'addIp', isList: false, isWrite: false, },
      { headerName: '수정자', field: 'updateUser', isList: false, isWrite: false, },
      { headerName: '수정일시', field: 'updateDate', isList: false, isWrite: false, },
      { headerName: '수정IP', field: 'updateIp', isList: false, isWrite: false, },
      { headerName: '비고', field: 'bigo', isList: false, isWrite: false, },
    ]
    const aa04Store = Aa04Store()
    
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
        const result = aa04Store.getAa04List(config.value.params)
        result.then((response) => {
          aa04Store.aa04List = response.content
          config.value.rowData = aa04Store.aa04List
          config.value.totalPage = response.totalPages
          config.value.nowPage = response.number
        }).catch((error) => {
          console.log(error)

        })
      },
      write: aa04Store.write,
      delete: (selectData) => {
        const params = ref([])
        selectData.forEach((row) => {
          params.value.push({  })
        })
        const result = aa04Store.delete(gubn.value)
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