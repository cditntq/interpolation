package com.ntq.baseMgr.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>@description:求职者和职位的详细信息Vo </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.vo
 * @className:
 * @author: shuangyang
 * @date: 17-4-17 下午9:49
 */
@Setter
@Getter
public class JobSeekerPositionDetailVo {

    /*职位编号*/
    private Long positionNo;
    /*职位id*/
    private Long positionId;
    /*职位名称*/
    private String positionName;
    /*求职者编号*/
    private Long jobSeekerId;
    //求职者电话
    private Long jobSeekerPhone;
    //微信
    private String jobSeekerWeixin;
    //求职者学历
    private Integer recordOfFormalSchooling;

    // 更新简历状态需要附件id
    private Long resumeId;
    //resume
    //简历附件名称
    private String resumeName;
    //简历状态
    private Integer dealStatus;
    /*企业名称*/
    private String companyName;

    //更新简历状态需要附件id
    //

}
