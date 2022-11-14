<template>
  <div class="card">
    <div class="card-header">
      업로드
    </div>
    <div class="card-body">
      <div class="row">
        <div class="col-12">
          <input type="file" class="form-control" accept=".pdf" @change="loadFile">

        </div>
        <div class="col-12 text-end mt-3">
          <button class="btn btn-primary" @click="submitFile">등록</button>

        </div>
      </div>

    </div>

  </div>

</template>
<script>
import {upload} from "../api";
export default {
  setup() {
    const params={
      file:null
    }
    const loadFile = (event) => {
      const fileData = event.target.files[0]
      const filename=fileData.name
      const fileext=filename.substring( filename.lastIndexOf(".")+1)
      params.file = fileData
    }
    const submitFile =()=>{
      const result = upload(params)
      result
          .then((response) => {
            if (response.code === 200) {

            }
          })
          .catch((error) => {
            console.log(error)
          })
    }
    return {
      loadFile,
      submitFile
    }
  },
}

</script>