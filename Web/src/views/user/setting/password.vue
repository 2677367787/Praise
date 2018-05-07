<template>
  <div class="app-container">
    <el-form ref="form" :rules="formRules" :model="form" label-width="80px" class="demo-ruleForm login-container">
      <el-form-item label="旧密码" prop="passwordOld">
        <el-input type="password" v-model="form.passwordOld"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="passwordNew">
        <el-input type="password" v-model="form.passwordNew"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="passwordAgain">
        <el-input type="password" v-model="form.passwordAgain"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="onSubmit('form')" type="primary" :loading="logining">修改密码</el-button>
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
        passwordOld: '',
        passwordNew: '',
        passwordAgain: ''
      },
      formRules: {
        passwordOld: [
          { required: true, message: '请输入旧密码', tirgger: 'blur' }
        ],
        passwordNew: [
          { required: true, message: '请输入新密码', tirgger: 'blur' }
        ],
        passwordAgain: [
          { required: true, message: '请再次输入新密码', tirgger: 'blur' }
        ]
      }
    }
  },
  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.form.passwordNew !== this.form.passwordAgain) {
            this.$message.error('两次密码输入不一致')
            return
          }
          this.logining = true
          this.$ajax.post(ApiUrl.modifyPasswordUrl, this.form).then(result => {
            this.$message.success(result.message)
          })
          this.logining = false
        } else {
          this.logining = false
          return false
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
.el-input{
    width: 180px;
}
</style>

