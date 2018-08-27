<template>
    <div class="app-container">
      <el-form ref="form" :model="form" class="query-region" label-width="120px">
        <el-form-item label="周期">
            <el-date-picker
                v-model="form.praiseDate"
                type="daterange"
                align="right"
                unlink-panels
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :picker-options="pickerOptions">
            </el-date-picker>
            <user-input :formData="form"></user-input>
            <el-button type="primary" @click="query">Query</el-button>
            <el-button type="primary" @click="onClear">Clear</el-button>
        </el-form-item>
      </el-form>
      <el-table :data="list" v-loading.body="listLoading" element-loading-text="数据加载中..." 
      border fit highlight-current-row
      >
          <el-table-column label="#" type="index" width="60" align="center">
          </el-table-column>
          <el-table-column label="姓名" sortable prop="username">
              <template slot-scope="scope" >
                  <router-link :to="{name:'home',query:{username:scope.row.username}}">{{scope.row.uniqueName}}</router-link>
              </template> 
          </el-table-column>
          <el-table-column label="收到的赞" sortable prop="gain">
              <template slot-scope="scope" >
                  <el-button @click="showGainDetail(scope.row,'from',scope.$index)"type="text" v-if="scope.row.gain > 0" size="small">{{scope.row.gain}}　　(详细)</el-button> 
                  <div v-else>0</div>
              </template> 
          </el-table-column>
          <el-table-column label="送出的赞" sortable prop="give">
              <template slot-scope="scope">
                    <el-button @click="showGainDetail(scope.row,'to',scope.$index)" v-if="scope.row.give > 0" type="text" size="small">{{scope.row.give}}　　(详细)</el-button> 
                  <div v-else>0</div>
              </template>
          </el-table-column>
          <el-table-column label="快速点赞">
              <template slot-scope="scope">
                    <el-button @click="quickPraise(scope.row)" type="text" size="small">快速点赞</el-button>
              </template>
          </el-table-column>
      </el-table>
      <el-dialog id="showMsg" title="详细信息" :visible.sync="dialogFormVisible">
        <div slot="title">{{master}}{{action}}</div>  
        <el-row>
          <el-col :span="24">
            <template v-for="(user,index) in pariseList">  
              <div class="jimi_lists lim_operator clearfix">
                <div class="header_img jimi3 fl">
                  <svg-icon :icon-class="'defaultAvatar'" class="user-avatat"></svg-icon>
                </div>
                <div class="unique-name">{{user.nickName}}({{(user.userName)}})&nbsp;&nbsp;&nbsp;<span class="time">{{user.createDate|parseTime('{m}-{d} {h}:{i}')}}</span></div>
                <div :class="(index+1)%2==0?'lim_bubble':'lim_bubble2'">
                  <p class="lim_dot">{{user.content}}</p>
                </div>
                <div :class="(index+1)%2==0?'lim_tale':'lim_tale2'">
                  <div class="radiusborder"></div>
                </div>
              </div>
            </template>
          </el-col>
        </el-row>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="Pre()">上一位</el-button>
          <el-button type="primary" @click="Next()">下一位</el-button>
        </div>
      </el-dialog>
      <el-dialog title="快速点赞" :visible.sync="quickPraiseDialog">
        <div slot="title">快速点赞</div>  
        <el-row>
          <el-col :span="24">
              <el-form ref="editform" :model="editform" label-width="80px">
                <el-form-item label="姓名">
                  <el-input v-model="editform.uniqueName" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="内容">
                  <el-input
                  type="textarea"
                  :rows="4"
                  class="input-conten"
                  placeholder="请输入内容"
                  v-model="editform.content">
                  </el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="praiseAdd('form')" :loading="logining">点赞</el-button>
                </el-form-item>
              </el-form>
          </el-col>
        </el-row>
      </el-dialog>
    </div>
</template>

