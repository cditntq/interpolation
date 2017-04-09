package com.ntq.baseMgr.po;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class CompanyPositionInfos {
    /*主键ID*/
    private Long id;
     /*公司id*/
    private Long companyInfosId;
    /*职位编号*/
    private Long positionNo;
    /*职位信息*/
    private String positionName;
    /*最高薪资*/
    private BigDecimal highSalary;
    /*最低薪资*/
    private BigDecimal lowSalary;
    /*职位类型*/
    private Integer positionType;
   /*学历条件*/
    private Integer qualificationsType;
   /*最低工作年限*/
    private Integer lowWorkingLife;
    /*最高工作年限*/
    private Integer highWorkingLife;
    /*工作地点*/
    private String workAddress;
    /*是否有效*/
    private Integer isValid;
   /*创建时间*/
    private Date serverCreateDate;
    /*修改时间*/
    private Date serverUpdateDate;
    /*有效期*/
    private Date deadline;
    /*是否面议*/
    private Integer isDiscussPersonally;
    /*职位状态*/
    private Long postionStatus;
    /*发时间*/
    private Date publishTime;

}