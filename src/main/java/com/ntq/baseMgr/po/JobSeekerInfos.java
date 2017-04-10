package com.ntq.baseMgr.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
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

    private Integer isValid;

}