<template>
  <div class="card-tag">
        <el-tag
        :key="tag.impressId"
        v-for="tag in tags"
        size="medium"
        :disable-transitions="false">
        {{tag.tag}}
        </el-tag>
        <div v-if="this.isOneself" class="panel">
            <el-input
            class="input-new-tag"
            v-if="inputVisible"
            v-model="inputValue"
            ref="saveTagInput"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
            >
            </el-input>
            <el-button v-else class="button-new-tag" size="small" @click="showInput">+印象标签</el-button>
        </div>
  </div>
</template>
<script>
export default {
  name: 'impressTag',
  data() {
    return {
      inputVisible: false,
      inputValue: '',
      tags: []
    }
  },
  props: {
    isOneself: {
      type: Boolean,
      default: true
    },
    userName: {
      type: String,
      default: ''
    }
  },
  mounted() {
    this.loadData()
  },
  methods: {
    loadData() {
      if (!this.userName) return
      this.$ajax.get(this.$apiUrl.getImpressTagUrl + this.userName).then(result => {
        this.tags = result.data
      })
      this.loading = false
    },
    showInput() {
      this.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },
    handleInputConfirm() {
      if (!this.inputValue) {
        this.inputVisible = false
        this.inputValue = ''
        return
      }
      const inputValue = this.inputValue
      this.$emit('onSave', inputValue)
      this.inputVisible = false
      this.inputValue = ''
    }
  }
}
</script>
<style lang="scss" scoped>
.el-tag + .el-tag {
    margin-left: 10px;
}

.card-tag{
  float: left;
}

.button-new-tag {
    margin-left: 10px;
    height: 28px;
    line-height: 28px;
    padding-top: 0;
    padding-bottom: 0;
}
.input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
    height: 28px;
    line-height: 28px;
}
.panel{
  float: right;
}
.input-new-tag input{
  height: 28px;
}
</style>
