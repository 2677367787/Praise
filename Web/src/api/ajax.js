import axios from 'axios'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import Vue from 'vue'
import store from '../store'
import { getToken } from '@/utils/auth'

const axiosIns = axios.create({})

axiosIns.defaults.baseURL = location.origin + '/web'
// axiosIns.defaults.baseURL = 'http://10.5.4.24:9001/web'
// axiosIns.defaults.baseURL = 'http://47.104.242.130/web/'

// 添加请求拦截器
axiosIns.interceptors.request.use(function(config) {
  // 在发送请求之前做些什么
  NProgress.start()
  if (store.getters.token) {
    config.headers['t-token'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  return config
}, function(error) {
  // 对请求错误做些什么
  console.log('错误的传参')
  return Promise.reject(error)
})

axiosIns.interceptors.response.use((res) => {
  // 对响应数据做些事
  NProgress.done()
  if (!res.data) {
    return Promise.reject(res)
  }
  return res
}, (error) => {
  NProgress.done()
  return Promise.reject(error)
})
const ajaxMethod = ['get', 'post', 'put', 'delete']
const ajax = {}
ajaxMethod.forEach((method) => {
  ajax[method] = function(uri, data, config) {
    if (method === 'get') {
      data = { params: data }
    }
    return new Promise(function(resolve, reject) {
      axiosIns[method](uri, data, config)
        .then(response => {
          if (response.data.code === 200) {
            resolve(response.data)
          } else if (response.data.code === 401) {
            store.dispatch('FedLogOut').then(() => {
              location.reload()// 为了重新实例化vue-router对象 避免bug
            })
          } else {
            Vue.prototype.$message.error(response.data.message, 1000)
            reject(response.data)
          }
        })
        .catch(function(error) {
          if (error.response) {
            // 请求已发出，但服务器响应的状态码不在 2xx 范围内
            console.log(error.response.data)
            console.log(error.response.status)
            console.log(error.response.headers)
          } else {
            console.log(error)
            // Something happened in setting up the request that triggered an Error
            console.log('Error', error.message)
          }
          console.log(error.config)
        })
    })
  }
})
export default ajax

/* 1 根据process.env.NODE_ENV 获取对应的apiDomain
 * 2 处理ajax库axios，为了以后不重复引用，挂在原型对象上
 * 3 axios是封装在main.js里面的，是为了获取vue实例操作store、router
 * 4 组件里面使用this.$axios.get or  this.$axios.post 调用  使用debugger，查看接口返回数据的走向
 */
