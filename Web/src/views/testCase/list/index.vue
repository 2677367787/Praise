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
                <user-input :formData="form"></user-input>
                <el-button type="primary" @click="onQuery">Query</el-button>
                <el-button type="primary" @click="onClear">Clear</el-button>
                <el-button type="primary" @click="dialogFormVisible=true">发布新bug</el-button>
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
            <el-table-column prop="status" label="发布人" width="100">
              <template slot-scope="scope"> 
                <span>{{scope.row.createBy|parseUserName}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="status" label="领取人" width="100">
              <template slot-scope="scope"> 
                <span>{{scope.row.acceptBy|parseUserName}}</span>
              </template>
            </el-table-column>
            <el-table-column
            prop="demandTitle"
            label="大纲">
            </el-table-column>
            <el-table-column prop="status" label="当前状态" width="90">
              <template slot-scope="scope">
                <el-tag type="success">{{scope.row.status|parseStatus}}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="unionCreateBy" label="参与人" width="150">
              <template slot-scope="scope">
                <span>{{scope.row.unionCreateBy}}</span>
              </template>
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="300">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status < 3" @click="onAddEnergy(scope.row)" type="success" size="mini">点赞</el-button>
                <el-button v-if="scope.row.status === 1" @click="onReceive(scope.row,'2')" type="primary" size="mini">领取</el-button>
                <el-button v-if="scope.row.status === 2" @click="onReceive(scope.row,'3')" type="primary" size="mini">完成</el-button>
                <el-button v-if="scope.row.status === 3 && scope.row.createBy === scope.row.username" @click="onReceive(scope.row,'4')" type="primary" size="mini">验收</el-button>
                <el-button @click="toDetail(scope.row)"  type="info" size="mini">查看</el-button>
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
        <el-dialog title="发布任务" class="edit-region" :visible.sync="dialogFormVisible">
            <el-form :model="editform" label-width="80px">
                <el-form-item label="菜单路径"  prop="demandTitle"> 
                    <el-input v-model="editform.menuPath" placeholder="请输入任务大纲" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="任务大纲"  prop="demandTitle"> 
                    <el-input v-model="editform.demandTitle" placeholder="请输入任务大纲" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="详细描述">
                      <el-input
                      type="textarea"
                      :rows="4"
                      class="input-conten"
                      placeholder="请输入详细描述"
                      v-model="editform.demandContent">
                      </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveTasks">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      dialogEnergyVisible: false,
      dialogFormVisible: false,
      form: {
        praiseDate: [],
        userName: ''
      },
      editform: {
        demandTitle: '',
        demandContent: ''
      },
      detailform: {
        demand_content: '没有什么要补充的，我只想单纯的帮忙点个赞~',
        tasksId: ''
      },
      loading: false,
      tableData: [],
      currentPage: 1,
      total: 0,
      pageNum: 1,
      pageSize: 10
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
      this.$ajax.get(this.$apiUrl.getListTasksUrl, param).then(
        result => {
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
    saveTasks() {
      this.$ajax.post(this.$apiUrl.addTasksUrl, this.editform).then(
        result => {
          this.$message.success('保存成功!')
          this.dialogFormVisible = false
          this.onQuery()
        }
      )
    },
    handleClick(row) {

    },
    toDetail(row) {
      this.$router.push({ name: 'tasksDetail', params: row })
    },
    onAddEnergy(row) {
      console.log(row)
      this.dialogEnergyVisible = true
      this.detailform.tasksId = row.tasksId
    },
    saveTasksDetail() {
      this.$ajax.post(this.$apiUrl.addTaskDetailsUrl, this.detailform).then(
        result => {
          this.$message.success('操作成功!')
          this.dialogEnergyVisible = false
          this.onQuery()
        }
      )
    },
    onReceive(row, status) {
      row.status = status
      this.$ajax.post(this.$apiUrl.receiveUrl, row).then(
        result => {
          this.$message.success('操作成功!')
          // this.onQuery()
        }
      )
    },
    onDelete(row) {
      this.$confirm('此操作将永久删除该任务, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.delete(this.$apiUrl.addTasksUrl + '/' + row.tasksId).then(
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
</style>
