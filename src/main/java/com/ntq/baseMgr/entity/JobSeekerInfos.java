package com.ntq.baseMgr.entity;

import java.util.Date;

public class JobSeekerInfos {
    private Long id;

    private String jobSeekerName;

    private Long jobSeekerSex;

    private Long jobSeekerPhone;

    private String jobSeekerEmail;

    private String jobSeekerWeixin;

    private String graduateSchool;

    private String majorSubjects;

    private Date graduateDate;

    private Date serverCreateDate;

    private Date serverUpdateDate;

    private Boolean isValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobSeekerName() {
        return jobSeekerName;
    }

    public void setJobSeekerName(String jobSeekerName) {
        this.jobSeekerName = jobSeekerName == null ? null : jobSeekerName.trim();
    }

    public Long getJobSeekerSex() {
        return jobSeekerSex;
    }

    public void setJobSeekerSex(Long jobSeekerSex) {
        this.jobSeekerSex = jobSeekerSex;
    }

    public Long getJobSeekerPhone() {
        return jobSeekerPhone;
    }

    public void setJobSeekerPhone(Long jobSeekerPhone) {
        this.jobSeekerPhone = jobSeekerPhone;
    }

    public String getJobSeekerEmail() {
        return jobSeekerEmail;
    }

    public void setJobSeekerEmail(String jobSeekerEmail) {
        this.jobSeekerEmail = jobSeekerEmail == null ? null : jobSeekerEmail.trim();
    }

    public String getJobSeekerWeixin() {
        return jobSeekerWeixin;
    }

    public void setJobSeekerWeixin(String jobSeekerWeixin) {
        this.jobSeekerWeixin = jobSeekerWeixin == null ? null : jobSeekerWeixin.trim();
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool == null ? null : graduateSchool.trim();
    }

    public String getMajorSubjects() {
        return majorSubjects;
    }

    public void setMajorSubjects(String majorSubjects) {
        this.majorSubjects = majorSubjects == null ? null : majorSubjects.trim();
    }

    public Date getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
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
}