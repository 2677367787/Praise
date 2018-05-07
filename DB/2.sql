/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.6.17 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `praise` (
	`praise_id` int (11),
	`praise_from` varchar (150),
	`praise_to` varchar (150),
	`content` varchar (6000),
	`create_by` varchar (90),
	`create_date` datetime ,
	`last_update_by` varchar (90),
	`last_update_date` datetime ,
	`enable_flag` int (1)
); 
