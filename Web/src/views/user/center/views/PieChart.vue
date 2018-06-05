<template>
    <div :className="className" :style={height:height,width:width}></div>
</template>
<script>
import echarts from 'echarts'
import { debounce } from 'lodash'
export default {
  name: 'PieChart',
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null,
      contentData: [],
      titleData: [],
      tips: ''
    }
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    window.removeEventListener('resize', this.__resizeHanlder)
    this.chart.dispose()
    this.chart = null
  },
  mounted() {
    this.__resizeHanlder = debounce(() => {
      if (this.chart) {
        this.chart.resize()
      }
    }, 100)
    window.addEventListener('resize', this.__resizeHanlder)
  },
  methods: {
    initData(data) {
      this.contentData = data.contentData
      this.titleData = data.titleData
      this.tips = data.tips
    },
    initChart() {
      console.log(this.contentData)
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '10',
          data: this.titleData
        },
        calculable: true,
        series: [
          {
            name: this.tips,
            type: 'pie',
            roseType: 'radius',
            radius: [15, 95],
            center: ['50%', '38%'],
            data: this.contentData,
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      })
    }
  }
}
</script>
