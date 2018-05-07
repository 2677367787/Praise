/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.6.17 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `users` (
	`id` int (11),
	`user_name` varchar (90),
	`nick_name` varchar (765),
	`password` varchar (384),
	`portrait` varchar (900),
	`signature` varchar (1500),
	`sex` int (1),
	`status` int (4),
	`register_date` datetime ,
	`register_ip` varchar (384),
	`login_time` datetime ,
	`login_ip` varchar (384),
	`enable_flag` int (1)
); 
