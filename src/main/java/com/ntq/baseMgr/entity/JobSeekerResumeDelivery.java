package com.ntq.baseMgr.entity;

import java.util.Date;

public class JobSeekerResumeDelivery {
    private Long id;

    private Long jobSeekerInfosId;

    private String jobCode;

    private String resumePath;

    private Date serverCreateDate;

    private Date serverUpdateDate;

    private Integer dealStatus;

    private Boolean isValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobSeekerInfosId() {
        return jobSeekerInfosId;
    }

    public void setJobSeekerInfosId(Long jobSeekerInfosId) {
        this.jobSeekerInfosId = jobSeekerInfosId;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode == null ? null : jobCode.trim();
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath == null ? null : resumePath.trim();
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

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }
}