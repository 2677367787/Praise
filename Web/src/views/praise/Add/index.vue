<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="姓名">
        <user-input :formData="form" @handleSelect="handleSelect"></user-input>
      </el-form-item>
      <el-form-item>
        <el-row style="background:#fff;width:500px"> 
          <hot-words-chart ref="hotWords"></hot-words-chart>
        </el-row>
      </el-form-item>
      <el-form-item label="内容">
        <el-input
        type="textarea"
        :rows="4"
        class="input-conten"
        placeholder="请输入内容 按下ctrl+enter 或 alt+enter 快速点赞"
        v-model="form.content"
        @keyup.ctrl.enter.native="onSubmit('form')"
        @keyup.alt.enter.native="onSubmit('form')"
        >
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')" :loading="logining">点赞</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { ApiUrl } from '@/api/apiUrl'
import HotWordsChart from './components/HotWordsChart'

function createRandomItemStyle() {
  return {
    normal: {
      color: 'rgb(' + [
        Math.round(Math.random() * 160),
        Math.round(Math.random() * 160),
        Math.round(Math.random() * 160)
      ].join(',') + ')'
    }
  }
}

export default {
  components: { HotWordsChart },
  data() {
    return {
      logining: false,
      form: {
        userName: '',
        content: ''
      }
    }
  },
  methods: {
    onSubmit(formName) {
      this.form.praiseTo = this.form.userName
      if (!this.form.praiseTo) {
        this.$message.error('请选择要点赞的人员!')
        return false
      }

      if (!this.form.content) {
        this.$message.error('请输入点赞内容!')
        return false
      }
      this.logining = true
      this.$ajax.post(ApiUrl.praiseAddUrl, this.form).then(result => {
        this.logining = false
        this.$message.success('点赞成功!')
        this.form.content = ''
      }, result => {
        this.logining = false
      })
    },
    handleSelect(item) {
      const myself = this
      this.$ajax.get(this.$apiUrl.hotWordsUrl + '/' + item.userName).then(result => {
        const chartData = result.data.map(map => {
          const rgbColor = createRandomItemStyle()
          const { words: name, counter: value } = map
          return { name, value, rgbColor }
        })
        console.log(chartData, myself)
        myself.$refs['hotWords'].setOptions(chartData)
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
.input-conten{
  max-width: 80%;
  min-width: 300px;
}
</style>

