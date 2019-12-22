<template>
  <div class="board-column">
    <div class="board-column-header">
      {{headerText}}
    </div>
    <div
      class="board-column-content"
      :list="list"
      :options="options">
        <el-row type="flex" justify="space-between" class="board-item" v-for="element in list" :key="element.id">
          <el-col :span="6"><canvas ref="myCanvas" width="40px" height="40px"></canvas></el-col>
          <el-col :span="8"><div></div>{{element.name}}</el-col>
          <el-col :span="6"><div></div> {{element.id}}</el-col>
        </el-row>
    </div>
  </div>
</template>
<script>
export default {
  name: 'dragKanban-demo',
  props: {
    headerText: {
      type: String,
      default: 'Header'
    },
    options: {
      type: Object,
      default() {
        return {}
      }
    },
    list: {
      type: Array,
      default() {
        return []
      }
    }
  },
  methods: {
    drawAvator() {
      var ctx = this.$refs['myCanvas']
      var canvas = ctx[0].getContext('2d')
      canvas.fillStyle = '#4A9FF9'
      canvas.fillRect(0, 0, 30, 30)
      canvas.font = '30px 宋体'
      canvas.fillStyle = '#fff'
      canvas.width = 80
      canvas.height = 80
      canvas.fillText('唐', 5, 30)
    }
  },
  mounted() {
    this.drawAvator()
  }
}
</script>
<style lang="scss">
.board-column {
  min-width: 300px;
  min-height: 100px;
  height: auto;
  overflow: hidden;
  background: #f0f0f0;
  border-radius: 3px;

  .board-column-header {
    height: 50px;
    line-height: 50px;
    overflow: hidden;
    padding: 0 20px;
    text-align: center;
    background: #333;
    color: #fff;
    border-radius: 3px 3px 0 0;
  }

  .board-column-content {
    height: auto;
    overflow: hidden;
    border: 10px solid transparent;
    min-height: 60px;
    display: flex;
    justify-content: flex-start;
    flex-direction: column;
    align-items: center;

    .board-item {
      cursor: pointer;
      width: 100%;
      height: 40px;
      margin: 4px 0;
      background-color: #fff;
      text-align: left;
      line-height: 30px;
      padding: 2px 5px;
      box-sizing: border-box;
      box-shadow: 0px 1px 3px 0 rgba(0,0,0,0.2);
    }
  }
}
</style>

