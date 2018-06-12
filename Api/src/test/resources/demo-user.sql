/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-06-23 14:25:27
*/
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `user_name` VARCHAR (30) NOT NULL COMMENT '用户名编号',
  `nick_name` VARCHAR (255) NOT NULL COMMENT '姓名',
  `password` VARCHAR(128) NOT NULL COMMENT '密码',
  `portrait` VARCHAR(300) DEFAULT '' COMMENT '头像路径',
  `signature` VARCHAR(500) DEFAULT '' COMMENT '个性签名',
  `sex` INT(1) DEFAULT '1' COMMENT '性别' ,
  `status` INT(4) NOT NULL DEFAULT '1' COMMENT '状态',
  `register_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
  `register_ip` VARCHAR(128) COMMENT '注册IP',
  `login_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `login_ip` VARCHAR(128) COMMENT '登录IP',
  `enable_flag` INT(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

DROP TABLE IF EXISTS `impress_tag`;
CREATE TABLE `impress_tag` (
  `impress_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `user_name` VARCHAR (30) NOT NULL COMMENT '用户名编号',
  `tag` VARCHAR (50) NOT NULL COMMENT '印象标签',
  `count` INT(10) NOT NULL DEFAULT '0' COMMENT '赞同人数',
  `create_by` VARCHAR (30) NOT NULL COMMENT '创建人',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` VARCHAR(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` INT(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`impress_id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='印象标签';

DROP TABLE IF EXISTS `impress_tag_detail`;
CREATE TABLE `impress_tag_detail` (
  `impress_detail_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `impress_id` INT(11) NOT NULL COMMENT '主表外键',
  `agreeBy` VARCHAR (30) NOT NULL COMMENT '赞同人',
  `count` INT(11) NOT NULL DEFAULT '0' COMMENT '次数',
  `create_by` VARCHAR (30) NOT NULL COMMENT '创建人',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` VARCHAR(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` INT(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`impress_detail_id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='印象标签详细';

DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise` (
  `praise_id` INT(11) NOT NULL AUTO_INCREMENT  COMMENT '主键(自增长)',
  `praise_from` VARCHAR(50) NOT NULL  COMMENT '来自',
  `praise_to` VARCHAR (50) NOT NULL COMMENT '目标',
  `content` VARCHAR(2000) NOT NULL DEFAULT '0' COMMENT '内容',
  `create_by` VARCHAR (30) NOT NULL COMMENT '创建人',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` VARCHAR(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` INT(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`praise_id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='点赞表';

DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `meeting_id` INT(11) NOT NULL AUTO_INCREMENT  COMMENT '主键(自增长)',
  `tally` INT(4) COMMENT '会议次数',
  `start` DATE COMMENT '开始时间',
  `end` DATE COMMENT '结束时间',
  `theme` VARCHAR(200) COMMENT '内容',
  `content` VARCHAR(2000) COMMENT '内容',
  `is_send_email` tinyint(1) DEFAULT '0' COMMENT '是否发送邮件',
  `create_by` VARCHAR (30) NOT NULL COMMENT '创建人',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` VARCHAR(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` INT(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`meeting_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会议表';

DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `tasks_id` INT(11) NOT NULL AUTO_INCREMENT  COMMENT '主键(自增长)',
  `demand_title` VARCHAR(300) COMMENT '需求标题',
  `demand_content` VARCHAR(2000) COMMENT '需求内容',
  `status` INT(1) DEFAULT '0' COMMENT '状态 0待充能,1待接受,2待完成,3已完成',
  `accept_by` VARCHAR (30) COMMENT '接受人',
  `accept_date` DATETIME COMMENT '接受时间',
  `create_by` VARCHAR (30) NOT NULL COMMENT '创建人',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` VARCHAR(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` INT(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`tasks_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='任务表';

DROP TABLE IF EXISTS `tasks_detail`;
CREATE TABLE `tasks_detail` (
  `tasks_detail_id` INT(11) NOT NULL AUTO_INCREMENT  COMMENT '主键(自增长)',
  `tasks_id` INT(11) NOT NULL COMMENT '任务ID',
  `demand_content` VARCHAR(2000) DEFAULT '0' COMMENT '补充信息',
  `evaluate` DOUBLE(3,2) DEFAULT '0' COMMENT '打分',
  `comment` VARCHAR(1000) DEFAULT '' COMMENT '评论',
  `is_comment` INT(1) DEFAULT '0' COMMENT '是否评论',
  `create_by` VARCHAR (30) NOT NULL COMMENT '创建人',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` VARCHAR(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` INT(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`tasks_detail_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='任务明细';

DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `vote_id` INT(11) NOT NULL AUTO_INCREMENT  COMMENT '主键(自增长)',
  `theme` VARCHAR(1000) COMMENT '主题',
  `additional` VARCHAR(2000) COMMENT '补充信息',
  `type` INT(10) COMMENT '投票类型 1.积分制,2.单选,3.多选',
  `votes_number` INT(10) DEFAULT 1 COMMENT '票数,单选,多选为默认为1 积分制根据输入',
  `select_myself` TINYINT(1) DEFAULT '0' COMMENT '是否可选择自己',
  `most_select` INT(10) COMMENT '最多选项',
  `least_select` INT(10) COMMENT '最少选项',
  `close_date` DATETIME NOT NULL COMMENT '关闭时间',
  `is_send_email` TINYINT(1) DEFAULT '0' COMMENT '是否发送邮件',
  `create_by` VARCHAR (30) NOT NULL COMMENT '创建人',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` VARCHAR(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` INT(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`vote_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='投票表';

DROP TABLE IF EXISTS `vote_option`;
CREATE TABLE `vote_option` (
  `option_id` INT(11) NOT NULL AUTO_INCREMENT  COMMENT '主键(自增长)',
  `vote_id` INT(11) NOT NULL COMMENT '主表ID',
  `option_text` VARCHAR(1000) DEFAULT '0' COMMENT '选项',
  `create_by` VARCHAR (30) NOT NULL COMMENT '创建人',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` VARCHAR(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` INT(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`option_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='投票选项';

DROP TABLE IF EXISTS `vote_option_detail`;
CREATE TABLE `vote_option_detail` (
  `option_detail_id` INT(11) NOT NULL AUTO_INCREMENT  COMMENT '主键(自增长)',
  `option_id` INT(11) NOT NULL COMMENT '主表ID',
  `voters` VARCHAR(30) COMMENT '投票人',
  `share` INT(11) DEFAULT 0 COMMENT '票数',
  `create_by` VARCHAR(30) NOT NULL COMMENT '创建人',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` VARCHAR(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` INT(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`option_detail_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='选项明细';