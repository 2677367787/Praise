<template>
    <div class="app-container" v-loading="loading">
        <div class="card">
            <div class="card-head">
            </div>
            <div class="card-main">
                <div class="card-img-border"  @mouseover="onMousemove()" @mouseout="onMouseout()">
                    <div class="card-img-padding">
                        <img class="card-img" width="160" height="160" src="https://pic2.zhimg.com/f7458a3e9_xl.jpg">
                    </div>
                    <el-upload
                        class="avatar-uploader"
                        action="http://localhost:8088/users/fileUpload"
                        name="file"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        
                            <div id="upload-avatar-div" class="Mask UserAvatarEditor-mask Mask-hidden" @click="uploadAvatar()">
                                <div class="Mask-mask Mask-mask--black UserAvatarEditor-maskInner"></div>
                                <div class="Mask-content">
                                    <svg class="Zi Zi--Camera UserAvatarEditor-cameraIcon" fill="currentColor" viewBox="0 0 24 24" width="36" height="36">
                                        <path d="M20.094 6S22 6 22 8v10.017S22 20 19 20H4.036S2 20 2 18V7.967S2 6 4 6h3s1-2 2-2h6c1 0 2 2 2 2h3.094zM12 16a3.5 3.5 0 1 1 0-7 3.5 3.5 0 0 1 0 7zm0 1.5a5 5 0 1 0-.001-10.001A5 5 0 0 0 12 17.5zm7.5-8a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" fill-rule="evenodd"></path>
                                    </svg>
                                    <div class="UserAvatarEditor-maskInnerText">修改我的头像</div>
                                </div>
                            </div>
                        
                    </el-upload>
                </div>
                <div class="card-content">
                    <h1 class="card-nickname">{{form.nickName}}</h1>
                    <div><span>{{form.signature}}</span></div>
                    <div class="box-user-avatat">
                        <svg-icon :icon-class="'boy'" v-if="form.sex===1" class="user-avatat"></svg-icon>
                        <svg-icon :icon-class="'girl'" v-else class="user-avatat"></svg-icon>
                    </div>
                    <div class="card-tag">
                        <el-tag
                        :key="tag"
                        v-for="tag in dynamicTags"
                        size="medium"
                        :disable-transitions="false"
                        @close="handleClose(tag)">
                        {{tag}}
                        </el-tag>
                        <el-input
                        class="input-new-tag"
                        v-if="inputVisible"
                        v-model="inputValue"
                        ref="saveTagInput"
                        size="small"
                        @keyup.enter.native="handleInputConfirm"
                        @blur="handleInputConfirm"
                        >
                        </el-input>
                        <el-button v-else class="button-new-tag" size="small" @click="showInput">+印象标签</el-button>
                    </div>
                    <div class="edit-button"><el-button type="primary" plain @click="dialogFormVisible = true">编辑个人资料</el-button></div> 
                </div>
            </div>
        </div>
        <div class="achievement">
            <div class="Card-Small Profile-sideColumn">
                <div class="Card-header">
                    <div class="Card-headerText">当前</div>
                </div>
                <div class="Profile-sideColumnItem">
                    <div class="IconGraf">
                        <div class="IconGraf-iconWrapper">
                            <svg-icon :icon-class="'praise'" class="user-avatat"></svg-icon>
                        </div>
                        获得 7 次赞同
                    </div>
                    <div class="Profile-sideColumnItemValue">送出 7 次赞同</div>
                </div>
            </div>
            <div class="Card-Small Profile-sideColumn">
                <div class="Card-header">
                    <div class="Card-headerText">历史</div>
                </div>
                <div class="Profile-sideColumnItem">
                    <div class="IconGraf">
                        <div class="IconGraf-iconWrapper">
                            <svg-icon :icon-class="'praise'" class="user-avatat"></svg-icon>
                        </div>
                        获得 7 次赞同
                    </div>
                    <div class="Profile-sideColumnItemValue">送出 7 次赞同</div>
                </div>
            </div>
        </div>
        <div class="dynamic">
            <div></div>
            <div class="tag-content">2018年5月6日 14:16:17  张三赞了你</div>
            <div class="tag-content">2018年5月6日 14:16:17  李四赞了你</div>
            <div class="tag-content">2018年5月6日 14:16:17  你赞了王武</div>
        </div>
        <el-dialog title="编辑资料" :visible.sync="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="姓名" :label-width="formLabelWidth">
                    <el-input v-model="form.nickName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="工号" :label-width="formLabelWidth">
                    <el-input v-model="form.userName" auto-complete="off"></el-input>
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
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import { ApiUrl } from '@/api/apiUrl'
export default {
  name: 'home',
  data() {
    return {
      dialogFormVisible: false,
      dynamicTags: ['标签一', '标签二', '标签三'],
      inputVisible: false,
      inputValue: '',
      imageUrl: '',
      form: {
        nickName: '',
        userName: '',
        sex: '',
        signature: ''
      },
      formRules: {
        nickName: [
          { required: true, message: '请输入工号', tirgger: 'blur' }
        ],
        userName: [
          { required: true, message: '请输入用户名', tirgger: 'blur' }
        ]
      },
      formLabelWidth: '120px',
      loading: false
    }
  },
  methods: {
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1)
    },

    showInput() {
      this.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },
    handleInputConfirm() {
      const inputValue = this.inputValue
      if (inputValue) {
        this.dynamicTags.push(inputValue)
      }
      this.inputVisible = false
      this.inputValue = ''
    },
    loadingData() {
      this.loading = true
      this.$ajax.get(ApiUrl.getUsersInfoUrl).then(result => {
        console.log(result.data)
        this.form = result.data
      })
      this.loading = false
    },
    onMousemove() {
      console.log('move')
      var div = document.getElementById('upload-avatar-div')
      div.classList.remove('Mask-hidden')
    },
    onMouseout() {
      var div = document.getElementById('upload-avatar-div')
      div.classList.add('Mask-hidden')
    },
    uploadAvatar() {

    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  },
  mounted() {
    this.loadingData()
  }
}
</script>
<style lang="scss" scoped>
.app-container {
    background: #f6f6f6;
    padding: 20px;
}

