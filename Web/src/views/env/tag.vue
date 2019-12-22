<template>
  <el-button type="info" @click="showInput()" v-if="!isEdit"> + </el-button>
  <el-input
    v-model="inputValue"
    placeholder="请输入名称"
    @keyup.enter.native="handleInputConfirm"
    @blur="handleInputConfirm"
    ref="saveTagInput"
    v-else
  ></el-input>
</template>
<script>
export default {
  name: 'tag',
  data() {
    return {
      isEdit: false,
      inputValue: ''
    };
  },
  props: {
    type: {
      type: String,
      default: ''
    },
    id: {
      type: Number,
      default: ''
    }
  },
  methods: {
    showInput() {
      this.isEdit = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    handleInputConfirm() {
      console.log(this.inputValue);
      if (!this.inputValue) {
        return;
      }
      const param = { id: this.id, type: this.type, name: this.inputValue };
      console.log(param);
      this.$emit('onEnvSave', param);
      this.isEdit = false;
      this.inputValue = '';
    }
  }
};
</script>
