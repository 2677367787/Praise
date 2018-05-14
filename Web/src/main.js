import Vue from 'vue'

import 'normalize.css/normalize.css'// A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en'

import '@/styles/index.scss' // global css

import App from './App'
import router from './router'
import store from './store'

import '@/icons' // icon
import '@/permission' // permission control

import echarts from 'echarts'
import ajax from '@/api/ajax' // ajax请求封装
import moment from 'moment' // 日期格式化组件
import UserInput from '@/components/UserInput/index'
import { ApiUrl } from '@/api/apiUrl'

Vue.use(ElementUI, { locale, size: 'small' })
Vue.prototype.$echarts = echarts
Vue.prototype.$ajax = ajax
// Object.defineProperty允许我们用属性描述器来定义我们的属性。我们可以定义该属性是否可写，可枚举，可配置。
Vue.prototype.$moment = moment
Vue.prototype.$apiUrl = ApiUrl
Vue.config.productionTip = false
Vue.component('user-input', UserInput)
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
