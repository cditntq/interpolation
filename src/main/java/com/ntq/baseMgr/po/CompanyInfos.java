package com.ntq.baseMgr.po;

import lombok.Data;

import java.util.Date;
/**
 * <p>@description:公司信息表 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.po
 * @className:
 * @author: shuangyang
 * @date: 17-3-30 下午10:17
 */

@Data
public class CompanyInfos {
    private Long id;

    private String companyName;

    private Long companyPhone;

    private String resumeMail;

    private String contactWeixinId;

    /*private Boolean recruitType;

    private Boolean companyType;*/

    private Integer recruitType;

    private Integer companyType;

    private Date serverCreateDate;

    private Date serverUpdateDate;

    private Integer isValid;

    private String recruiterName;

    private String companySynopsis;

/*    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Long getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(Long companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getResumeMail() {
        return resumeMail;
    }

    public void setResumeMail(String resumeMail) {
        this.resumeMail = resumeMail == null ? null : resumeMail.trim();
    }

    public String getContactWeixinId() {
        return contactWeixinId;
    }

    public void setContactWeixinId(String contactWeixinId) {
        this.contactWeixinId = contactWeixinId == null ? null : contactWeixinId.trim();
    }

    public Boolean getRecruitType() {
        return recruitType;
    }

    public void setRecruitType(Boolean recruitType) {
        this.recruitType = recruitType;
    }

    public Boolean getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Boolean companyType) {
        this.companyType = companyType;
    }

    public Date getServerCreateDate() {
        return serverCreateDate;
    }

    public void setServerCreateDate(Date serverCreateDate) {
        this.serverCreateDate = serverCreateDate;
    }

    public Date getServerUpdateDate() {
        return serverUpdateDate;
    }

    public void setServerUpdateDate(Date serverUpdateDate) {
        this.serverUpdateDate = serverUpdateDate;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName == null ? null : recruiterName.trim();
    }

    public String getCompanySynopsis() {
        return companySynopsis;
    }

    public void setCompanySynopsis(String companySynopsis) {
        this.companySynopsis = companySynopsis == null ? null : companySynopsis.trim();
    }*/
}