import { getUserInfo } from '@/utils/auth'

const getters = {
  sidebar: state => state.app.sidebar,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => {
    if (!state.user.name) {
      const data = JSON.parse(getUserInfo())
      return data.userName
    }
    return state.user.name
  },
  roles: state => state.user.roles,
  nick_name: state => {
    if (!state.user.nick_name) {
      const data = JSON.parse(getUserInfo())
      return data.nickName
    }
    return state.user.nick_name
  }
}
export default getters
