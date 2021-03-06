﻿/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.6.17 : Database - praisesystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`praisesystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `praisesystem`;

/*Table structure for table `impress_tag` */

DROP TABLE IF EXISTS `impress_tag`;

CREATE TABLE `impress_tag` (
  `impress_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `user_name` varchar(30) NOT NULL COMMENT '用户名编号',
  `tag` varchar(50) NOT NULL COMMENT '印象标签',
  `count` int(10) NOT NULL DEFAULT '0' COMMENT '赞同人数',
  `create_by` varchar(30) NOT NULL COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` varchar(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` int(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`impress_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='印象标签';

/*Data for the table `impress_tag` */

insert  into `impress_tag`(`impress_id`,`user_name`,`tag`,`count`,`create_by`,`create_date`,`last_update_by`,`last_update_date`,`enable_flag`) values 
(1,'233','765758',0,'6396000749','2018-05-13 19:01:16','6396000749','2018-05-13 19:01:16',1),
(2,'6396000748','124124',0,'6396000749','2018-05-13 19:04:09','6396000749','2018-05-13 19:04:09',1),
(3,'6396000748','546',0,'6396000749','2018-05-13 19:04:35','6396000749','2018-05-13 19:04:35',1),
(4,'6396000748','124124',0,'6396000749','2018-05-13 19:05:17','6396000749','2018-05-13 19:05:17',1),
(5,'6396000748','23333',0,'6396000749','2018-05-13 19:05:32','6396000749','2018-05-13 19:05:32',1),
(6,'6396000748','1111111',0,'6396000749','2018-05-13 19:05:40','6396000749','2018-05-13 19:05:40',1),
(7,'6396000748','42421',0,'6396000749','2018-05-13 19:05:53','6396000749','2018-05-13 19:05:53',1),
(8,'6396000748','1233',0,'6396000749','2018-05-13 19:06:44','6396000749','2018-05-13 19:06:44',1),
(9,'6396000748','555',0,'6396000749','2018-05-13 19:06:45','6396000749','2018-05-13 19:06:45',1),
(16,'6396000748','12421',0,'6396000749','2018-05-13 20:17:48','6396000749','2018-05-13 20:17:48',1),
(17,'6396000748','33333',0,'6396000749','2018-05-13 20:18:40','6396000749','2018-05-13 20:18:40',1),
(18,'6396000748','111',0,'6396000749','2018-05-13 20:25:31','6396000749','2018-05-13 20:25:31',1),
(19,'6396000748','2333',0,'6396000749','2018-05-13 20:25:35','6396000749','2018-05-13 20:25:35',1),
(20,'6396000748','124124',0,'6396000749','2018-05-13 20:26:03','6396000749','2018-05-13 20:26:03',1),
(21,'6396000748','323',0,'6396000749','2018-05-13 20:26:04','6396000749','2018-05-13 20:26:04',1),
(22,'6396000748','2222222222222222222222222222222',0,'6396000749','2018-05-13 20:26:21','6396000749','2018-05-13 20:26:21',1),
(23,'6396000748','3333333333333333333333333333333333333',0,'6396000749','2018-05-13 20:26:24','6396000749','2018-05-13 20:26:24',1),
(24,'6396000748','33333333',0,'6396000749','2018-05-13 20:26:29','6396000749','2018-05-13 20:26:29',1),
(25,'6396000748','44444444443',0,'6396000749','2018-05-13 20:26:33','6396000749','2018-05-13 20:26:33',1),
(26,'6396000741','9999',0,'6396000749','2018-05-14 20:08:23','6396000749','2018-05-14 20:08:23',1),
(27,'6396000743','帅',0,'6396000749','2018-05-14 22:44:31','6396000749','2018-05-14 22:44:31',1),
(28,'6396000745','。、。。233',0,'6396000749','2018-05-14 22:52:23','6396000749','2018-05-14 22:52:23',1),
(29,'6396000741','342141',0,'6396000748','2018-05-14 23:44:13','6396000748','2018-05-14 23:44:13',1),
(30,'6396000748','2312123',0,'6396000749','2018-05-19 09:28:17','6396000749','2018-05-19 09:28:17',1),
(31,'6396000748','23',0,'6396000749','2018-05-19 10:07:57','6396000749','2018-05-19 10:07:57',1),
(32,'6396000748','214124124',0,'6396000749','2018-05-19 10:24:07','6396000749','2018-05-19 10:24:07',1),
(33,'6396000748','213214124124',0,'6396000749','2018-05-19 10:24:10','6396000749','2018-05-19 10:24:10',1),
(34,'6396000743','12312',0,'22','2018-05-30 22:16:45','22','2018-05-30 22:16:45',1),
(35,'6396000743','未全额',0,'22','2018-05-30 22:16:47','22','2018-05-30 22:16:47',1),
(36,'6396000743','123123',0,'22','2018-05-30 22:16:49','22','2018-05-30 22:16:49',1),
(37,'6396000743','123123',0,'22','2018-05-30 22:16:50','22','2018-05-30 22:16:50',1),
(38,'6396000743','23123',0,'22','2018-05-30 22:16:52','22','2018-05-30 22:16:52',1),
(39,'6396000743','222222222222222222222222222222222222',0,'22','2018-05-30 22:17:01','22','2018-05-30 22:17:01',1),
(40,'6396000743','33333333333333333333333333333333333',0,'22','2018-05-30 22:17:04','22','2018-05-30 22:17:04',1),
(41,'6396000743','1244444444444444444444444444444444',0,'22','2018-05-30 22:17:07','22','2018-05-30 22:17:07',1),
(42,'6396000743','22222222222222222222222222222222',0,'22','2018-05-30 22:17:09','22','2018-05-30 22:17:09',1),
(43,'6396000743','213333333333333333333',0,'22','2018-05-30 22:17:13','22','2018-05-30 22:17:13',1),
(44,'6396000743','123',0,'22','2018-05-30 22:18:49','22','2018-05-30 22:18:49',1),
(45,'6396000743','123',0,'22','2018-05-30 22:18:50','22','2018-05-30 22:18:50',1),
(46,'6396000743','123',0,'22','2018-05-30 22:18:51','22','2018-05-30 22:18:51',1),
(47,'admin','qq12312',0,'6396000749','2018-06-03 18:37:35','6396000749','2018-06-03 18:37:35',1),
(48,'admin','asfas ',0,'6396000749','2018-06-03 18:37:37','6396000749','2018-06-03 18:37:37',1);

/*Table structure for table `impress_tag_detail` */

DROP TABLE IF EXISTS `impress_tag_detail`;

CREATE TABLE `impress_tag_detail` (
  `impress_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `impress_id` int(11) NOT NULL COMMENT '主表外键',
  `agreeBy` varchar(30) NOT NULL COMMENT '赞同人',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '次数',
  `create_by` varchar(30) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `last_update_by` varchar(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` datetime NOT NULL COMMENT '最后更新时间',
  `enable_flag` int(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`impress_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='印象标签详细';

/*Data for the table `impress_tag_detail` */

/*Table structure for table `meeting` */

DROP TABLE IF EXISTS `meeting`;

CREATE TABLE `meeting` (
  `meeting_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `tally` int(4) DEFAULT NULL,
  `start` date DEFAULT NULL COMMENT '开始时间',
  `end` date DEFAULT NULL COMMENT '结束时间',
  `theme` varchar(200) DEFAULT NULL COMMENT '内容',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `is_send_email` tinyint(1) DEFAULT '0' COMMENT '是否发送邮件',
  `create_by` varchar(30) NOT NULL COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` varchar(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` int(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`meeting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='会议表';

/*Data for the table `meeting` */

insert  into `meeting`(`meeting_id`,`tally`,`start`,`end`,`theme`,`content`,`is_send_email`,`create_by`,`create_date`,`last_update_by`,`last_update_date`,`enable_flag`) values 
(1,11,'2018-01-01','2018-01-14','测试12222','333333',0,'','2018-05-12 23:48:02','','2018-05-12 23:48:02',1),
(2,2,'2018-01-15','2018-01-31','测试2','内容',0,'','2018-05-12 23:48:12','','2018-05-12 23:48:12',1),
(3,3,'2018-02-01','2018-02-28','测试3','2333',0,'','2018-05-12 23:48:19','','2018-05-12 23:48:19',1),
(4,4,'2018-03-01','2018-03-15','测试4','23333',1,'','2018-05-12 23:48:45','','2018-05-12 23:48:45',1),
(5,5,'2018-03-16','2018-03-31','测试5','',1,'','2018-05-12 23:48:55','','2018-05-12 23:48:55',1),
(6,6,'2018-04-01','2018-04-15','测试6',NULL,0,'','2018-05-12 23:49:08','','2018-05-12 23:49:08',1),
(7,7,'2018-04-16','2018-04-30','测试7',NULL,0,'','2018-05-12 23:50:23','','2018-05-12 23:50:23',1),
(8,8,'2018-05-01','2018-05-13','测试8',NULL,0,'','2018-05-12 23:50:40','','2018-05-12 23:50:40',1),
(9,1,'2018-05-23','2018-05-30','12','124',0,'22','2018-05-30 23:49:02','22','2018-05-30 23:49:02',1),
(10,10,'2018-04-30','2018-04-30','12','124',0,'22','2018-05-30 23:51:47','22','2018-05-30 23:51:47',1);

/*Table structure for table `praise` */

DROP TABLE IF EXISTS `praise`;

CREATE TABLE `praise` (
  `praise_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `praise_from` varchar(50) NOT NULL COMMENT '来自',
  `praise_to` varchar(50) NOT NULL COMMENT '目标',
  `content` varchar(2000) NOT NULL COMMENT '内容',
  `create_by` varchar(30) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `last_update_by` varchar(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` datetime NOT NULL COMMENT '最后更新时间',
  `enable_flag` int(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`praise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='点赞表';

/*Data for the table `praise` */

insert  into `praise`(`praise_id`,`praise_from`,`praise_to`,`content`,`create_by`,`create_date`,`last_update_by`,`last_update_date`,`enable_flag`) values 
(1,'6396000743','6396000749','3425','6396000741','2018-04-18 21:49:02','6396000741','2018-04-18 21:49:02',1),
(2,'6396000745','6396000749','帅','6396000741','2018-01-18 21:49:02','6396000741','2018-04-18 21:49:02',1),
(3,'6396000741','6396000749','能力强','6396000741','2018-02-18 21:49:02','6396000741','2018-04-18 21:49:02',1),
(4,'6396000748','6396000741','而我份额为','6396000742','2018-03-18 21:49:02','6396000742','2018-04-18 21:49:02',1),
(5,'6396000741','6396000749','段位高','6396000742','2018-04-18 21:49:02','6396000742','2018-04-18 21:49:02',1),
(6,'6396000749','6396000748','额外高温高','6396000742','2018-01-11 21:49:02','6396000742','2018-04-18 21:49:02',1),
(7,'6396000749','6396000741','24124','6396000749','2018-05-05 23:16:03','6396000749','2018-05-05 23:16:03',1),
(8,'6396000749','6396000743','3333333333','6396000749','2018-05-05 23:18:24','6396000749','2018-05-05 23:18:24',1),
(9,'6396000749','6396000743','33333333333333','6396000749','2018-05-05 23:19:31','6396000749','2018-05-05 23:19:31',1),
(10,'6396000749','6396000741','1111111111111111','6396000749','2018-05-05 23:20:13','6396000749','2018-05-05 23:20:13',1),
(11,'6396000749','6396000748','23333333333333','6396000749','2018-05-06 20:29:35','6396000749','2018-05-06 20:29:35',1),
(12,'6396000749','6396000748','233333333','6396000749','2018-05-09 21:52:37','6396000749','2018-05-09 21:52:37',1),
(13,'6396000749','6396000748','呆呆呆呆呆呆呆呆地','6396000749','2018-05-09 21:52:57','6396000749','2018-05-09 21:52:57',1),
(19,'6396000749','6396000741','李四','6396000749','2018-05-13 17:32:16','6396000749','2018-05-13 17:32:16',1),
(20,'6396000749','6396000743','第三方速度','6396000749','2018-05-13 17:32:50','6396000749','2018-05-13 17:32:50',1),
(21,'6396000749','6396000741','大杀四方','6396000749','2018-05-13 17:37:05','6396000749','2018-05-13 17:37:05',1),
(22,'6396000749','6396000748','12412','6396000749','2018-05-13 22:50:08','6396000749','2018-05-13 22:50:08',1),
(23,'6396000749','6396000741','但我却都劝我放弃我','6396000749','2018-05-14 23:00:10','6396000749','2018-05-14 23:00:10',1),
(24,'admin','6396000741','111111111111111111111111','admin','2018-05-16 22:27:06','admin','2018-05-16 22:27:06',1),
(25,'admin','6396000743','2222222222222','admin','2018-05-16 22:27:13','admin','2018-05-16 22:27:13',1),
(26,'6396000749','6396000745','1111111','6396000749','2018-05-19 08:03:13','6396000749','2018-05-19 08:03:13',1),
(27,'6396000749','6396000741','333333333','6396000749','2018-05-19 08:03:25','6396000749','2018-05-19 08:03:25',1),
(28,'6396000749','6396000741','444444444','6396000749','2018-05-19 09:13:28','6396000749','2018-05-19 09:13:28',1),
(29,'6396000749','admin','谷歌广告','6396000749','2018-05-19 09:23:31','6396000749','2018-05-19 09:23:31',1);

/*Table structure for table `tasks` */

DROP TABLE IF EXISTS `tasks`;

CREATE TABLE `tasks` (
  `tasks_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `demand_title` varchar(300) DEFAULT NULL COMMENT '需求标题',
  `demand_content` varchar(2000) DEFAULT NULL COMMENT '需求内容',
  `status` int(1) DEFAULT '0' COMMENT '状态 0待充能,1待接受,2待完成,3已完成',
  `accept_by` varchar(30) DEFAULT NULL COMMENT '接受人',
  `accept_date` datetime DEFAULT NULL COMMENT '接受时间',
  `create_by` varchar(30) NOT NULL COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` varchar(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` int(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`tasks_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='任务表';

/*Data for the table `tasks` */

insert  into `tasks`(`tasks_id`,`demand_title`,`demand_content`,`status`,`accept_by`,`accept_date`,`create_by`,`create_date`,`last_update_by`,`last_update_date`,`enable_flag`) values 
(1,'111111111','333333333333333',2,'6396000749','2018-06-03 23:59:15','22','2018-05-31 23:14:31','22','2018-05-31 23:14:31',1),
(2,'222222222222','没有什么要补充的，我只想单纯的帮忙点个赞~',2,'6396000749','2018-06-03 17:12:59','22','2018-05-31 23:30:51','22','2018-05-31 23:30:51',1),
(10,'行政村感受到过分的话电风扇发达国家','行政村感受到过分的话电风扇发达国家行政村感受到过分的话电风扇发达国家行政村感受到过分的话电风扇发达国家行政村感受到过分的话电风扇发达国家行政村感受到过分的话电风扇发达国家行政村感受到过分的话电风扇发达国家行政村感受到过分的话电风扇发达国家行政村感受到过分的话电风扇发达国家行政村感受到过分的话电风扇发达国家行政村感受到过分的话电风扇发达国家',2,'6396000748','2018-06-03 19:25:47','6396000749','2018-06-03 17:28:13','6396000749','2018-06-03 17:28:13',1),
(11,'1233333333333333','222222222222',0,NULL,NULL,'6396000749','2018-06-03 18:11:03','6396000749','2018-06-03 18:11:03',1);

/*Table structure for table `tasks_detail` */

DROP TABLE IF EXISTS `tasks_detail`;

CREATE TABLE `tasks_detail` (
  `tasks_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `tasks_id` int(11) NOT NULL COMMENT '任务ID',
  `demand_content` varchar(2000) DEFAULT '0' COMMENT '补充信息',
  `evaluate` double(3,2) DEFAULT '0.00' COMMENT '打分',
  `comment` varchar(1000) DEFAULT '' COMMENT '评论',
  `create_by` varchar(30) NOT NULL COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` varchar(30) NOT NULL COMMENT '最后更新人',
  `last_update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` int(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`tasks_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='任务明细';

/*Data for the table `tasks_detail` */

insert  into `tasks_detail`(`tasks_detail_id`,`tasks_id`,`demand_content`,`evaluate`,`comment`,`create_by`,`create_date`,`last_update_by`,`last_update_date`,`enable_flag`) values 
(1,1,'没有什么要补充的，我只想单纯的帮忙点个赞~',3.50,'','6396000748','2018-06-03 19:52:19','6396000748','2018-06-03 19:52:19',1),
(2,1,'没有什么要补充的，我只想单纯的帮忙点个赞~',0.00,'','6396000748','2018-06-03 19:52:20','6396000748','2018-06-03 19:52:20',1),
(3,1,'没有什么要补充的，我只想单纯的帮忙点个赞~',0.00,'','6396000749','2018-06-03 23:55:01','6396000749','2018-06-03 23:55:01',1),
(4,2,'没有什么要补充的，我只想单纯的帮忙点个赞~',3.00,'','6396000749','2018-06-03 23:55:19','6396000749','2018-06-03 23:55:19',1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `user_name` varchar(30) NOT NULL COMMENT '用户名编号',
  `nick_name` varchar(255) NOT NULL COMMENT '姓名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `portrait` varchar(300) DEFAULT '' COMMENT '头像路径',
  `signature` varchar(500) DEFAULT '' COMMENT '个性签名',
  `sex` int(1) DEFAULT '1' COMMENT '性别',
  `status` int(4) NOT NULL DEFAULT '1' COMMENT '状态',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
  `register_ip` varchar(128) DEFAULT NULL COMMENT '注册IP',
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `login_ip` varchar(128) DEFAULT NULL COMMENT '登录IP',
  `enable_flag` int(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `users` */

insert  into `users`(`id`,`user_name`,`nick_name`,`password`,`portrait`,`signature`,`sex`,`status`,`register_date`,`register_ip`,`login_time`,`login_ip`,`enable_flag`) values 
(2,'6396000749','唐志','fbaa75a6bad8bbdbf509fa4f95757af6','http://localhost:8099/images/2.jpg','一点一滴的磨练都是通往强者之路的基石',1,1,'2018-04-14 16:15:06','','2018-04-14 17:27:23','',1),
(3,'6396000748','黄三','01e19b80f9ad9634ed53c1701694aaec','','241244242421421',0,0,'2018-03-15 16:15:06','','2018-04-14 16:15:06','',1),
(4,'admin','管理员','b676cc5fb659ffe396904c581c3503b2','http://localhost:8099/images/4.jpg','',NULL,0,'2018-03-28 16:15:06','','2018-04-14 16:15:06','',1),
(5,'6396000741','张三','24e6019fe5f90a76a2126b013e2c69e8','','',NULL,0,'2018-03-28 16:15:06','','2018-04-14 16:15:06','',1),
(6,'6396000743','李四','864f5b4a9c5d4e45d0efe3f89545843b','','',NULL,0,'2018-03-28 16:15:06','','2018-04-14 16:15:06','',1),
(7,'6396000745','王武','2274f14708662553d6e633cfc5418863','','',NULL,0,'2018-03-28 16:15:06','','2018-04-14 16:15:06','',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

CREATE TABLE `test_case` (
  `case_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增长)',
  `content` VARCHAR(2000) DEFAULT NULL COMMENT '需求内容',
  `level` INT(1) DEFAULT '0' COMMENT 'BUG 级别 10高 20中 30低级',
  `status` INT(1) DEFAULT '0' COMMENT '状态 10 待接受,20待验证 ,30打回, 40关闭',
  `handler_by` VARCHAR(30) DEFAULT NULL COMMENT '当前处理人',
  `handler_date` DATETIME DEFAULT NULL COMMENT '接受时间',
  `repulse_count` INT(1) DEFAULT '0' COMMENT '打回次数',
  `create_by` VARCHAR(30) COMMENT '创建人',
  `create_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` VARCHAR(30) COMMENT '最后更新人',
  `last_update_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `enable_flag` INT(1) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`case_id`)
) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='任务表'