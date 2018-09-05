import { getToken, setToken, removeToken, setUserInfo, getUserInfo } from '@/utils/auth'
import ajax from '@/api/ajax'

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    nick_name: '',
    roles: []
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_NICK_NAME: (state, nick_name) => {
      state.nick_name = nick_name
    }
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      return new Promise((resolve, reject) => {
        ajax.post(userInfo.url, userInfo).then(response => {
          const data = response.data
          console.log(data)
          setToken(data.token)
          setUserInfo(data)
          commit('SET_TOKEN', data.token)
          commit('SET_NICK_NAME', data.nickName)
          commit('SET_NAME', data.userName)
          commit('SET_AVATAR', data.avatar)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 从本地存储获取用户名
    GetUserName({ commit, state }) {
      return new Promise((resolve, reject) => {
        const data = getUserInfo()
        console.log(data)
        commit('SET_NICK_NAME', data.nickName)
        commit('SET_NAME', data.userName)
        resolve(data)
      }).catch(error => {
        console.log(error)
      })
    },
    // 获取用户信息
    // GetInfo({ commit, state }) {
    //   return new Promise((resolve, reject) => {
    //     getInfo(state.token).then(response => {
    //       const data = response.data
    //       // commit('SET_ROLES', data.roles)
    //       // commit('SET_NAME', data.name)
    //       // commit('SET_AVATAR', data.avatar)
    //       resolve(response)
    //     }).catch(error => {
    //       console.log(error)
    //       reject(error)
    //     })
    //   })
    // },

    // 登出
    LogOut({ commit, state }) {
      console.log('1213')
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
  }
}

export default user
