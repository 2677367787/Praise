<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="姓名">
        <user-input :formData="form"></user-input>
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
      }
    }
  },
  methods: {
    onSubmit(formName) {
      this.form.praiseTo = this.form.userName
      if (!this.form.praiseTo && !this.form.content) {
        this.$message.error('请输入必填项!')
        return false
      }
      this.logining = true
      this.$ajax.post(ApiUrl.praiseAddUrl, this.form).then(result => {
        this.logining = false
        this.$message.success('点赞成功!')
        this.form.content = ''
        this.form.userName = ''
      }, result => {
        this.logining = false
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

