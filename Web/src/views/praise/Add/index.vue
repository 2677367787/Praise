<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="formRules" label-width="80px">
      <el-form-item label="姓名">
        <user-input ref="userInput" :data="form" :onFocusGetData="true"></user-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input
        type="textarea"
        :rows="4"
        class="input-conten"
        placeholder="请输入内容"
        v-model="form.content">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')" :loading="logining">点赞</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { ApiUrl } from '@/api/apiUrl'
export default {
  data() {
    return {
      logining: false,
      form: {
        userName: '',
        content: ''
      },
      formRules: {
        content: [
          { required: true, message: '请输入点赞内容', tirgger: 'blur' }
        ]
      }
    }
  },
  methods: {
    onSubmit(formName) {
      this.logining = true
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.form.praiseTo = this.form.userName
          this.$ajax.post(ApiUrl.praiseAddUrl, this.form).then(result => {
            this.logining = false
            this.$message.success('点赞成功!')
            this.form.content = ''
            this.form.userName = ''
          }, result => {
            this.logining = false
            this.$message.success(result)
          })
        } else {
          this.logining = false
          this.$message.error('!')
        }
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
.input-conten{
  max-width: 35%;
  min-width: 300px;
}
</style>

