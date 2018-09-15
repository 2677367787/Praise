<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item label="姓名">
        <user-input :formData="form" @handleSelect="handleSelect" ref="txtUser"></user-input>
      </el-form-item>
      <el-form-item label="猜你想赞:">
          <el-row>
            <div v-for="(item,index) in wantPraise" :key="''+index">
              <div class="probably-btn">
                <el-tooltip v-if="item[0]==='6396000749'" class="item" placement="bottom-start" effect="dark">
                  <div slot="content">来自开发者特权推荐<br/>没错,掌握源代码就是可以为所欲为</div>
                  <el-button type="primary" size="mini" plain @click="handleRecommendTag(item[0])">{{item[0]|parseUserName}}</el-button>
                </el-tooltip>
                <el-button v-else type="primary" size="mini" plain @click="handleRecommendTag(item[0])">{{item[0]|parseUserName}}</el-button>
              </div>
            </div>
          </el-row>
      </el-form-item>
      <el-form-item>
        <el-row style="background:#fff;width:500px"> 
          <hot-words-chart ref="hotWords"></hot-words-chart>
        </el-row>
      </el-form-item>
      <el-form-item label="撤回或编辑:" v-if="recentPraise.length > 0">
          <el-row v-for="(item,index) in recentPraise" :key="''+index">
            <div class="probably-btn">
              <el-tag class="tag" closable @click.native="handleRecentTag(item)" @close="handleClose(item)">
                To {{item.praiseTo|parseUserName}} : {{item.content|subContent}}
              </el-tag>
            </div>
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
        <el-button type="primary" @click="onSubmit('form')" :loading="logining">
          <span v-if="this.isEdit">修改</span>
          <span v-else>点赞</span>
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { ApiUrl } from '@/api/apiUrl'
import HotWordsChart from './components/HotWordsChart'
import { parseUserName } from '@/filters/index.js'
import * as _ from 'lodash'
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
        content: '',
        nickName: ''
      },
      isEdit: false,
      wantPraise: [],
      recentPraise: [],
      lastPraiseTo: ''
    }
  },
  mounted() {
    this.onLoad()
    this.refreshRecent()
  },
  methods: {
    onLoad() {
      this.$ajax.get(this.$apiUrl.probablyUrl, this.form).then(result => {
        var m = new Map()
        result.data.forEach(r => {
          if (m.has(r.username)) {
            const weight = m.get(r.username)
            if (weight > r.weight) {
              m.set(r.username, r.weight)
            }
          } else {
            m.set(r.username, r.weight)
          }
        })
        this.wantPraise = [...m]
      })
    },
    refreshRecent() {
      this.$ajax.get(this.$apiUrl.rectentTop3).then(result => {
        console.log(result.data)
        this.recentPraise = result.data
      })
    },
    onSubmit(formName) {
      this.form.praiseTo = this.form.userName
      this.form.uniqueName = this.form.nickName
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
        if (this.isEdit) {
          this.form = {}
          this.isEdit = false
          this.$refs['txtUser'].model = ''
        }
        this.refreshRecent()
      }, result => {
        this.logining = false
      })
    },
    handleSelect(item) {
      if (this.lastPraiseTo === item.userName) {
        console.log(this.lastPraiseTo, item.userName)
        return
      }
      const myself = this
      this.$ajax.get(this.$apiUrl.hotWordsUrl + '/' + item.userName).then(result => {
        const chartData = result.data.map(map => {
          const rgbColor = createRandomItemStyle()
          const { words: name, counter: value } = map
          return { name, value, rgbColor }
        })
        myself.$refs['hotWords'].setOptions(chartData)
        myself.lastPraiseTo = item.userName
      })
    },
    handleRecommendTag(item) {
      this.isEdit = false
      this.form = {}
      this.form.userName = item
      this.setData(item)
    },
    setData(item) {
      this.$refs['txtUser'].model = parseUserName(item)
      this.handleSelect(this.form)
    },
    handleRecentTag(item) {
      this.isEdit = true
      this.form = {}
      this.form = _.cloneDeep(item)
      this.form.userName = item.praiseTo
      this.setData(item.praiseTo)
    },
    handleClose(item) {
      if (this.form.praiseId && this.form.praiseId === item.praiseId) {
        this.form = {}
        this.$refs['txtUser'].model = ''
      }
      this.$ajax.put(ApiUrl.praiseAddUrl, item).then(result => {
        this.$message.success('撤回成功!')
        this.refreshRecent()
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
.probably-btn{
  float: left;
  padding-right: 10px;
}
.item {
  margin: 4px;
}
.tag {
  cursor: pointer;
}
</style>

