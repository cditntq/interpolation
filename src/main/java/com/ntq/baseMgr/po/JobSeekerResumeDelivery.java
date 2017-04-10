package com.ntq.baseMgr.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class JobSeekerResumeDelivery {
    private Long id;

    private Long jobSeekerInfosId;

    private String jobCode;

    private String resumePath;

    private Date serverCreateDate;

    private Date serverUpdateDate;

    private Integer dealStatus;

    private Integer isValid;

    private Integer isFeedback;

    private String resumeName;


}