<script>
import { PraiseVo } from './data/PraiseVo'
import { ApiUrl } from '@/api/apiUrl'
import UserInput from '@/components/UserInput/index'
export default {
  components: {
    UserInput
  },
  data() {
    return {
      list: '',
      listLoading: true,
      tabledata: PraiseVo.tabledata,
      dialogFormVisible: false,
      quickPraiseDialog: false,
      logining: false,
      currIndex: 0,
      form: {
        praiseDate: [],
        userName: ''
      },
      editform: {
        content: '',
        uniqueName: '',
        praiseTo: ''
      },
      pariseList: '',
      master: '',
      action: '',
      actionType: '',
      pickerOptions: {
        shortcuts: [{
          text: '最近二周',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 14)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      }
    }
  },
  created() {
    this.getCycle()
  },
  methods: {
    getCycle() {
      this.$ajax.get(this.$apiUrl.getNewestMeeting).then(result => {
        const end = new Date()
        const start = new Date()
        if (start.getTime() > (result.data.end + 3600 * 1000 * 24)) {
          start.setTime(result.data.end + 3600 * 1000 * 24)
          this.form.praiseDate = [start, end]
        } else {
          start.setTime(result.data.start)
          end.setTime(result.data.end)
          this.form.praiseDate = [start, end]
        }
        this.query()
      })
    },
    getParam() {
      const param = {}
      if (this.form.praiseDate && this.form.praiseDate.length > 0) {
        param.praiseDateBegin = this.$moment(this.form.praiseDate[0]).format('YYYY-MM-DD')
        param.praiseDateEnd = this.$moment(this.form.praiseDate[1]).format('YYYY-MM-DD')
      } else {
        param.praiseDateBegin = ''
        param.praiseDateEnd = ''
      }
      return param
    },
    query() {
      console.log(2)
      const param = this.getParam()
      param.userName = this.form.userName
      this.listLoading = true
      this.$ajax.get(ApiUrl.praiseListUrl, param).then(result => {
        this.list = result.data
        this.listLoading = false
      }, fail => {
        this.listLoading = false
      })
    },
    showGainDetail(row, type, index) {
      this.master = row.uniqueName
      this.dialogFormVisible = true
      this.currIndex = index
      this.getDetailData(row, type)
      this.listLoading = false
    },
    onClear() {
      const spaceModle = { praiseDate: [], userName: '' }
      this.form = spaceModle
    },
    quickPraise(row) {
      this.quickPraiseDialog = true
      this.editform.uniqueName = row.uniqueName
      this.editform.praiseTo = row.username
    },
    praiseAdd() {
      if (!this.editform.praiseTo) {
        this.$message.error('异常,未找到工号,请刷新页面!')
        return false
      }
      if (!this.editform.content) {
        this.$message.error('请输入点赞内容!')
        return false
      }
      this.logining = true
      this.$ajax.post(ApiUrl.praiseAddUrl, this.editform).then(result => {
        this.logining = false
        this.$message.success('点赞成功!')
        this.editform.content = ''
        this.query()
        this.quickPraiseDialog = false
      }, result => {
        this.logining = false
      })
    },
    Next() {
      const index = this.currIndex
      if (index + 1 < this.list.length) {
        this.currIndex++
        const row = this.list[this.currIndex]
        this.master = row.uniqueName
        this.getDetailData(row, this.actionType)
      }
    },
    Pre() {
      const index = this.currIndex
      if (index - 1 >= 0) {
        this.currIndex--
        const row = this.list[this.currIndex]
        this.master = row.uniqueName
        this.getDetailData(row, this.actionType)
      }
    },
    getDetailData(row, type) {
      const param = this.getParam()
      if (type === 'from') {
        param.praiseTo = row.praiseTo
        this.action = '收到'
        this.actionType = 'from'
      } else {
        param.praiseFrom = row.praiseFrom
        this.action = '送出'
        this.actionType = 'to'
      }
      this.$ajax.get(ApiUrl.praiseDetailUrl, param).then(result => {
        this.pariseList = result.data
      })
    }
  }
}
</script>
<style lang="scss" rel="stylesheet/scss"  scoped>
  a {
    color: #66b1ff;
  }
  .el-row {
    max-height: 350px;
    overflow-y: auto;
  }
  .el-dialog__body{
    padding: 0px;
  }
  .query-region{
    padding: 15px 0px;
    background: #EFF0F2;
    margin-bottom: 10px;
  }
  .el-form-item {
    margin-bottom: 15px;
    &:last-child {
        margin-bottom: 0;
    }
  }
  .jimi_lists { 
      margin: 15px 10px;
      position: relative;
  }

  .clearfix:after {
      content: " .";
      display: block;
      clear: both;
      visibility: hidden;
      height: 0;
  }

  .jimi_lists .header_img {
      width: 60px;
      height: 60px;
      top: 0;
      left: 0;
  }

  .lim_dot {
      padding: 5px;
      margin: 0px;
      color: black;
      font-size: 17px;
  }

  .fl {
      float: left;
  }

  .lim_bubble {
      float: left;
      border: 1px solid #a5d01b;
      background: #c8f064;
      margin-left: 10px;
      min-height: 30px;
      padding: 0 5px;
      border-radius: 5px;
      min-width: 200px;
      max-width: 80%;
  }
  .lim_bubble2 {
      float: left;
      border: 1px solid #a5d01b;
      background: #fff;
      margin-left: 10px;
      min-height: 30px;
      padding: 0 5px;
      border-radius: 5px;
      min-width: 200px;
      max-width: 80%;
  }

  .lim_tale {
      left: -1px;
      background-color: #c8f064;
      border-color: #a5d01b;
      margin-left: 61px;
      position: absolute;
      width: 12px;
      height: 8px;
      overflow: hidden !important;
      top: 31px;
      z-index: 2;
      border-top-style: solid;
      border-top-width: 1px;
  }

  .lim_tale2 {
      left: -1px;
      background-color: #fff;
      border-color: #a5d01b;
      margin-left: 61px;
      position: absolute;
      width: 12px;
      height: 8px;
      overflow: hidden !important;
      top: 31px;
      z-index: 2;
      border-top-style: solid;
      border-top-width: 1px;
  }
  .radiusborder {
      position: absolute;
      background-color: #fff;/*#EFF0F2; */
      top: -29px;
      left: -94px;
      height: 160px;
      width: 160px;
      border-top-style: solid;
      border-top-width: 1px;
      border-right-style: solid;
      border-right-width: 1px;
      border-top-right-radius: 154px;
      border-color: #a5d01b;
  }
  
  p {
      font-size: 10px;
      padding: 0px;
      margin: 0px;
  }

  .unique-name {
      margin-left: 70px;
      min-width: 200px;
      font-size: 10px;
  }
  .user-avatat{
    height: 60px;
    width: 60px;
  }
  .time {
    color:gray;
  }
</style>
