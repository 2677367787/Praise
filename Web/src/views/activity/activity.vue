<template>
  <div class="app-container"> 
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item>
      活动主题：5·19“以爱之名，全城热恋”<br/>
      主办单位：永州人民广播电台<br/>
      承办单位：永州丁泉水电子商务有限公司、水泊梁山休闲山庄<br/>
      赞助单位：多喜来西饼屋、广宏酒业（陆续增加中...）<br/>
      合作媒体：永州市广播电视台、在永州APP、永州网等多家媒体支持宣传<br/>
      活动时间：2018年5月19号（晚上18:30-22:00）<br/>
      活动地点：水泊梁山休闲山庄<br/>
      报名热线：0746-8666123<br/>
      报名微信：llq961104wanan<br/>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="年龄">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.resource">
          <el-radio label="男" value="1"></el-radio>
          <el-radio label="女" value="0"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="身高">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="手机号码">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="婚姻状态">
        <el-radio-group v-model="form.resource">
          <el-radio label="未婚" value="1"></el-radio>
          <el-radio label="离异" value="0"></el-radio>
          <el-radio label="丧偶" value="0"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="单位">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="微信号">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList">
          <el-button size="small" type="primary">上传照片</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">我要报名</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

