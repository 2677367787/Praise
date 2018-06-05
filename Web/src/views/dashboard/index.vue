<template>
  <div class="app-container">
    <el-row>{{new Date().getFullYear()}}年第{{meeting.tally}}次双周例会</el-row>
    <el-row>周期：{{meeting.start| parseTime('{y}-{m}-{d}')}}&nbsp;至&nbsp;{{meeting.end| parseTime('{y}-{m}-{d}')}}</el-row>
    <el-row>本周主题：{{meeting.theme}}</el-row>
    <div class="title">例会点赞趋势图</div>
    <el-row style="background:#fff;margin-bottom:32px;"> 
      <line-chart></line-chart>
    </el-row>
  </div>
</template>
<script>
import LineChart from './components/LineChart'
import { Storage } from '@/api/storage.js'
export default {
  name: 'Chart',
  components: { LineChart },
  data() {
    return {
      meeting: ''
    }
  },
  mounted() {
    this.initChart()
  },
  methods: {
    initChart() {
      this.$ajax.get(this.$apiUrl.getNewestMeeting).then(result => {
        this.meeting = result.data
        console.log(result.data)
      })

      this.$ajax.get(this.$apiUrl.usersUrl).then(result => {
        const userList = {}
        result.data.map(m => {
          userList[m.userName] = m
        })
        new Storage().set('userList', userList)
      })
    }
  }
}
</script>
<style scoped>
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .title{
    font-weight: bold;
  }
</style>
