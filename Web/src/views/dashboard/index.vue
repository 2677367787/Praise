<template>
  <div class="app-container dashboard-editor-container">
    <panel-group @handleSetLineChartData="handleSetLineChartData"></panel-group>
    <el-row style="background:#fff;margin-bottom:32px;"> 
      <line-chart></line-chart>
    </el-row>
  </div>
</template>
<script>
import PanelGroup from './components/PanelGroup'
import LineChart from './components/LineChart'
import { Storage } from '@/api/storage.js'
export default {
  name: 'Chart',
  components: { LineChart, PanelGroup },
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
      })

      this.$ajax.get(this.$apiUrl.usersUrl).then(result => {
        console.log(result.data)
        const userList = {}
        result.data.map(m => {
          userList[m.userName] = m
        })
        new Storage().set('userList', userList)
      })
    },

    handleSetLineChartData() {

    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .title{
    font-weight: bold;
  }
  .dashboard-editor-container {
    background-color: rgb(240, 242, 245);
    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }
</style>

