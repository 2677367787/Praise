<template>
  <el-autocomplete
  ref="suggestions"
  popper-class="my-autocomplete"
  v-model="model"
  :debounce="200"
  size="small"
  :fetch-suggestions="querySearch"
  :placeholder="placeholder"
  @select="handleSelect"
  :trigger-on-focus="true">
  <i
    class="el-icon-close suffix"
    slot="suffix"
    @click="handleIconClick">
  </i>
  <template slot-scope="{ item }">
    <div class="name">{{ item.nickName }}(<span class="addr">{{ item.userName }}</span>)</div>
  </template>
</el-autocomplete>
</template>
<script>
export default {
  name: 'UserInput',
  data() {
    return {
      model: ''
    }
  },
  props: {
    formData: {
      type: Object
    },
    text: {
      type: String,
      default: 'nickName'
    },
    value: {
      type: String,
      default: 'userName'
    },
    placeholder: {
      type: String,
      default: '请输入工号或姓名'
    }
  },
  watch: {
    fieldText(newValue, oldValue) {
      if (newValue === '') {
        this.model = ''
      }
    }
  },
  computed: {
    fieldText() {
      return this.formData[this.value]
    }
  },
  methods: {
    querySearch(queryString, cb) {
      const param = { userName: queryString }
      this.$ajax.get(this.$apiUrl.usersUrl, param).then(result => {
        cb(result.data)
        if (result.data.length === 1) {
          this.$refs.suggestions.select(result.data[0])
          this.$refs.suggestions.$children[0].blur()
        }
      })
    },
    handleSelect(item) {
      this.formData[this.value] = item.userName
      this.model = item.nickName + ' ' + item.userName
      this.formData[this.text] = item.nickName + ' ' + item.userName
      this.$emit('handleSelect', item)
    },
    handleIconClick() {
      this.model = ''
      this.formData[this.value] = ''
      this.formData[this.text] = ''
    }
  },
  mounted() {
    if (this.formData[this.text]) {
      this.model = this.formData[this.text]
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.my-autocomplete {
  li {
    line-height: normal;
    padding: 7px;

    .name {
      text-overflow: ellipsis;
      overflow: hidden;
    }
    .addr {
      font-size: 12px;
      color: #b4b4b4;
    }

    .highlighted .addr {
      color: #ddd;
    }
  }
}
.suffix{
  cursor: pointer;
}
</style>