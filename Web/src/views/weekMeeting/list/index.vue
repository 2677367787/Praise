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
                <el-button type="primary" @click="onQuery">Query</el-button>
                <el-button type="primary" @click="onClear">Clear</el-button>
                <el-button type="primary" @click="onNewMeeting()">发布新会议</el-button>
            </el-form-item>
        </el-form>
        <el-table
            :data="tableData"
            border
            stripe
            style="width: 100%">
            <el-table-column label="2018年" width="220">
              <template slot-scope="scope">
                <span>第{{scope.row.tally}}次</span>
              </template>
            </el-table-column>
            <el-table-column label="会议时间" width="220">
              <template slot-scope="scope">
                <span>{{scope.row.start | parseTime('{y}-{m}-{d}')}}</span>&nbsp;至&nbsp;
                <span>{{scope.row.end | parseTime('{y}-{m}-{d}')}}</span>
              </template>
            </el-table-column>
            <el-table-column
            prop="theme"
            label="会议主题"
            width="220">
            </el-table-column>
            <el-table-column
            prop="content"
            label="会议内容">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="120">
              <template slot-scope="scope">
                <el-button
                  @click="handleClick(scope.row)"
                  type="text"
                  size="small">
                  编辑
                </el-button>
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
        <el-dialog title="新会议" class="edit-region" :visible.sync="dialogFormVisible">
            <el-form :model="editform" ref="editform" :rules="formRules" label-width="80px">
                <el-form-item label="第几次"  prop="tally"> 
                    <el-input v-model="editform.tally" placeholder="请输入第几次会议" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="会议时间" required>
                  <el-col :span="8">
                    <el-form-item prop="start">
                      <el-date-picker type="date" placeholder="选择日期" v-model="editform.start" style="width: 100%;"></el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col class="line" :span="1">-</el-col>
                  <el-col :span="8">
                    <el-form-item prop="end">
                      <el-date-picker type="date" placeholder="选择日期" v-model="editform.end" style="width: 100%;"></el-date-picker>
                    </el-form-item>
                  </el-col>
                </el-form-item>
                <el-form-item label="主题"  prop="theme"> 
                    <el-input v-model="editform.theme" placeholder="请输入主题" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="内容" prop="content">
                      <el-input
                      type="textarea"
                      :rows="4"
                      class="input-conten"
                      placeholder="请输入内容"
                      v-model="editform.content">
                      </el-input>
                </el-form-item>
                <el-form-item label="邮件通知">
                  <el-switch v-model="editform.isSendEmail"></el-switch>
                </el-form-item>
                <el-form-item>
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveTasks('editform')">确 定</el-button>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">

            </div>
        </el-dialog>
    </div>
</template>
<script>
export default {
  data() {
    return {
      dialogFormVisible: false,
      form: {
        praiseDate: [],
        userName: ''
      },
      editform: {
        theme: '',
        content: '',
        isSendEmail: '',
        start: '',
        end: '',
        tally: ''
      },
      formRules: {
        theme: [
          { required: true, message: '请输入主题', tirgger: 'blur' }
        ],
        content: [
          { required: true, message: '请输入内容', tirgger: 'blur' }
        ],
        start: [
          { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
        ],
        end: [
          { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
        ]
      },
      loading: false,
      tableData: [],
      currentPage: 1,
      total: 0,
      pageNum: 1,
      pageSize: 10
    }
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      console.log(rowIndex)
      if (rowIndex % 2 === 0) {
        return 'warning-row'
      } else {
        return 'success-row'
      }
    },
    getParam() {
      const param = {}
      console.log(this.form.praiseDate)

      if (this.form.praiseDate && this.form.praiseDate.length > 0) {
        console.log('if')
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
      this.$ajax.get(this.$apiUrl.getMeetingListUrl, param).then(
        result => {
          this.tableData = result.data.list
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
    saveTasks(formName) {
      let method
      if (this.editform.action === 'Add') {
        method = 'post'
      } else {
        method = 'put'
      }

      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$ajax[method](this.$apiUrl.meetingUrl, this.editform).then(
            result => {
              this.$message.success('保存成功!')
              this.dialogFormVisible = false
              this.onQuery()
            }
          )
        } else {
          return false
        }
      })
    },
    handleClick(row) {
      this.editform.action = 'Edit'
      this.editform = row
      this.dialogFormVisible = true
    },
    onNewMeeting() {
      this.editform.action = 'Add'
      this.dialogFormVisible = true
    }
  },
  mounted() {
    this.onQuery()
  }
}
</script>
<style rel="stylesheet/scss" scoped>
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
.line{
  text-align: center;
}
</style>
