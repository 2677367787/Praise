<template>
    <div class="app-container">
      <div>
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
            <user-input ref="userInput" :data="form" :fieldName="'userNo'"></user-input>
            <el-button type="primary" @click="query">Query</el-button>
            <el-button type="primary" @click="onClear">Clear</el-button>
        </el-form-item>
      </el-form>
      </div>
      <el-table  :data="list" v-loading.body="listLoading" element-loading-text="数据加载中..." border fit highlight-current-row>
          <el-table-column prop="date" label="#" type="index" width="60" align="center">
          </el-table-column>
          <el-table-column prop="uniqueName" label="姓名">
          </el-table-column>
          <el-table-column label="收到的赞">
              <template slot-scope="scope" >
                  <el-button @click="showGainDetail(scope.row)"type="text" v-if="scope.row.gain > 0" size="small">{{scope.row.gain}}　　(详细)</el-button> 
                  <div v-else>0</div>
              </template> 
          </el-table-column>
          <el-table-column label="送出的赞">
              <template slot-scope="scope">
                  <el-button type="text" size="small">{{scope.row.give}}</el-button> 
              </template>
          </el-table-column>
      </el-table>
      <el-dialog title="详细信息" :visible.sync="dialogFormVisible">
        <div slot="title">{{master}}收到</div>  
        <el-row>
          <el-col :span="24">
            <template v-for="(user,index) in pariseList">  
              <div class="jimi_lists lim_operator clearfix">
                <div class="header_img jimi3 fl">
                  <svg-icon :icon-class="'defaultAvatar'" class="user-avatat"></svg-icon>
                </div>
                <div class="unique-name">{{user.nickName}}{{(user.userName)}}</div>
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
      list: null,
      listLoading: true,
      tabledata: PraiseVo.tabledata,
      dialogFormVisible: false,
      form: {
        praiseDate: [],
        userName: ''
      },
      pariseList: '',
      master: '',
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
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  created() {
    const end = new Date()
    const start = new Date()
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 14)
    this.form.praiseDate = [start, end]
    this.query()
  },
  methods: {
    getParam() {
      const param = {}
      if (this.form.praiseDate && this.form.praiseDate.length > 0) {
        console.log(this.form.praiseDate[1])
        param.praiseDateBegin = this.$moment(this.form.praiseDate[0]).format('YYYY-MM-DD')
        param.praiseDateEnd = this.$moment(this.form.praiseDate[1]).add(1, 'days').format('YYYY-MM-DD')
      } else {
        param.praiseDateBegin = ''
        param.praiseDateEnd = ''
      }
      return param
    },
    query() {
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
    showGainDetail(row) {
      this.master = row.uniqueName
      this.dialogFormVisible = true
      const param = this.getParam()
      param.praiseTo = row.praiseTo
      this.$ajax.get(ApiUrl.praiseDetailUrl, param).then(result => {
        this.pariseList = result.data
        console.log(result.data)
      })
      this.listLoading = false
    },
    onClear() {
      const spaceModle = { praiseDate: [], userName: '' }
      this.form = spaceModle
    },
    onRead() {
      alert(1)
    }
  }
}
</script>
<style rel="stylesheet/scss"  scoped>
  .query-region{
    padding: 15px 0px;
    background: #EFF0F2;
    margin-bottom: 10px;
  }
  .el-form-item {
      margin-bottom: 0px;
  }
  .jimi_lists { 
      margin: 10px;
      position: relative;
  }

  .clearfix:after {
      content: " .";
      display: block;
      clear: both;
      visibility: hidden;
      height: 0;
  }

  .jimi_lists .jimi3 {
      /* background: url(./img/mine.jpg) no-repeat 0 0; */
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
</style>
