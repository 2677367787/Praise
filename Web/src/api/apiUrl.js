export class ApiUrl {
  // 首页柱状图数据
  static getCharts = '/meeting/charts'
  // 首页会议信息
  static getNewestMeeting = '/meeting/newest'

  // 封装人员组件
  static getUsersListByNameUrl = '/users/'
  // 查询人员信息
  static getUsersInfoUrl = '/users/info'
  // 上传图片
  static userAvatarUploadUrl = '/users/fileUpload'

  // 获取主页信息
  static getUsersInfoUrl = '/users/info/'
  // 查询所有标签
  static getImpressTagUrl = '/impress/tag/query/'
  // 保存标签
  static impressTagAddUrl = '/impress/tag'

  // 保存个人信息
  static saveUserInfoUrl = '/users'

  // 修改密码
  static modifyPasswordUrl = '/users/modifyPassword'
  // 登录
  static loginUrl = '/users/login'
  // 点赞列表
  static praiseListUrl = '/praise/list'

  static praiseDetailUrl = '/praise/detail'

  static praiseAddUrl = '/praise'
}
