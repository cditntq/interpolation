package com.ntq.baseMgr.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JobSeekerInfos {
    private Long id;
    //姓名
    private String jobSeekerName;
    //性别
    private Long jobSeekerSex;
    //电话
    private Long jobSeekerPhone;
    //邮箱
    private String jobSeekerEmail;
    //微信
    private String jobSeekerWeixin;
    //毕业院校
    private String graduateSchool;
    //专业
    private String majorSubjects;
    //毕业时间
    private Date graduateDate;
    //学历
    private Integer recordOfFormalSchooling;

    private Date serverCreateDate;

    private Date serverUpdateDate;
    //是否有效
    private Integer isValid;
    //是否已经添加内推圈微信 默认为否
    private Integer isAddNtqweixin;

}