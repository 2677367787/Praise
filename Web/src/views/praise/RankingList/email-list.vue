<template>
  <div>
    <div class="board-top-3">
    <el-carousel :interval="4000" type="card" arrow="nerver" indicator-position="none" height="200px">
      <el-carousel-item v-for="item in 3" :key="item">
        <h3>{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
    </div>
    <div class="components-container board">
      <Kanban :key="1" class="kanban todo" :list="list1" :options="options" header-text="获赞榜"/>
      <Kanban :key="2" class="kanban done" :list="list3" :options="options" header-text="点赞榜"/>
    </div>
  </div>
</template>
<script>
import Kanban from '@/components/Kanban'

export default {
  name: 'email-list',
  components: {
    Kanban
  },
  data() {
    return {
      options: {
        group: 'mission'
      },
      list1: [
        { name: 'Mission', id: 1 },
        { name: 'Mission', id: 2 },
        { name: 'Mission', id: 3 },
        { name: 'Mission', id: 4 }
      ],
      list2: [
        { name: 'Mission', id: 5 },
        { name: 'Mission', id: 6 },
        { name: 'Mission', id: 7 }
      ],
      list3: [
        { name: 'Mission', id: 8 },
        { name: 'Mission', id: 9 },
        { name: 'Mission', id: 10 }
      ]
    }
  }
}
</script>
<style lang="scss">
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

.board-top-3 {
  width: 700px;
  margin: 50px auto;
}

.board {
  display: flex;
  justify-content: space-around;
  flex-direction: row;
  align-items: flex-start;
  margin: 50px auto;
  width: 700px;
}
.kanban {
  &.todo {
    .board-column-header {
      background: #4A9FF9;
    }
  }
  &.working {
    .board-column-header {
      background: #f9944a;
    }
  }
  &.done {
    .board-column-header {
      background: #2ac06d;
    }
  }
}
</style>

