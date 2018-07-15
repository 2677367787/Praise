<template>
    <div class="app-container" v-loading="loading">
        <div class="card">
            <div class="card-head">
            </div>
            <div class="card-main">
                <div class="card-img-border"  @mouseover="onMousemove()" @mouseout="onMouseout()">
                    <div class="card-img-padding">
                        <img class="card-img" :src="imageUrl" width="160" height="160">
                    </div>
                    <el-upload
                        style="display:block;"
                        v-if="this.isOneself"
                        class="avatar-uploader"
                        action="http://localhost:8088/users/fileUpload"
                        :headers="header"
                        name="file"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload">
                        <div id="upload-avatar-div" class="Mask UserAvatarEditor-mask Mask-hidden">
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
                    <div>
                        <span v-if="form.signature">{{form.signature}}</span>
                        <span v-else>这个人很懒,什么都没有填写</span>
                    </div>
                    <div class="box-user-avatat">
                        <svg-icon :icon-class="'boy'" v-if="form.sex===1" class="user-avatat"></svg-icon>
                        <svg-icon :icon-class="'girl'" v-else class="user-avatat"></svg-icon>
                    </div>
                    <impress-tag ref="impressTag" :userName="username" v-if="!this.isOneself" @onSave="onSave"></impress-tag>
                    <div class="edit-button"><el-button type="primary" v-if="this.isOneself" plain @click="dialogFormVisible = true">编辑个人资料</el-button></div>
                </div>
            </div>
        </div>
        <el-row style="padding-top:20px;">
            <el-col :span="24" >
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>获赞画像</span>
                    </div>
                    <pie-chart ref="praiseFrom"></pie-chart>
                </el-card>

                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>点赞画像</span>
                    </div>
                    <pie-chart ref="praiseTo"></pie-chart>
                </el-card>
            </el-col>
        </el-row>
        <el-card class="dynamic">
            <div slot="header" class="clearfix">
                <span>动态</span>
            </div>
            <div class="tag-content" v-for="log in logs">
                {{log.createDate|parseTime('{y}-{m}-{d} {h}:{i}:{s}')}}&nbsp;&nbsp;
                {{log.beFrom|parseUserName}}&nbsp;&nbsp;
                <span v-if="isOneself">给你</span>
                <span v-else>给TA</span>{{log.type|logTypeConver}}
            </div>
        </el-card>
        <edit :formData="form" :userName="username" :visible="dialogFormVisible" @onClose="onClose"></edit>
    </div>
</template>
<script>
import { ApiUrl } from '@/api/apiUrl'
import store from '@/store'
import { getToken } from '@/utils/auth'
import { mapGetters } from 'vuex'
import impressTag from './views/impressTag'
import PieChart from './views/PieChart'
import Edit from './views/Edit'

export default {
  name: 'home',
  components: {
    impressTag, PieChart, Edit
  },
  data() {
    return {
      dialogFormVisible: false,
      imageUrl: '',
      isOneself: true,
      form: {
        nickName: '',
        userName: '',
        sex: '',
        signature: ''
      },
      logs: {},
      loading: false,
      username: '',
      contentData: [],
      titleData: []
    }
  },
  methods: {
    loadingData() {
      this.loading = true
      this.$ajax.get(ApiUrl.getUsersInfoUrl + this.username).then(result => {
        console.log(result.data)
        this.form = result.data
        this.imageUrl = result.data.portrait
      })
      this.loading = false
    },
    onMousemove() {
      console.log('move')
      var div = document.getElementById('upload-avatar-div')
      if (div) div.classList.remove('Mask-hidden')
    },
    onMouseout() {
      var div = document.getElementById('upload-avatar-div')
      if (div) div.classList.add('Mask-hidden')
    },
    onSave(data) {
      if (!this.username) {
        return
      }
      const param = { userName: this.username, tag: data }
      this.$ajax.post(this.$apiUrl.impressTagAddUrl, param).then(result => {
        this.$refs.impressTag.loadData()
      })
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
        return
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
        return
      }
    },
    loadingPieChart() {
      this.$ajax.get(ApiUrl.praiseToPortraitUrl + this.username).then(result => {
        const contentData = result.data.map(m => {
          const { name, praiseNum: value } = m
          return { name, value }
        })
        const titleData = result.data.map(m => {
          return m.name
        })
        const tips = '送出'
        const data = { contentData, titleData, tips }
        this.$refs.praiseTo.initData(data)
        this.$refs.praiseTo.initChart()
      })

      this.$ajax.get(ApiUrl.praiseFromPortraitUrl + this.username).then(result => {
        const contentData = result.data.map(m => {
          const { name, praiseNum: value } = m
          return { name, value }
        })
        const titleData = result.data.map(m => {
          return m.name
        })
        const tips = '获的'
        const data = { contentData, titleData, tips }
        this.$refs.praiseFrom.initData(data)
        this.$refs.praiseFrom.initChart()
      })

      this.$ajax.get(ApiUrl.getRecordByRelevant + this.username).then(result => {
        this.logs = result.data
      })
    },
    onClose() {
      this.dialogFormVisible = false
    }
  },
  created() {
    console.log(this.$route.query.username + '---' + this.name)
    if (this.$route.query.username && this.name !== this.$route.query.username) {
      this.username = this.$route.query.username
      this.isOneself = false
    } else {
      this.username = this.name
      this.isOneself = true
    }
    this.loadingPieChart()
  },
  mounted() {
    this.loadingData()
  },
  computed: {
    header() {
      let token
      if (store.getters.token) {
        token = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
      } else {
        token = store.getters.token
      }
      return { 't-token': token }
    },
    ...mapGetters([
      'name'
    ])
  }
}
</script>
<style lang="scss" scoped>
.app-container {
    background: #f6f6f6;
    padding: 20px;
}

.box-card {
    width: 480px;
    float: left;
    margin-right: 20px;
}

.card{
    background:#ffffff;
    padding-bottom: 20px;
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
.avatar-uploader{
    height: 0px;
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;

}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}
.avatar-uploader-icon {
    color: #8c939d;
    width: 160px;
    height: 142px;
    text-align: center;
}
.avatar {
    width: 150px;
    height: 140px;
    display: block;
}
.el-upload {
    display: block !important;
}
</style>

