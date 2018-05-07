<template>
  <el-menu class="navbar" mode="horizontal">
    <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>
    <breadcrumb></breadcrumb>
    <el-dropdown trigger="hover" class="avatar-container">
        <span class="el-dropdown-link userinfo-inner">
          <div>
            <div class="box-user-avatat" v-if="this.avatar ===''"><svg-icon :icon-class="'defaultAvatar'" class="user-avatat"></svg-icon></div>
            <img :src="this.avatar" v-else/>
            {{this.nick_name}}({{this.name}}){{this.avatar}}
          </div>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item >
              <a href="javascript:;">我的消息</a>
              <el-badge class="mark" :value="3" />
          </el-dropdown-item>
          <el-dropdown-item>
            <router-link to="/Setting">设置</router-link>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
    </el-dropdown>
  </el-menu>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'nick_name',
      'name'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('ToggleSideBar')
    },
    logout() {
      this.$store.dispatch('LogOut').then(() => {
        location.reload() // 为了重新实例化vue-router对象 避免bug
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 50px;
  line-height: 50px;
  border-radius: 0px !important;
  .hamburger-container {
    line-height: 58px;
    height: 50px;
    float: left;
    padding: 0 10px;
  }
  .screenfull {
    position: absolute;
    right: 90px;
    top: 16px;
    color: red;
  }
  .avatar-container {
    height: 50px;
    display: inline-block;
    position: absolute;
    right: 35px;
    .avatar-wrapper {
      cursor: pointer;
      margin-top: 5px;
      position: relative;
      .user-avatar {
        width: 40px;
        height: 40px;
        border-radius: 10px;
      }
      .el-icon-caret-bottom {
        position: absolute;
        right: -20px;
        top: 25px;
        font-size: 12px;
      }
    }
  }
}

.userinfo-inner {
    cursor: pointer;
    img {
      width: 40px;
      height: 40px;
      border-radius: 20px;
      padding: 5px 10px;
      float: right;
    }
    .user-avatat{
      width: 40px;
      height: 40px;
      padding: 5px;
      float: right;
    }
    .box-user-avatat{
      background: #fff;;
      border: solid 1px #f6f6f6;
      width: 40px;
      height: 40px;
      float:right;
      margin-top: 5px;
      
    }
  }
</style>

