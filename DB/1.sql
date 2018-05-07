/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.6.17 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `impress_tag` (
	`impress_id` int (11),
	`user_name` varchar (90),
	`tag` varchar (150),
	`count` int (10),
	`create_by` varchar (90),
	`create_date` datetime ,
	`last_update_by` varchar (90),
	`last_update_date` datetime ,
	`enable_flag` int (1)
); 
