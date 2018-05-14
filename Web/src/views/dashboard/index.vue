<template>
  <div class="app-container">
    <div>{{new Date().getFullYear()}}年第{{meeting.tally}}次双周例会</div>
    <p></p>
    <div>本周主题:{{meeting.theme}}</div>
    <p></p>
    <div id="myChart" :style="{width:'700px',height:'300px'}"></div>
  </div>
</template>
<script>
export default {
  name: 'Chart',
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
      const myChart = this.$echarts.init(document.getElementById('myChart'), 'light')
      this.$ajax.get(this.$apiUrl.getCharts).then(result => {
        var chartData = result.data.reverse().map(m => { return m.praiseCount })
        var chartTitle = result.data.map(m => { return m.issue })
        myChart.setOption({
          title: { text: '例会点赞趋势图' },
          xAxis: {
            data: chartTitle,
            boundaryGap: false,
            axisTick: {
              show: false
            }
          },
          grid: {
            left: 10,
            right: 10,
            bottom: 20,
            top: 30,
            containLabel: true
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross'
            },
            padding: [5, 10]
          },
          yAxis: {
            axisTick: {
              show: false
            }
          },
          series: [
            {
              name: '点赞数量',
              smooth: true,
              type: 'line',
              itemStyle: {
                normal: {
                  color: '#3888fa',
                  lineStyle: {
                    color: '#3888fa',
                    width: 2
                  },
                  areaStyle: {
                    color: '#f3f8ff'
                  }
                }
              },
              data: chartData,
              animationDuration: 2800,
              animationEasing: 'quadraticOut'
            }
          ]
        })
      })

      this.$ajax.get(this.$apiUrl.getNewestMeeting).then(result => {
        this.meeting = result.data
        console.log(result.data)
      })
    }
  }
}
</script>
