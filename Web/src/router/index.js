import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  { path: '/activity', component: () => import('@/views/activity/activity.vue'), hidden: true },
  {
    path: '/',
    component: Layout,
    redirect: '/home',
    name: '主页',
    hidden: true,
    children: [{
      path: 'home',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/praise',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'praiseList',
        component: () => import('@/views/praise/RankingList/index'),
        meta: { title: '点赞排行', icon: 'rankings' }
      }
    ]
  },

  {
    path: '/praise',
    component: Layout,
    children: [
      {
        path: 'add',
        name: 'Add',
        component: () => import('@/views/praise/Add/index'),
        meta: { title: '我要点赞', icon: 'praise' }
      }
    ]
  },

  {
    path: '/tree',
    component: Layout,
    children: [
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree', icon: 'tree' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ]
  },
  {
    path: '/user/center',
    component: Layout,
    redirect: '/user/center/home',
    name: '个人中心',
    meta: { title: '个人中心', icon: 'example' },
    children: [
      {
        path: 'home',
        name: 'home',
        component: () => import('@/views/user/center/home'),
        meta: { title: '主页', icon: 'home' }
      },
      {
        path: 'password',
        name: 'password',
        component: () => import('@/views/user/setting/password'),
        meta: { title: '修改密码', icon: 'password' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  mode: 'history', // 后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

