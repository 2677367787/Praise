<template>
    <div class="app-container">
        <el-form ref="form" :model="form" class="query-region"  label-position="right" label-width="120px">
            <el-form-item label="发布时间"  >
                <el-date-picker
                    v-model="form.praiseDate"
                    type="daterange"
                    align="right"
                    unlink-panels
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期">
                </el-date-picker>
                <user-input :formData="form" placeholder="发布人"></user-input>
                <el-button type="primary" @click="onQuery">Query</el-button>
                <el-button type="primary" @click="onClear">Clear</el-button>
                <el-button type="primary" @click="onAdd">发起投票</el-button>
            </el-form-item>
        </el-form>
        <el-table
            :data="tableData"
            border
            stripe
            style="width: 100%">
            <el-table-column prop="createDate" label="发布日期" width="120">
              <template slot-scope="scope">
                <span>{{scope.row.createDate | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="createBy" label="发布人" width="100">
              <template slot-scope="scope"> 
                <span>{{scope.row.createBy|parseUserName}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="type" label="投票类型" width="80">
              <template slot-scope="scope"> 
                <span>{{scope.row.type|parseType}}</span>
              </template>
            </el-table-column>
            <el-table-column
              prop="participants"
              label="参与人数"
              width="80">
            </el-table-column>
            <el-table-column
              prop="theme"
              label="主题">
            </el-table-column>
            <el-table-column prop="closeDate" label="截至日期" width="120">
              <template slot-scope="scope">
                <span>{{scope.row.closeDate | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
              </template>
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="260">
              <template slot-scope="scope">
                <el-button v-if="scope.row.createBy === scope.row.username" @click="onEdit(scope.row)" type="success" size="mini">编辑</el-button>
                <el-button @click="toDetail(scope.row)"  type="info" size="mini">投票</el-button>
                <el-button v-if="scope.row.createBy === scope.row.username"  @click="onDelete(scope.row)" type="danger" size="mini">删除</el-button>
              </template>
            </el-table-column>
        </el-table>
        <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
        </el-pagination>
        <el-dialog title="发起投票" class="demo-dynamic" :close-on-click-modal="false" :visible.sync="dialogFormVisible">
            <el-form :model="editform" ref="editform" :rules="formRules" label-width="100px" size="mini">
                <el-form-item label="投票类型">
                  <el-radio-group v-model="editform.type" @change="onTypeChange">
                    <el-tooltip class="item" effect="dark" placement="top">
                      <div slot="content">所有参与人有一个可用总分值<br/>可自由分配给可选项</div>
                      <el-radio :label="1">积分制</el-radio>
                    </el-tooltip>
                    <el-radio :label="2">单选</el-radio>
                    <el-radio :label="3">多选</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="投票主题"  prop="theme"> 
                    <el-input v-model="editform.theme" placeholder="请输入投票主题" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="详细描述" prop="additional">
                      <el-input
                      type="textarea"
                      :rows="3"
                      class="input-conten"
                      placeholder="请输入详细描述"
                      v-model="editform.additional">
                      </el-input>
                </el-form-item>
                <el-form-item label="选项类型">
                    <el-radio v-model="editform.optionType" :label="0">项目组成员作为选项</el-radio>
                    <el-radio v-model="editform.optionType" :label="1">自定义选项</el-radio>
                </el-form-item>
                <template v-if="editform.optionType === 1">
                    <el-form-item
                        v-for="(option, index) in editform.voteOptions"
                        :label="'选项' + (index+1)"
                        :key="option.key"
                        :prop="'voteOptions.' + index + '.optionText'"
                        :rules="{required: true, message: '选项不能为空', trigger: 'blur'}"
                      >
                      <el-input class="option" v-model="option.optionText"></el-input><el-button type="danger" @click.prevent="removeOption(option)">删除</el-button>
                    </el-form-item>
                </template>
                <el-form-item label="是否可选本人" prop="selectMyself" v-if="editform.optionType === '0'">
                  <el-switch v-model="editform.selectMyself"></el-switch>
                </el-form-item>
                    <el-row :gutter="0">
                      <el-col :span="8" v-if="editform.type === 1">
                        <el-form-item :label="votesText">
                          <el-input v-model="editform.votesNumber" placeholder="总分" auto-complete="off" />
                        </el-form-item>
                      </el-col>
                      <el-col :span="8">
                        <el-form-item :label="leastText" v-if="editform.type !== 2">
                          <el-input v-model="editform.leastSelect" placeholder="最少选择" auto-complete="off" />
                        </el-form-item>
                      </el-col>
                      <el-col :span="8">
                        <el-form-item :label="mostText" v-if="editform.type !== 2">
                          <el-input v-model="editform.mostSelect" placeholder="最多选择" auto-complete="off" />
                        </el-form-item>
                      </el-col>
                    </el-row>
                <el-form-item label="结束时间"  prop="closeDate">
                    <el-date-picker v-model="editform.closeDate" type="date" placeholder="结束日期"> </el-date-picker>
                </el-form-item>
                <el-form-item label="邮件通知" prop="isSendEmail">
                  <el-switch v-model="editform.isSendEmail"></el-switch>
                </el-form-item>
                <el-form-item>
                  <el-button @click="dialogFormVisible = false">取 消</el-button>
                  <el-button v-if="editform.optionType === 1" type="success" plain @click="addOption">新增选项</el-button>
                  <el-button type="primary" v-if="editform.edit === 'add'" @click="addVote('editform')">新 增</el-button>
                  <el-button type="primary" v-else @click="saveVote('editform')">修 改</el-button>
                </el-form-item>
            </el-form> 
        </el-dialog>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
import { Storage } from '@/api/storage.js'
import { index } from './ctrls/index.js'
import * as _ from 'lodash'
export default {
  data() {
    return {
      dialogEnergyVisible: false,
      dialogFormVisible: false,
      form: {
        praiseDate: [],
        userName: ''
      },
      editform: {},
      formRules: {
        theme: [
          { required: true, message: '请输入主题', tirgger: 'blur' }
        ],
        closeDate: [
          { type: 'date', required: true, message: '请输入关闭时间', trigger: 'change' }
        ]
      },
      loading: false,
      tableData: [],
      currentPage: 1,
      total: 0,
      pageNum: 1,
      pageSize: 10,
      votesText: '总分',
      mostText: '最多选择',
      leastText: '最少选择',
      enabled: false
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'nick_name',
      'name'
    ])
  },
  methods: {
    onTypeChange(item) {
      if (item === 2) {
        this.editform.votesNumber = 1
        this.editform.mostSelect = 1
        this.editform.leastSelect = 1
      }
    },
    getUserList() {
      const userList = new Storage().get('userList')
      const users = []
      for (const user in userList) {
        users.push(`${userList[user].nickName}(${user})`)
      }
      return users
    },
    getParam() {
      const param = {}
      if (this.form.praiseDate && this.form.praiseDate.length > 0) {
        param.praiseDateBegin = this.$moment(this.form.praiseDate[0]).format(
          'YYYY-MM-DD'
        )
        param.praiseDateEnd = this.$moment(this.form.praiseDate[1]).format(
          'YYYY-MM-DD'
        )
      } else {
        param.praiseDateBegin = ''
        param.praiseDateEnd = ''
      }
      return param
    },
    onQuery() {
      const param = this.getParam()
      param.userName = this.form.userName
      param.pageSize = this.pageSize
      param.pageNum = this.pageNum
      this.listLoading = true
      this.$ajax.get(this.$apiUrl.voteUrl, param).then(
        result => {
          console.log(result.data.list)
          this.tableData = result.data.list.map(map => {
            map.username = this.name
            return map
          })
          this.total = result.data.total
        }
      )
      this.listLoading = false
    },
    onClear() {
      const spaceModle = { praiseDate: [], userName: '' }
      this.form = spaceModle
    },
    handleSizeChange(page) {
      this.pageSize = page
      this.onQuery()
    },
    handleCurrentChange(page) {
      this.pageNum = page
      this.onQuery()
    },
    addVote(formName) {
      this.saveData(formName, 'post')
    },
    saveVote(formName) {
      this.saveData(formName, 'put')
    },
    saveData(formName, methodName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.editform.type === 3) {
            this.editform.votesNumber = this.editform.mostSelect
          }
          this.$ajax[methodName](this.$apiUrl.voteUrl, this.editform).then(
            result => {
              this.$message.success('保存成功!')
              this.dialogFormVisible = false
              this.onQuery()
            }
          )
        }
      })
    },
    toDetail(row) {
      this.$ajax.get(this.$apiUrl.voteOptionChoosedUrl + row.voteId).then(
        result => {
          this.$router.push({ name: 'voteDetail', params: { row, 'choose': result.data }})
        }
      )
    },
    addOption() {
      this.editform.voteOptions.push({
        optionText: '',
        key: Date.now()
      })
    },
    removeOption(item) {
      var index = this.editform.voteOptions.indexOf(item)
      if (index !== -1) {
        this.editform.voteOptions.splice(index, 1)
      }
    },
    onDelete(row) {
      this.$confirm('此操作将永久删除该任务, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.delete(this.$apiUrl.voteUrl + '/' + row.voteId).then(
          result => {
            this.$message.success('操作成功!')
            this.onQuery()
          }
        )
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    onEdit(row) {
      this.editform = _.cloneDeep(row)
      this.editform.closeDate = new Date(this.editform.closeDate)
      this.dialogFormVisible = true
    },
    onAdd() {
      this.editform = _.cloneDeep(index.orgForm)
      this.dialogFormVisible = true
    }
  },
  mounted() {
    this.onQuery()
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
.query-region {
  padding: 15px 0px;
  background: #eff0f2;
  margin-bottom: 10px;
}
.query-region .el-form-item {
  margin-bottom: 0px;
}
.el-pagination {
  padding-top: 10px;
  text-align: right;
}
.edit-region {
  padding: 0 20px;
}
.demo-dynamic {
    .el-input, .input-conten{
      margin-right: 10px;
      width: 100%;
      vertical-align: top;
    }
    .option{
      width: 80%;
    }
}

</style>
