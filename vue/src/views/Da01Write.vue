<template>
  <div>
    <form @submit="sendSubmit">
    <div class="card">
      <div class="card-header">
        <div class="row justify-content-between">
          <div class="col-auto">
            DSSAD 등록
          </div>
          <div class="col-auto">


            <button class="btn btn-outline-secondary">
              등록
            </button>
          </div>
        </div>

      </div>
      <div class="card-body">
          <div class="row mb-3">
            <div class="col-2 col-form-label">
              <span class="text-danger">*</span>
              사고번호
            </div>
            <div class="col-6">
              <input type="text" class="form-control" v-model="da01.accNo" required>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-2 col-form-label">
              <span class="text-danger">*</span>
              리포트버전
            </div>
            <div class="col-2">
              <select class="form-select" v-model="da01.reportVersion" required>
                <option value="">선택하세요</option>
                <template v-if="reportVersion.length>0">
                  <option v-for="ver in reportVersion" :key="ver" :value="ver">{{ver}}</option>
                </template>
              </select>
            </div>
            <div class="col-2 col-form-label">
              제조사
            </div>
            <div class="col-2">
              <select class="form-select" v-model="da01.prodName">
                <option value="">선택하세요</option>
                <template v-if="code.BA0103.length > 0">
                  <option v-for="maker in code.BA0103" :key="maker" :value="maker.aa02PK.code">{{maker.cname}}</option>
                </template>
              </select>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-2 col-form-label">
              차종 정보
            </div>
            <div class="col-2">
              <input type="text" class="form-control" v-model="da01.carDesc">
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-2 col-form-label">
              차대 번호
            </div>
            <div class="col-2">
              <input type="text" class="form-control" v-model="da01.vin">
            </div>
            <div class="col-2 col-form-label">
              툴 버전
            </div>
            <div class="col-2">
              <input type="text" class="form-control" v-model="da01.toolVersion">
<!--              <select class="form-select" v-model="da01.toolVersion"></select>-->
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-2 col-form-label">
              사용자
            </div>
            <div class="col-2">
              <input type="text" class="form-control" v-model="da01.addUser">
            </div>
            <div class="col-2 col-form-label">
              <span class="text-danger">*</span>
              PDF 업로드
            </div>
            <div class="col">
              <input type="file" class="form-control" ref="pdfFile" @change="fileSelect" required>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-2 col-form-label">
              기록날자
            </div>
            <div class="col-2">
              <input type="text" class="form-control" v-model="da01.addDate">
            </div>
            <div class="col-2 col-form-label">

            </div>
            <div class="col">
              <input type="text" class="form-control">
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-2 col-form-label">
              메모
            </div>
            <div class="col">
              <textarea class="form-control" v-model="da01.bigo"></textarea>
            </div>
          </div>

      </div>
    </div>
    </form>
  </div>
</template>

<script>
import {Da01Store} from "/@store/da01";
import {Aa02Store} from "/@store/aa02";
import {Ba04Store} from "/@store/ba04";
import {computed, ref} from "vue";
export default {
  name: "Da01Write",
  props:{
    complete:{
      type:Function
    }
  },
  setup(props, {emit}) {
    const aa02Store=Aa02Store()
    const ba04Store=Ba04Store()
    aa02Store.getCode("BA0103")
    aa02Store.getCode("BA0105")
    ba04Store.getReportVersion()

    const code=computed(()=>aa02Store.code)
    const reportVersion=computed(()=>ba04Store.reportVersion)

    const da01Store=Da01Store()
    const da01=da01Store.getBean()
    const pdfFile=ref(null)
    const sendSubmit=(e)=>{
      e.preventDefault()
      const result=da01Store.write(da01)
      result.then((response) => {
        console.log(response)
        emit("complete")

      }).catch((error) => {
        console.log(error)

      })
    }
    const fileSelect=(e)=>{
      const fileData = e.target.files[0]
      da01.pdfFile=fileData
    }
    const emitTest=()=> {
      emit("complete")
    }
    return {
      code,
      reportVersion,
      da01,
      pdfFile,
      sendSubmit,
      fileSelect,
      emitTest
    }
  }
}
</script>

<style scoped>

</style>