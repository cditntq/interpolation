package com.ntq.baseMgr.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CompanyInfos {
    //    主键
    private Long id;
    //公司名称
    private String companyName;
    //公司电话
    private Long companyPhone;
    //简历投递邮箱
    private String resumeMail;
    //微信id
    private String contactWeixinId;
    //
    private Integer recruitType;

    private Integer companyType;

    private Date serverCreateDate;

    private Date serverUpdateDate;

    private Integer isNewlyEstablished;

    private Integer financingType;

    private Integer isValid;

    private String recruiterName;

    private String companySynopsis;

}