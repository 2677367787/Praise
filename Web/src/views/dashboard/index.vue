<template>
  <div class="app-container">
    <div>{{new Date().getFullYear()}}年第{{MeetingNumber}}次双周例会</div>
    <div>本周主题:{{MeetingTheme}}</div>
    <div id="myChart" :style="{width:'500px',height:'500px'}"></div>
  </div>
</template>
<script>
export default {
  name: 'Chart',
  data() {
    return {
      MeetingTheme: '',
      MeetingNumber: ''
    }
  },
  mounted() {
    this.drawLine()
  },
  methods: {
    drawLine() {
      console.log(1)
      const myChart = this.$echarts.init(document.getElementById('myChart'), 'light')
      this.$ajax.get('praise/title').then(result => {
        const { message, code, data } = result
        if (code === 200) {
          var chartTitle = new Array(5)
          var chartData = new Array(5)
          for (let index = 0; index < data.length; index++) {
            chartTitle[index] = `第${data[index].issue}次例会`
            chartData[index] = data[index].praiseCount
            this.MeetingNumber = data[index].issue
          }
          myChart.setOption({
            title: { text: '例会点赞趋势图' },
            tooltip: {},
            xAxis: {
              data: chartTitle
            },
            yAxis: {},
            series: [
              {
                name: '点赞数量',
                type: 'line',
                data: chartData
              }
            ]
          })
        } else {
          this.$message.error(message)
        }
      })
    }
  }
}
</script>
