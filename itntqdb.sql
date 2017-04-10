/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50716
 Source Host           : localhost
 Source Database       : itntqdb

 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 04/02/2017 23:20:45 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `company_infos`
-- ----------------------------
DROP TABLE IF EXISTS `company_infos`;
CREATE TABLE `company_infos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `company_name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '企业名称',
  `company_phone` bigint(11) NOT NULL COMMENT '公司联系电话',
  `resume_mail` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '简历投递邮箱',
  `contact_weixin_id` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '联系人微信ID',
  `recruit_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '招聘类型，1-内推，2-HR招聘',
  `company_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '企业类型，1-民营，2-国企，3-外企',
  `company_synopsis` text COLLATE utf8_bin NOT NULL COMMENT '公司简介',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器创建时间',
  `server_update_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效。1-有效，2-无效',
  `recruiter_name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '招聘人姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `company_position_infos`
DROP TABLE IF EXISTS  company_position_infos ;
CREATE TABLE `company_position_infos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `company_infos_id` bigint(20) NOT NULL COMMENT '公司信息ID',
  `position_no` bigint(20) NOT NULL COMMENT '职位编号：年月日+00001 如：2017040100001',
  `position_name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '职位信息',
  `high_salary` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '最高薪水',
  `low_salary` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '最低薪水',
  `position_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '职位类别.1-全职，2-兼职，3-实习',
  `qualifications_type` int(11) NOT NULL DEFAULT '3' COMMENT '学历条件.1-高中，2-大专，3-本科，4-硕士，5-博士，6-其他，7-不限',
  `low_working_life` int(11) DEFAULT NULL COMMENT '该职位最低工作年限',
  `high_working_life` int(11) DEFAULT NULL COMMENT '该职位要求的最高工作年限',
  `work_address` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '工作地址',
  `position_desc` text COLLATE utf8_bin NOT NULL COMMENT '职位描述',
  `position_requirements` text COLLATE utf8_bin NOT NULL COMMENT '职位要求',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效.1-有效,2-无效',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务创建时间',
  `server_update_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务器更新时间',
  `deadline` datetime NOT NULL COMMENT '有效期，职位有效期为内推圈发布职位的时间往后推两周',
  `is_discuss_personally` tinyint(4) NOT NULL DEFAULT '2' COMMENT '是否面议。1-是，2-否',
  `postion_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '职位状态,1-待审核、2-已发布、3-拒绝发布、4-待下架、5-已下架',
  `publish_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='职位信息'

-- ----------------------------
--  Table structure for `job_seeker_infos`
-- ----------------------------
DROP TABLE IF EXISTS `job_seeker_infos`;
CREATE TABLE `job_seeker_infos` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `job_seeker_name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '求职者姓名',
  `job_seeker_sex` bigint(1) NOT NULL COMMENT '1-男，2-女',
  `job_seeker_phone` bigint(11) NOT NULL COMMENT '求职者电话',
  `job_seeker_email` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '求职者邮箱',
  `job_seeker_weixin` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '求职者微信ID',
  `graduate_school` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '毕业学校',
  `major_subjects` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '所学专业',
  `graduate_date` date NOT NULL COMMENT '毕业日期',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，1-有效，2-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `job_seeker_resume_delivery`
-- ----------------------------
DROP TABLE IF EXISTS `job_seeker_resume_delivery`;
CREATE TABLE `job_seeker_resume_delivery` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `job_seeker_infos_id` bigint(20) DEFAULT NULL COMMENT '求职者信息表主键ID',
  `job_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '投递职位编码',
  `resume_path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '简历存放位置',
  `server_create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '服务端创建时间',
  `server_update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '服务端最后修改时间',
  `deal_status` int(2) DEFAULT '1' COMMENT '处理状态，1-待处理，2-内推圈简历筛选未通过，3-已投递至企业，4-企业筛选未通过，5-企业筛选通过，6-面试未通过，7-面试成功',
  `is_valid` tinyint(1) DEFAULT '1' COMMENT '是否有效,1-有效，2-无效',
  `is_feedback` tinyint(1) DEFAULT '1' COMMENT '是否反馈，1-未反馈，2-已反馈',
  `feedback_content` text COLLATE utf8_bin COMMENT '反馈内容',
  `not_pass_reason` text COLLATE utf8_bin COMMENT '未通过原因',
  `resume_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '简历名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `sys_authority`
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `authority_name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '权限名称',
  `parent_authority_id` bigint(20) NOT NULL COMMENT '父权限ID',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，1-有效，2-无效',
  `authority_description` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '权限说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `parent_role_id` bigint(20) NOT NULL COMMENT '父角色ID',
  `role_description` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `is_vaild` tinyint(1) DEFAULT '1' COMMENT '是否有效，1-有效，2-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `sys_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authority`;
CREATE TABLE `sys_role_authority` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sys_role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `sys_authority_id` bigint(20) unsigned NOT NULL COMMENT '系统权限ID',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `is_valid` tinyint(1) DEFAULT '1' COMMENT '是否有效，1-有效，2-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用户姓名',
  `user_password` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `user_telephone` char(11) COLLATE utf8_bin NOT NULL COMMENT '用户手机号码',
  `user_email` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '用户邮箱地址',
  `user_sex` bigint(1) DEFAULT NULL COMMENT '用户性别',
  `user_type` tinyint(1) NOT NULL DEFAULT '2' COMMENT '1-求职者,2-内推圈，3-企业用户',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，1-有效，2-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_user_id` bigint(20) unsigned NOT NULL COMMENT '系统用户ID',
  `sys_role_id` bigint(20) unsigned NOT NULL COMMENT '系统角色ID',
  `server_create_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端创建时间',
  `server_update_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，1-有效，2-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `message_validate_record` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `phone_num` varchar(11) DEFAULT NULL COMMENT '发送短信的手机号码' ,
  `token` varchar(10) DEFAULT NULL COMMENT '验证码',
  `valide_time` timestamp NULL DEFAULT NULL COMMENT '发送时间',
  `send_success` tinyint(2) DEFAULT NULL COMMENT '1:成功,2:失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8