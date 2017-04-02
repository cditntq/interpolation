-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: itntqdb
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `job_seeker_infos`
--

DROP TABLE IF EXISTS `job_seeker_infos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_seeker_infos` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `job_seeker_name` varchar(20) NOT NULL COMMENT '求职者姓名',
  `job_seeker_sex` bigint(1) NOT NULL COMMENT '1-男，2-女',
  `job_seeker_phone` bigint(11) NOT NULL COMMENT '求职者电话',
  `job_seeker_email` varchar(50) NOT NULL COMMENT '求职者邮箱',
  `job_seeker_weixin` varchar(30) NOT NULL COMMENT '求职者微信ID',
  `graduate_school` varchar(100) NOT NULL COMMENT '毕业学校',
  `major_subjects` varchar(100) NOT NULL COMMENT '所学专业',
  `graduate_date` date NOT NULL COMMENT '毕业日期',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `is_valid` int(1) NOT NULL DEFAULT '1' COMMENT '是否有效，1-有效，2-无效，3-黑名单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='求职者信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_seeker_infos`
--

/*!40000 ALTER TABLE `job_seeker_infos` DISABLE KEYS */;
INSERT INTO `job_seeker_infos` VALUES (2,'杨爽123',1,15123247202,'247677858@qq.com','1235556','ddgef','计算机','2017-03-13','2017-03-19 10:31:18.012','2017-03-31 15:12:22.346',2),(3,'杨爽123',1,15123247202,'247677858@qq.com','1235556','ddgef','计算机','2017-03-13','2017-03-19 10:31:45.845','2017-03-30 04:02:53.051',1),(4,'杨爽123',1,15123247203,'247677858@qq.com','1235556','ddgef','计算机','2017-03-13','2017-03-19 10:36:22.736','2017-03-30 05:33:23.800',1);
/*!40000 ALTER TABLE `job_seeker_infos` ENABLE KEYS */;

--
-- Table structure for table `job_seeker_resume_delivery`
--

DROP TABLE IF EXISTS `job_seeker_resume_delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_seeker_resume_delivery` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `job_seeker_infos_id` bigint(20) NOT NULL COMMENT '求职者信息表主键ID',
  `job_code` varchar(20) NOT NULL COMMENT '投递职位编码',
  `resume_path` varchar(200) NOT NULL COMMENT '简历存放位置',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `deal_status` int(2) NOT NULL DEFAULT '1' COMMENT '处理状态，1-待处理，2-已投递至企业，3-企业筛选未通过，4-企业筛选通过，5-内推圈建立筛选未通过，6-面试未通过，7-面试成功',
  `is_valid` int(1) NOT NULL DEFAULT '1' COMMENT '是否有效,1-有效，2-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_seeker_resume_delivery`
--

/*!40000 ALTER TABLE `job_seeker_resume_delivery` DISABLE KEYS */;
INSERT INTO `job_seeker_resume_delivery` VALUES (1,4,'f12345','/home/ys/文档/简历//2017年03月19日杨爽123_f12345.doc','2017-03-19 10:36:26.423','2017-04-01 08:49:10.296',5,1);
/*!40000 ALTER TABLE `job_seeker_resume_delivery` ENABLE KEYS */;

--
-- Table structure for table `sys_authority`
--

DROP TABLE IF EXISTS `sys_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_authority` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `authority_name` varchar(20) NOT NULL COMMENT '权限名称',
  `parent_authority_id` bigint(20) NOT NULL COMMENT '父权限ID',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，1-有效，2-无效',
  `authority_description` varchar(200) DEFAULT NULL COMMENT '权限说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_authority`
--

/*!40000 ALTER TABLE `sys_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_authority` ENABLE KEYS */;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `parent_role_id` bigint(20) NOT NULL COMMENT '父角色ID',
  `role_description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `is_vaild` tinyint(1) DEFAULT '1' COMMENT '是否有效，1-有效，2-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

--
-- Table structure for table `sys_role_authority`
--

DROP TABLE IF EXISTS `sys_role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_authority` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sys_role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `sys_authority_id` bigint(20) unsigned NOT NULL COMMENT '系统权限ID',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `is_valid` tinyint(1) DEFAULT '1' COMMENT '是否有效，1-有效，2-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_authority`
--

/*!40000 ALTER TABLE `sys_role_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_authority` ENABLE KEYS */;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户姓名',
  `user_password` varchar(100) NOT NULL COMMENT '用户密码',
  `user_telephone` char(11) NOT NULL COMMENT '用户手机号码',
  `user_email` varchar(100) NOT NULL COMMENT '用户邮箱地址',
  `user_sex` bigint(1) DEFAULT NULL COMMENT '用户性别',
  `user_type` tinyint(1) NOT NULL DEFAULT '2' COMMENT '1-求职者,2-内推圈，3-企业用户',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，1-有效，2-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_user_id` bigint(20) unsigned NOT NULL COMMENT '系统用户ID',
  `sys_role_id` bigint(20) unsigned NOT NULL COMMENT '系统角色ID',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，1-有效，2-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-02 20:36:41
