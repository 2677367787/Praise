<template>
    <div class="app-container">
      <el-row>
          <el-card shadow="never">
              <div slot="header">
                  <span class="card-title">投票详情</span>
              </div>
              <table class="gridtable">
                  <tr>
                      <td class="title">发布人</td>
                      <td class="name"><span>{{vote.createBy|parseUserName}}</span></td>
                      <td class="title">发布时间</td>
                      <td>{{vote.createDate|parseTime('{y}-{m}-{d} {h}:{i}:{s}')}}</td>
                  </tr>
                  <tr>
                      <td class="title">主题</td>
                      <td colspan="3"><span>{{vote.theme}}</span></td>
                  </tr>
                  <tr>
                      <td class="title">详细描述</td>
                      <td colspan="3"><span>{{vote.additional}}</span></td>
                  </tr>
                  <tr>
                    <td class="title">总参与人数：</td>
                    <td colspan="3">
                      {{vote.participants}}
                    </td>
                  </tr>
              </table>
          </el-card>
      </el-row>
      <el-row>
        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item>
            <template slot="title">
              <span class="card-title">投票结果</span>
            </template>
            <div>假装有个统计图~~</div>
            <div></div>
          </el-collapse-item>
        </el-collapse>
      </el-row>
      <el-row>
          <el-card shadow="never">
              <div slot="header">
                  <span class="card-title">选项</span>
              </div>
              <table class="tab-option" v-if="!choosed">
                  <tr v-if="vote.type!==2">
                    <td>
                      <el-tag>最少选择：{{vote.leastSelect}}</el-tag>
                      <el-tag>最多选择：{{vote.mostSelect}}</el-tag>
                      <el-tag type="warning">还可以选择：{{remainNmber}}</el-tag>
                    </td>
                  </tr>
                  <tr v-if="vote.type===1">
                    <td>
                      <el-tag>总分：{{vote.votesNumber}}</el-tag>
                      <el-tag>剩余可分配：{{remainShare}}</el-tag>
                    </td>
                  </tr>
                  <template v-if="vote.type!==3">
                    <tr v-for="(item,index) in voteOption" :key="''+index">
                        <td v-if="vote.type===1">
                          <el-tag type="info" size="small">{{item.optionText}}</el-tag>
                          <el-input-number controls-position="right" :min='0' v-model="item.share" @change="handleChange" size="mini" :label="item.optionText"></el-input-number>
                        </td>
                        <td v-if="vote.type===2">
                          <el-radio v-model="singleSelection" :label="item.optionId">{{item.optionText}}</el-radio>
                        </td>
                    </tr>
                  </template>
                  <el-checkbox-group v-else v-model="multiSelection" :max="vote.mostSelect" @change="handleCheckedChange">
                    <tr v-for="(item,index) in voteOption" :key="item.optionId">
                      <td>
                        <el-checkbox :disabled="choosed" :label="item.optionId">{{item.optionText}}</el-checkbox>
                      </td>
                    </tr>
                  </el-checkbox-group>
                  <tr>
                    <td><el-button type="primary" size="mini" v-if="!choosed" @click="saveVote">投  票</el-button></td>
                  </tr>
              </table>
              <table class="tab-option" v-else>
                <tr v-for="(item,index) in voteOption" :key="''+index">
                  <td style="width:500px;max-width:100%">
                      <el-tag type="info" size="small">{{item.optionText}}</el-tag><el-tag>总票数：{{item.share}}</el-tag>
                      <el-progress :stroke-width="5" :percentage="Math.round(((item.share/(vote.participants*vote.votesNumber))*100)*10)/10"></el-progress>
                  </td>
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
      vote: [],
      voteOption: [],
      singleSelection: '',
      multiSelection: [],
      remainNmber: 0,
      remainShare: 0,
      title: '',
      choosed: false,
      activeNames: '',
      choose: []
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
    this.choose = this.$route.params.choose
    if (this.choose.length > 0) {
      this.choosed = true
      this.singleSelection = this.choose[0].optionId
      this.multiSelection = this.choose.map(map => map.optionId)
    }
    console.log(this.choosed)
    this.formData = this.$route.params.row
    this.onLoad()
  },
  methods: {
    onLoad() {
      this.$ajax.get(this.$apiUrl.voteUrl + '/' + this.formData.voteId).then(
        result => {
          console.log(result.data)
          this.vote = result.data.vote
          this.remainNmber = this.vote.mostSelect
          result.data.voteOption.map(map => {
            let sumShare = 0
            map.voteOptionDetail.forEach(s => {
              sumShare += s.share || 0
            })
            map.share = sumShare
          })
          this.voteOption = result.data.voteOption
          this.remainShare = this.vote.votesNumber

          if (this.vote.type === 1) {
            this.title = '总分:' + this.vote.votesNumber
          } else if (this.vote.type === 3) {
            this.title = '剩余可选:'
          }
          console.log(this.voteOption)
        }
      )
    },
    handleChange(value) {
      const { optionNum, sumShare } = this.getShareAndNum()
      this.remainShare = this.vote.votesNumber - sumShare
      this.remainNmber = this.vote.mostSelect - optionNum
      console.log(optionNum, sumShare)
    },
    handleCheckedChange(value) {
      this.remainNmber = this.vote.mostSelect - value.length
    },
    getShareAndNum() {
      let optionNum = 0
      let sumShare = 0
      this.voteOption.forEach((map, index) => {
        if (map.share > 0) {
          optionNum++
        }
        sumShare += parseInt(map.share)
      })
      return { optionNum, sumShare }
    },
    saveVote() {
      let optionIds = []
      let postData
      if (this.vote.type === 1) {
        const { optionNum, sumShare } = this.getShareAndNum()
        let msg = ''
        if (optionNum < this.vote.leastSelect) {
          msg = `最少要选择${this.vote.leastSelect}选项！`
        } else if (optionNum > this.vote.mostSelect) {
          msg = `最多只能选择${this.vote.mostSelect}选项！`
        } else if (sumShare !== this.vote.votesNumber) {
          msg = `总分只能刚刚好分配完！`
        }

        if (msg) {
          this.$message.info(msg)
          return
        }
        postData = this.voteOption.filter(f => f.share > 0)
      } else if (this.vote.type === 2) {
        if (!this.singleSelection) {
          this.$message.info(`至少选择一个选项!`)
          return
        }
        postData = [{ 'optionId': this.singleSelection, 'share': 1 }]
      } else {
        optionIds = this.multiSelection
        if (optionIds.length < this.vote.leastSelect) {
          this.$message.info(`本次投票至少需要选择${this.vote.leastSelect}个选项!`)
          return
        }
        postData = optionIds.map(map => {
          const share = 1
          const optionId = map
          return { optionId, share }
        })
      }
      postData = { 'voteOptionDetailList': postData, 'voteId': this.vote.voteId }
      this.$ajax.post(this.$apiUrl.voteOptionDetailUrl, postData).then(
        result => {
          this.$message.success('保存成功!')
          this.choosed = true
          this.onLoad()
          this.$ajax.get(this.$apiUrl.voteOptionChoosedUrl + this.formData.voteId).then(
            result => {
              this.choose = result.data
              if (this.choose.length > 0) {
                this.choosed = true
                this.singleSelection = this.choose[0].optionId
                this.multiSelection = this.choose.map(map => map.optionId)
              }
            }
          )
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
.el-tag{
  margin-bottom: 5px;
  margin-right: 20px;
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
.tab-option td{
  padding: 10px;
}

.bg-purple-light {
    background: #e5e9f2;
}
.card-title{
    font-weight: bold;
    margin-right: 200px;
    font-size: 16px;
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
.el-collapse-item{
  border-left: 1px solid #dedede;
  border-right: 1px solid #dedede;
  font-family: verdana, arial, sans-serif;
  font-size: 11px;
  padding: 10px;
}
</style>

