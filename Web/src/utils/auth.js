import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'
const UserInfoKey = 'Admin-info'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token, { expires: 60 })
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function setUserInfo(userInfo) {
  return Cookies.set(UserInfoKey, userInfo, { expires: 60 })
}

export function getUserInfo() {
  return Cookies.get(UserInfoKey)
}
