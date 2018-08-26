<template>
<div class="login-body">
  <el-form :model="ruleForm" :rules="formRules" ref="ruleForm" class="demo-ruleForm login-container">
    <h3 class="title">系统登录</h3>
    <el-form-item prop="userName">
        <el-input v-model="ruleForm.userName" size="medium" @keyup.enter.native="submitForm('ruleForm')" auto-complete="off" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item prop="password">
        <el-input type="password" :type="pwdType" @keyup.enter.native="submitForm('ruleForm')" size="medium" v-model="ruleForm.password" auto-complete="off" placeholder="请输入密码"></el-input>
        <span class="show-pwd" @click="showPwd"><svg-icon icon-class="eye" /></span>
    </el-form-item>
    <el-form-item class="login-button">
        <el-button style="width:100%;" size="medium" @click="submitForm('ruleForm')" @keyup.enter.native="submitForm('ruleForm')" type="primary" :loading="logining">登录</el-button>
    </el-form-item>
    <el-row class="register-div"><el-button type="text" @click="dialogFormVisible=true">新用户注册</el-button></el-row>
  </el-form>
  <el-dialog title="新用户注册" :visible.sync="dialogFormVisible" :close-on-click-modal="false" width="450px" top="180px">
    <el-form :model="registerForm" ref="registerForm" :rules="registerFormRules" :status-icon="true">
      <el-form-item label="工号" label-width="80px" prop="userName">
        <el-input v-model="registerForm.userName" placeholder="工号" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="姓名" label-width="80px" prop="nickName">
        <el-input  v-model="registerForm.nickName" placeholder="姓名" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" label-width="80px" prop="password">
        <el-input v-model="registerForm.password" placeholder="密码" type="password" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" label-width="80px" prop="password2">
        <el-input v-model="registerForm.password2" placeholder="确认密码" type="password" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="性别" label-width="80px">
          <el-radio-group v-model="registerForm.sex">
              <el-radio :label="1">男</el-radio>
              <el-radio :label="0">女</el-radio>
          </el-radio-group>
      </el-form-item>
      <el-form-item label-width="80px">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" :loading="logining" @click="onRegister('registerForm')">注 册</el-button>
      </el-form-item>
    </el-form> 
  </el-dialog>
</div>
</template>

<script>
import { ApiUrl } from '@/api/apiUrl'
import { getUserInfo } from '@/utils/auth'
export default {
  data() {
    var checkUserName = (rule, value, callback) => {
      if (!value) {
        return callback('工号不能为空')
      }
      this.$ajax.get(this.$apiUrl.validUserNameUrl + this.registerForm.userName).then(result => {
        return callback()
      }, fail => {
        return callback('用户名已经存在！')
      })
    }
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.registerForm.password2 !== '') {
          this.$refs.registerForm.validateField('password2')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      logining: false,
      ruleForm: {
        userName: '',
        password: ''
      },
      formRules: {
        userName: [
          { required: true, message: '请输入用户名', tirgger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', tirgger: 'blur' }
        ]
      },
      registerForm: {
        userName: '',
        nickName: '',
        password: '',
        password2: '',
        sex: 1
      },
      registerFormRules: {
        userName: [
          { validator: checkUserName, trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: '请输入姓名', tirgger: 'blur' }
        ],
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        password2: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      },
      pwdType: 'password',
      dialogFormVisible: false,
      formLabelWidth: '120px'
    }
  },
  mounted() {
    this.onLoad()
  },
  methods: {
    onLoad() {
      const userInfo = getUserInfo()
      if (!userInfo) return
      const data = JSON.parse(userInfo)
      this.ruleForm.userName = data.userName
    },
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
    },
    onRegister(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.logining = true
          this.$ajax.post(this.$apiUrl.registerUrl, this.registerForm).then(result => {
            this.ruleForm.userName = this.registerForm.userName
            this.ruleForm.password = this.registerForm.password
            this.$message.success('注册成功!')
            this.dialogFormVisible = false
          })
          this.logining = false
        } else {
          return false
        }
      })
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
  width: 400px;
  padding: 30px 30px 10px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.title {
  margin: 0px auto 18px auto;
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

.register-div{
  text-align: right;
}
.login-button{
  margin-bottom: 0px;
}
</style>