.card{
    background:#ffffff;
    padding-bottom: 50px;
    .card-head {
        height: 50px;
        background: #66b1ff
    }
    .card-main {
        position: relative;
        margin: 0 20px 24px;
        .card-img-border {
            cursor: pointer;
            position: relative;
            top: -25px;
            float: left;
            .card-img-padding {
                display: inline-block;
                overflow: hidden;
                vertical-align: top;
                background-color: #fff;
                border: 4px solid #fff;
                border-radius: 8px;
                .card-img {
                    background: #fff;
                    border-radius: 2px;
                    border-radius: 4px;
                    vertical-align: top;
                }
            }
        }
    }
    .card-content {
        padding-left: 20px;
        border-left: 164px solid transparent;
        .card-nickname{
            padding: 10px 0px;
        }
        .box-user-avatat{
            .user-avatat{
                width: 32px;
                height: 32px;
            }
        }
        .edit-button{
            margin-top: 5px;
        }
        .edit-button{
            float: right;
        }
    }
}

h1 {
    padding: 0px;
    margin: 0px;
}

.el-tag + .el-tag {
    margin-left: 10px;
}
.button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}
.input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
}

.Card-Small {
    margin: 10px 0px;
    background: #fff;
    overflow: hidden;
    border-radius: 2px;
    box-shadow: 0 1px 3px rgba(26, 26, 26, .1);
    box-sizing: border-box;
    color: #646464;
    margin-right: 30px;
    float: left;
}
.Card-header {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    height: 50px;
    padding: 0 20px;
    border-bottom: 1px solid #f6f6f6;
    box-sizing: border-box;
}
.Profile-sideColumnItem {
    padding: 18px 20px;
}
.IconGraf-icon {
    fill: #8590a6;
}

.IconGraf-iconWrapper {
    display: inline-block;
    width: 28px;
}
.Card-headerText {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    font-weight: 600;
    font-synthesis: style;
}

.Profile-sideColumnItemValue {
    margin-top: 6px;
    margin-left: 29px;
    font-size: 14px;
    color: #8590a6;
}
.Profile-sideColumn {
    width: 296px;
    color: #646464;
}
.achievement{
    height: 146px;
    padding: 10px 0px;
}
.dynamic {
    margin: 20px 0px;
    padding: 0px 10px;
    box-shadow: 0 1px 3px rgba(26, 26, 26, 0.1);
    background: #fff;
    .tag-content{
        background: #fff;
        padding: 5px 0px;
    }
}

.Mask-hidden {
    pointer-events: none;
    opacity: 0;
}
.Mask {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 1;
    transition: opacity .2s ease-in;
}

.UserAvatarEditor-maskInner {
    z-index: 4;
    border: 4px solid #fff;
    border-radius: 8px;
}
.Mask-mask--black {
    background: #1a1a1a;
}
.Mask-mask {
    position: absolute;
    z-index: -1;
    width: 100%;
    height: 100%;
    opacity: .4;
    box-sizing: border-box;
}

.Mask-content {
    position: absolute;
    top: 50%;
    left: 50%;
    z-index: 5;
    color: #fff;
    text-align: center;
    -webkit-transform: translate(-50%,-50%);
    transform: translate(-50%,-50%);
}

.UserAvatarEditor-cameraIcon {
    margin-bottom: 14px;
    fill: #fff;
}

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    display: block;
}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}
.avatar-uploader-icon {
    color: #8c939d;
    width: 160px;
    height: 160px;
    text-align: center;
}
.avatar {
    width: 150px;
    height: 150px;
    display: block;
}
</style>

