package com.ntq.baseMgr.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>@description:公司简介 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.po
 * @className:
 * @author: shuangyang
 * @date: 17-4-2 下午12:28
 */
@Getter
@Setter
public class CompanyInfos {
    /*自增长ID*/
    private Long id;
    /*公司名称*/
    private String companyName;
   /*公司联系电话*/
    private Long companyPhone;
   /*简历投递邮箱*/
    private String resumeMail;
    /*联系人威信*/
    private String contactWeixinId;
   /*招聘类型*/
    private Integer recruitType;
  /*企业类型*/
    private Integer companyType;
    /*创建时间*/
    private Date serverCreateDate;
    /*修改时间*/
    private Date serverUpdateDate;
    /*是否为创业公司*/
    private Integer isNewlyEstablished;
    /*融资状况*/
    private Integer financingType;
    /*是否有效*/
    private Integer isValid;
    /*招聘人姓名*/
    private String recruiterName;
   /*公司简介*/
    private String companySynopsis;

    @Override
    public String toString() {
        return "CompanyInfos{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyPhone=" + companyPhone +
                ", resumeMail='" + resumeMail + '\'' +
                ", contactWeixinId='" + contactWeixinId + '\'' +
                ", recruitType=" + recruitType +
                ", companyType=" + companyType +
                ", serverCreateDate=" + serverCreateDate +
                ", serverUpdateDate=" + serverUpdateDate +
                ", isNewlyEstablished=" + isNewlyEstablished +
                ", financingType=" + financingType +
                ", isValid=" + isValid +
                ", recruiterName='" + recruiterName + '\'' +
                ", companySynopsis='" + companySynopsis + '\'' +
                '}';
    }

}