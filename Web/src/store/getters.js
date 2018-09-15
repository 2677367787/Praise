import { getUserInfo } from '@/utils/auth'

const getters = {
  sidebar: state => state.app.sidebar,
  token: state => state.user.token,
  avatar: state => {
    if (!state.user.avatar) {
      const userInfo = getUserInfo()
      if (!userInfo) return ''
      const data = JSON.parse(userInfo)
      return data.avatar
    }
    return state.user.avatar
  },
  name: state => {
    if (!state.user.name) {
      const userInfo = getUserInfo()
      if (!userInfo) return ''
      const data = JSON.parse(userInfo)
      return data.userName
    }
    return state.user.name
  },
  roles: state => state.user.roles,
  nick_name: state => {
    if (!state.user.nick_name) {
      const userInfo = getUserInfo()
      if (!userInfo) return ''
      const data = JSON.parse(userInfo)
      return data.nickName
    }
    return state.user.nick_name
  }
}
export default getters
