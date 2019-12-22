export class ApiUrl {
  // 首页柱状图数据
  static getCharts = '/meeting/charts';
  // 首页会议信息
  static getNewestMeeting = '/meeting/newest';
  // 首页周期获赞前3
  static getPariseTop3 = '/praise/top';
  // 首页未关闭投票前3
  static getVoteTop3 = '/vote/top3';
  // 首页未领取的任务前3
  static getTasksTop3 = '/tasks/top3';

  // 人员信息
  static usersUrl = '/users';

  // 封装人员组件
  static getUsersListByNameUrl = '/users/list';
  // 查询人员信息
  static getUsersInfoUrl = '/users/info';
  // 上传图片
  static userAvatarUploadUrl = '/users/fileUpload';

  // 获取主页信息
  static getUsersInfoUrl = '/users/info/';
  // 查询所有标签
  static getImpressTagUrl = '/impress/tag/query/';
  // 保存标签
  static impressTagAddUrl = '/impress/tag';
  // 查询点赞画像
  static praiseFromPortraitUrl = '/praise/from/portrait/';
  // 查询获赞画像
  static praiseToPortraitUrl = '/praise/to/portrait/';

  // 保存个人信息
  static saveUserInfoUrl = '/users';

  // 修改密码
  static modifyPasswordUrl = '/users/modifyPassword';
  // 登录
  static loginUrl = '/users/login';
  // 验证用户名
  static validUserNameUrl = '/public/valid/';
  // 注册用户
  static registerUrl = '/public/register';
  // 点赞列表
  static praiseListUrl = '/praise/list';
  // 点赞详情
  static praiseDetailUrl = '/praise/detail';
  // 点赞热词
  static hotWordsUrl = '/hot/words';
  // 我的点赞
  static myPraise = '/praise/to/list';

  static praiseAddUrl = '/praise';
  static probablyUrl = '/praise/probably';
  static rectentTop3 = '/praise/recent/top3';

  // 任务模块
  static addTasksUrl = '/tasks';
  // 任务列表
  static getListTasksUrl = '/tasks';
  // 新增任务明细
  static addTaskDetailsUrl = '/tasks/detail';
  // 接受任务
  static receiveUrl = '/tasks/receive';
  // 任务明细
  static getAlltaskDetailsUrl = '/tasks/detail/all/';
  // 新增任务明细
  static taskDetailsUrl = '/tasks/detail';

  // 会议列表
  static getMeetingListUrl = '/meeting';
  // 新会议
  static meetingUrl = '/meeting';

  // 投票模块
  static voteUrl = '/vote';

  // 投票选项明细
  static voteOptionDetailUrl = '/vote/option/detail';

  // 已选择的选项
  static voteOptionChoosedUrl = '/vote/option/detail/choosed/';

  // 动态/日志
  static getRecordByRelevant = '/log/getRecordByRelevant/';

  static envMapUrl = '/env/map';

  static envMapSave = '/env/map/table/data/save';
  static envMapDelete = '/env/map/table/data/delete';

  static envMapFrame = '/env/map/frame';
  static envMapFrameSave = '/env/map/frame/save';
  static envMapFrameDelete = '/env/map/frame/delete';

  static frameJoinTableSave = '/env/map/frame/join/table/save';
  static frameJoinTableDelete = '/env/map/frame/join/table/delete';

  static envMapTable = '/env/map/table';
  static envMapTableSave = '/env/map/table/save';
  static envMapTableDelete = '/env/map/table/delete';

  static envMapTableFrame = '/env/map/table/frame';
  static envMapTableFrameSave = '/env/map/table/frame/save';
  static envMapTableFrameDelete = '/env/map/table/frame/delete';
}
