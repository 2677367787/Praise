<template>
  <el-autocomplete
  popper-class="my-autocomplete"
  v-model="nickName"
  :debounce="500"
  size="small"
  value-key="nickName"
  :fetch-suggestions="querySearch"
  placeholder="请输入工号或姓名"
  @select="handleSelect"
  :trigger-on-focus="false">
  <i
    class="el-icon-close suffix"
    slot="suffix"
    @click="handleIconClick">
  </i>
  <template slot-scope="{ item }">
    <div class="name">{{ item.nickName }}</div>
    <span class="addr">{{ item.userName }}</span>
  </template>
</el-autocomplete>
</template>
<script>
import { ApiUrl } from '@/api/apiUrl'
export default {
  name: 'UserInput',
  data() {
    return {
      nickName: '',
      childData: ''
    }
  },
  props: {
    data: {
      type: Object
    },
    fieldName: {
      type: String,
      default: 'userName'
    }
  },
  watch: {
    history(newValue, oldValue) {
      if (newValue === '') {
        this.nickName = ''
      }
    }
  },
  computed: {
    history() {
      return this.data[this.fieldName]
    }
  },
  methods: {
    querySearch(queryString, cb) {
      if (!queryString) {
        this.data.userName = ''
        return
      }
      this.$ajax.get(ApiUrl.getUsersListByNameUrl + queryString).then(result => {
        cb(result.data)
      })
    },
    handleSelect(item) {
      this.data[this.fieldName] = item.userName
    },
    handleIconClick() {
      this.nickName = ''
      this.data.userName = ''
    }
  },
  mounted() {
    this.childData = this.data
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