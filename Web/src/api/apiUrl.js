export class ApiUrl {
  // 首页柱状图数据
  static getCharts = '/meeting/charts'
  // 首页会议信息
  static getNewestMeeting = '/meeting/newest'

  // 人员信息
  static usersUrl = '/users'

  // 封装人员组件
  static getUsersListByNameUrl = '/users/list'
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
  // 查询点赞画像
  static praiseFromPortraitUrl = '/praise/from/portrait/'
  // 查询获赞画像
  static praiseToPortraitUrl = '/praise/to/portrait/'

  // 保存个人信息
  static saveUserInfoUrl = '/users'

  // 修改密码
  static modifyPasswordUrl = '/users/modifyPassword'
  // 登录
  static loginUrl = '/users/login'
  // 验证用户名
  static validUserNameUrl = '/public/valid/'
  // 注册用户
  static registerUrl = '/public/register'
  // 点赞列表
  static praiseListUrl = '/praise/list'
  // 点赞详情
  static praiseDetailUrl = '/praise/detail'

  static praiseAddUrl = '/praise'

  // 任务模块
  static addTasksUrl = '/tasks'
  // 任务列表
  static getListTasksUrl = '/tasks'
  // 新增任务明细
  static addTaskDetailsUrl = '/tasks/detail'
  // 接受任务
  static receiveUrl = '/tasks/receive'
  // 任务明细
  static getAlltaskDetailsUrl = '/tasks/detail/all/'
  // 新增任务明细
  static taskDetailsUrl = '/tasks/detail'

  // 会议列表
  static getMeetingListUrl = '/meeting'
  // 新会议
  static meetingUrl = '/meeting'
}
