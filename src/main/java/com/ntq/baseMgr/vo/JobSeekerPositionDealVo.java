package com.ntq.baseMgr.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>@description: 更新求职者的职位变化情况的Vo</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.vo
 * @className:
 * @author: shuangyang
 * @date: 17-4-18 下午9:27
 */
@Getter
@Setter
public class JobSeekerPositionDealVo {
    /*附件Id*/
    private Long resumeId;
    /*简历处理状态*/
    private Integer dealStatus;
    /*求职者编号*/
    private Long jobSeekerId;
    /*反馈信息*/
    private String feedBackMessage;


}
