<template>
    <el-dialog title="编辑资料" :visible.sync="dialogFormVisible" @close="onClose">
        <el-form :model="form" :rules="formRules">
            <el-form-item label="姓名" :label-width="formLabelWidth" prop="nickName">
                <el-input v-model="form.nickName" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="工号" :label-width="formLabelWidth" prop="userName"> 
                <el-input ref="txtUserName" :disabled="true"  v-model="form.userName" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="性别" :label-width="formLabelWidth">
                <el-radio-group v-model="form.sex">
                    <el-radio :label="1">男</el-radio>
                    <el-radio :label="0">女</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="个性签名" :label-width="formLabelWidth">
                <el-input
                type="textarea"
                :rows="3"
                class="input-conten"
                placeholder="请输入内容"
                v-model="form.signature">
                </el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="onClose">取 消</el-button>
            <el-button type="primary" @click="saveUserInfo()">确 定</el-button>
        </div>
    </el-dialog>
</template>
<script>
export default {
  name: 'Edit',
  data() {
    return {
      formLabelWidth: '120px',
      form: {},
      formRules: {
        nickName: [
          { required: true, message: '请输入工号', tirgger: 'blur' }
        ],
        userName: [
          { required: true, message: '请输入用户名', tirgger: 'blur' }
        ]
      },
      dialogFormVisible: this.visible,
      oldUserName: this.userName
    }
  },
  computed: {
    visibleChange() {
      return this.visible
    }
  },
  watch: {
    formData(newl) {
      this.form = newl
    },
    visibleChange(newl) {
      this.dialogFormVisible = newl
    }
  },
  mounted() {
    this.form = this.formData
  },
  methods: {
    onClose() {
      this.dialogFormVisible = false
      this.$emit('onClose')
    },
    validUserName() {
      console.log(this.oldUserName)
      if (this.oldUserName !== this.form.userName) {
        this.$ajax.get(this.$apiUrl.validUserNameUrl + this.form.userName).then(result => {
          this.saveUserInfo()
          this.$store.dispatch('LogOut').then(() => {
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
        }, fail => {
          this.$refs['txtUserName'].focus()
        })
      } else {
        this.saveUserInfo()
      }
    },
    saveUserInfo() {
      this.$ajax.put(this.$apiUrl.saveUserInfoUrl, this.form).then(result => {
        this.dialogFormVisible = false
        this.$message.success(result.message)
        this.oldUserName = this.form.userName
      })
    },
    loadingData() {
      this.loading = true
      this.$ajax.get(this.$Apiurl.getUsersInfoUrl + this.userName).then(result => {
        console.log(result.data)
        this.form = result.data
        this.imageUrl = result.data.portrait
      })
      this.loading = false
    }
  },
  props: {
    formData: {
      type: Object
    },
    visible: {
      type: Boolean,
      default: false
    },
    userName: {
      type: String
    }
  }
}
</script>
