<template>
    <div class="app-container">
        <el-form ref="form" :model="form" class="query-region"  label-position="right" label-width="120px">
            <el-form-item label="点赞时间"  >
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
            </el-form-item>
        </el-form>
        <el-table
            :data="tableData"
            border
            stripe
            style="width: 100%">
            <el-table-column
              type="index"
              width="50">
            </el-table-column>
            <el-table-column prop="createDate" label="点赞时间" width="120">
              <template slot-scope="scope">
                <span>{{scope.row.createDate | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="praiseTo" label="To" width="150">
              <template slot-scope="scope"> 
                <span>{{scope.row.praiseTo|parseUserName}}</span>
              </template>
            </el-table-column>
            <el-table-column
            prop="content"
            label="点赞内容">
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
                <el-button
                  @click="deleteClick(scope.row)"
                  type="text"
                  size="small">
                  删除
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
          <div slot="title">快速点赞</div>  
          <el-row>
            <el-col :span="24">
                <el-form ref="editform" :model="editform" label-width="80px">
                  <el-form-item label="姓名">
                     <span>{{editform.praiseTo|parseUserName}}</span>
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
                    <el-button type="primary" @click="updatePraise('form')" :loading="loading">点赞</el-button>
                  </el-form-item>
                </el-form>
            </el-col>
          </el-row>     
        </el-dialog>
    </div>
</template>
<script>
import * as _ from 'lodash'
export default {
  data() {
    return {
      dialogFormVisible: false,
      form: {
        praiseDate: []
      },
      editform: {
        content: '',
        uniqueName: '',
        praiseTo: ''
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
    getParam() {
      const param = {}
      if (this.form.praiseDate && this.form.praiseDate.length > 0) {
        param.beginDate = this.$moment(this.form.praiseDate[0]).format(
          'YYYY-MM-DD'
        )
        param.endDate = this.$moment(this.form.praiseDate[1]).format(
          'YYYY-MM-DD'
        )
      } else {
        param.beginDate = ''
        param.endDate = ''
      }
      return param
    },
    onQuery() {
      const param = this.getParam()
      param.pageSize = this.pageSize
      param.pageNum = this.pageNum
      this.listLoading = true
      this.$ajax.get(this.$apiUrl.myPraise, param).then(
        result => {
          this.tableData = result.data.list
          this.total = result.data.total
        }
      )
      this.listLoading = false
    },
    onClear() {
      const spaceModle = { praiseDate: [] }
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
    updatePraise(formName) {
      this.$ajax.put(this.$apiUrl.praiseAddUrl, this.editform).then(
        result => {
          this.$message.success('保存成功!')
          this.dialogFormVisible = false
          this.editform = {}
          this.onQuery()
        }
      )
    },
    deleteClick(row) {
      this.$confirm('此操作将永久删除该内容, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.delete(this.$apiUrl.praiseAddUrl + '/' + row.praiseId).then(
          result => {
            this.$message.success('删除成功!')
            this.onQuery()
          }
        )
      })
    },
    handleClick(row) {
      this.editform = _.cloneDeep(row)
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
