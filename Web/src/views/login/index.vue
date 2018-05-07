<template>
<div class="login-body">
  <el-form :model="ruleForm" :rules="formRules" ref="ruleForm" class="demo-ruleForm login-container">
    <h3 class="title">系统登录</h3>
    <el-form-item prop="userName">
        <el-input v-model="ruleForm.userName" size="big" auto-complete="off" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item prop="password">
        <el-input type="password" :type="pwdType" size="big" v-model="ruleForm.password" auto-complete="off" placeholder="请输入密码"></el-input>
        <span class="show-pwd" @click="showPwd"><svg-icon icon-class="eye" /></span>
    </el-form-item>
    <el-form-item>
        <el-button style="width:100%;" size="big" @click="submitForm('ruleForm')" type="primary" :loading="logining">登录</el-button>
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
      ruleForm: {
        userName: '6396000749',
        password: '123456'
      },
      formRules: {
        userName: [
          { required: true, message: '请输入用户名', tirgger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', tirgger: 'blur' }
        ]
      },
      pwdType: 'password'
    }
  },
  methods: {
    submitForm(formName) {
      this.logining = true
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.url = ApiUrl.loginUrl
          this.$store.dispatch('Login', this.ruleForm).then(result => {
            this.logining = false
            this.$router.push({ path: '/' })
          }, result => {
            this.logining = false
          })
        } else {
          this.logining = false
          return false
        }
      })
    },
    showPwd() {
      if (this.pwdType) {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
$dark_gray:#889aa4;
.login-body {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  width: 100%;
  height: 100%;
  background-size: 110% 100%;
}
.login-container {
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 420px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
.show-pwd {
  position: absolute;
  right: 10px;
  top: 7px;
  font-size: 16px;
  color: $dark_gray;
  cursor: pointer;
  user-select:none;
}
</style>