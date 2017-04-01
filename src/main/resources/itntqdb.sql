CREATE TABLE job_seeker_infos
(
    id BIGINT(20) unsigned PRIMARY KEY NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
    job_seeker_name VARCHAR(20) NOT NULL COMMENT '求职者姓名',
    job_seeker_sex BIGINT(1) NOT NULL COMMENT '1-男，2-女',
    job_seeker_phone BIGINT(11) NOT NULL COMMENT '求职者电话',
    job_seeker_email VARCHAR(50) NOT NULL COMMENT '求职者邮箱',
    job_seeker_weixin VARCHAR(30) NOT NULL COMMENT '求职者微信ID',
    graduate_school VARCHAR(100) NOT NULL COMMENT '毕业学校',
    major_subjects VARCHAR(100) NOT NULL COMMENT '所学专业',
    graduate_date DATE NOT NULL COMMENT '毕业日期',
    server_create_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '服务端创建时间',
    server_update_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
    is_valid INT(1) DEFAULT '1' NOT NULL COMMENT '是否有效，1-有效，2-无效，3-黑名单'
);
CREATE TABLE job_seeker_resume_delivery
(
    id BIGINT(20) unsigned PRIMARY KEY NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
    job_seeker_infos_id BIGINT(20) NOT NULL COMMENT '求职者信息表主键ID',
    job_code VARCHAR(20) NOT NULL COMMENT '投递职位编码',
    resume_path VARCHAR(200) NOT NULL COMMENT '简历存放位置',
    server_create_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '服务端创建时间',
    server_update_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
    deal_status INT(2) DEFAULT '1' NOT NULL COMMENT '处理状态，1-待处理，2-已投递至企业，3-企业筛选未通过，4-企业筛选通过，5-内推圈建立筛选未通过，6-面试未通过，7-面试成功',
    is_valid INT(1) DEFAULT '1' NOT NULL COMMENT '是否有效,1-有效，2-无效'
);
CREATE TABLE sys_authority
(
    id BIGINT(20) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    server_create_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '服务端创建时间',
    server_update_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
    authority_name VARCHAR(20) NOT NULL COMMENT '权限名称',
    parent_authority_id BIGINT(20) NOT NULL COMMENT '父权限ID',
    is_valid TINYINT(1) DEFAULT '1' NOT NULL COMMENT '是否有效，1-有效，2-无效',
    authority_description VARCHAR(200) COMMENT '权限说明'
);
CREATE TABLE sys_role
(
    id BIGINT(20) unsigned PRIMARY KEY NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
    role_name VARCHAR(20) NOT NULL COMMENT '角色名称',
    parent_role_id BIGINT(20) NOT NULL COMMENT '父角色ID',
    role_description VARCHAR(200) COMMENT '角色描述',
    server_create_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '服务端创建时间',
    server_update_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
    is_vaild TINYINT(1) DEFAULT '1' COMMENT '是否有效，1-有效，2-无效'
);
CREATE TABLE sys_role_authority
(
    id BIGINT(20) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    sys_role_id BIGINT(20) unsigned NOT NULL COMMENT '角色ID',
    sys_authority_id BIGINT(20) unsigned NOT NULL COMMENT '系统权限ID',
    server_create_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '服务端创建时间',
    server_update_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
    is_valid TINYINT(1) DEFAULT '1' COMMENT '是否有效，1-有效，2-无效'
);
CREATE TABLE sys_user
(
    id BIGINT(20) unsigned PRIMARY KEY NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL COMMENT '用户姓名',
    user_password VARCHAR(100) NOT NULL COMMENT '用户密码',
    user_telephone CHAR(11) NOT NULL COMMENT '用户手机号码',
    user_email VARCHAR(100) NOT NULL COMMENT '用户邮箱地址',
    user_sex BIGINT(1) COMMENT '用户性别',
    user_type TINYINT(1) DEFAULT '2' NOT NULL COMMENT '1-求职者,2-内推圈，3-企业用户',
    server_create_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '服务端创建时间',
    server_update_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
    is_valid TINYINT(1) DEFAULT '1' NOT NULL COMMENT '是否有效，1-有效，2-无效'
);
CREATE TABLE sys_user_role
(
    id BIGINT(20) PRIMARY KEY NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
    sys_user_id BIGINT(20) unsigned NOT NULL COMMENT '系统用户ID',
    sys_role_id BIGINT(20) unsigned NOT NULL COMMENT '系统角色ID',
    server_create_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL COMMENT '服务端创建时间',
    server_update_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '服务端最后修改时间',
    is_valid TINYINT(1) DEFAULT '1' NOT NULL COMMENT '是否有效，1-有效，2-无效'
);
