<template>
    <div class="app-container">
        <el-row>
            <el-card shadow="never">
                <div slot="header">
                    <span class="card-title">任务进度</span>
                </div>
                <el-steps :active="formData.status" align-center>
                    <el-step title="充能" description="超过2个人认可的任务进入待接受"></el-step>
                    <el-step title="待接受" description="等待领取的任务"></el-step>
                    <el-step title="开发中" description="开发中的任务"></el-step>
                    <el-step title="待验收" description="开发完成等待所有参与人验收"></el-step>
                    <el-step title="已完成" description="任务已经完成"></el-step>
                </el-steps>
            </el-card>
        </el-row>
        <el-row>
            <el-card shadow="never">
                <div slot="header">
                    <span class="card-title">任务详情</span>
                </div>
                <table class="gridtable">
                    <tr>
                        <td class="title">发布人</td>
                        <td class="name"><span>{{formData.createBy|parseUserName}}</span></td>
                        <td class="title">发布时间</td>
                        <td>{{formData.createDate|parseTime('{y}-{m}-{d} {h}:{i}:{s}')}}</td>
                    </tr>
                    <tr>
                        <td class="title">任务大纲</td>
                        <td colspan="3"><span>{{formData.demandTitle}}</span></td>
                    </tr>
                    <tr>
                        <td class="title">描述</td>
                        <td colspan="3"><span>{{formData.demandContent}}</span></td>
                    </tr>
                </table>
            </el-card>
        </el-row>
        <el-row v-if="tasksDetail&&tasksDetail.length">
            <el-card shadow="never">
                <div slot="header">
                    <span class="card-title">其他参与人</span>
                </div>
                <template v-for="item in tasksDetail">
                    <table class="gridtable detail-tab">
                        <tr>
                            <td class="title">参与人</td>
                            <td class="name"><span>{{item.createBy|parseUserName}}</span></td>
                            <td class="title">参与时间</td>
                            <td>{{item.createDate|parseTime('{y}-{m}-{d} {h}:{i}:{s}')}}</td>
                        </tr>
                        <tr>
                            <td class="title">补充内容</td>
                            <td colspan="3"><span>{{item.demandContent}}</span></td>
                        </tr>
                        <tr>
                            <td class="title">评分</td>
                            <td colspan="3">
                                <el-rate 
                                :disabled="item.evaluate === 0 && item.userName === item.createBy? false:true" 
                                v-model="item.evaluate"
                                :allow-half="true"
                                :score-template="''+item.evaluate"
                                @change="onChange(item)">
                                </el-rate>
                            </td>
                        </tr>
                        <tr>
                            <td class="title">评价</td>
                            <td colspan="3">
                                <el-input v-if="!item.isComment && item.userName === item.createBy" v-model="item.comment" placeholder="任务完成了,点个赞吧~" auto-complete="off" style="width:50%" ></el-input>
                                <span v-else>{{item.comment}}</span>
                                <el-button v-if="!item.isComment && item.userName === item.createBy" type="primary" size="mini" @click="onChange(item,'1')">评价</el-button>
                            </td>
                        </tr>
                    </table>
                </template>
            </el-card>
        </el-row>
        <el-row v-if="formData.acceptBy">
            <el-card shadow="never">
                <div slot="header">
                    <span class="card-title">领取人信息</span>
                </div>
                <table class="gridtable">
                    <tr>
                        <td class="title">领取人</td>
                        <td class="name"><span>{{formData.acceptBy|parseUserName}}</span></td>
                        <td class="title">领取时间</td>
                        <td><span v-if="formData.acceptDate">{{formData.acceptDate|parseTime('{y}-{m}-{d} {h}:{i}:{s}')}}</span></td>
                    </tr>
                </table>
            </el-card>
        </el-row>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
export default {
  data() {
    return {
      formData: [],
      tasksDetail: []
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
  created() {
    this.formData = this.$route.params
    console.log(this.$route.params)
    this.onLoad()
  },
  methods: {
    onLoad() {
      this.$ajax.get(this.$apiUrl.getAlltaskDetailsUrl + this.formData.tasksId).then(
        result => {
          result.data.map(map => {
            map.evaluate = parseFloat(map.evaluate)
            map.userName = this.name
          })
          console.log(result.data)
          this.tasksDetail = result.data
        }
      )
    },
    onChange(item, isComment) {
      if (isComment) {
        item.isComment = 1
      }
      this.$ajax.put(this.$apiUrl.taskDetailsUrl, item).then(
        result => {
          this.$message.success('评价成功!')
          // this.onLoad()
        }
      )
    }
  }
}
</script>
<style lang="scss" scoped>
.grid-content {
    min-height: 36px;
    line-height: 36px;
}
.el-row {
    margin-bottom: 20px;
    &:last-child {
        margin-bottom: 0;
    }
}

.title{
    background-color: hsl(225, 14%, 95%);
    width: 120px;
}
table.gridtable {
    font-family: verdana, arial, sans-serif;
    font-size: 11px;
    color: #333333;
    border-width: 1px;
    border-color: #dedede;
    border-collapse: collapse;
    width: 100%;
}

table.gridtable td {
    border-width: 1px;
    padding: 10px;
    border-style: solid;
    border-color: #dedede;
}
.bg-purple-light {
    background: #e5e9f2;
}
.card-title{
    font-weight: bold;
}
.detail-tab{
    margin-bottom: 20px;
    &:last-child {
        margin-bottom: 0;
    }
}
.name{
    width: 30%;
}
</style>

