<template>
  <el-row class="panel-group" :gutter="40">
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class='card-panel'>
          <div class="title-text">例会信息</div>
          <el-row>{{new Date().getFullYear()}}年第{{meeting.tally}}次双周例会</el-row>
          <el-row>周期：{{meeting.start| parseTime('{y}-{m}-{d}')}}&nbsp;至&nbsp;{{meeting.end| parseTime('{y}-{m}-{d}')}}</el-row>
          <el-row>本周主题：{{meeting.theme}}</el-row>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
         <div class="title-text">点赞之星</div>
         <el-row v-for="(item,index) in praiseList" :key="index">
           <el-tag size="mini">{{item.uniqueName}}</el-tag>
           <el-tag size="mini" type="info">获赞:{{item.gain}}</el-tag>
         </el-row>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
         <div class="title-text">进行中的投票</div>
         <el-row v-for="(item,index) in votes" :key="index">
           <el-button type="text" @click="toVoteDetail(item)">{{item.theme}}</el-button>
         </el-row>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
         <div class="title-text">待接受任务</div>
         <el-row v-for="(item,index) in tasks" :key="index">
           <span size="mini">{{item.demandTitle}}</span>
         </el-row>
      </div>
    </el-col>
  </el-row>
</template>

<script>

export default {
  data() {
    return {
      meeting: '',
      praiseList: '',
      votes: '',
      tasks: {}
    }
  },
  mounted() {
    this.initChart()
  },
  methods: {
    initChart() {
      this.$ajax.get(this.$apiUrl.getNewestMeeting).then(result => {
        this.meeting = result.data
      })

      this.$ajax.get(this.$apiUrl.getPariseTop3).then(result => {
        this.praiseList = result.data
      })

      this.$ajax.get(this.$apiUrl.getVoteTop3).then(result => {
        this.votes = result.data
      })

      this.$ajax.get(this.$apiUrl.getTasksTop3).then(result => {
        this.tasks = result.data
      })
    },
    toVoteDetail(row) {
      this.$ajax.get(this.$apiUrl.voteOptionChoosedUrl + row.voteId).then(
        result => {
          this.$router.push({ name: 'voteDetail', params: { row, 'choose': result.data }})
        }
      )
    },
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.el-row{
  margin: 5px 0px;
  &:last-child {
      margin-bottom: 0;
  }
}
.panel-group {
  .card-panel-col{
    margin-bottom: 10px;
  }
  .card-panel {
    min-height: 120px;
    padding: 10px;
    font-size: 14px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);
    .title-text{
      font-weight: bold;
      font-size: 16px;
    }
  }
}
</style>
