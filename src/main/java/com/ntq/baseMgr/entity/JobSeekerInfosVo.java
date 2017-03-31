package com.ntq.baseMgr.entity;

import lombok.Data;

/**
 * <p>@description:扩展信息用于接收附件的附件信息</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.vo
 * @className:
 * @author: shuangyang
 * @date: 17-3-19 下午4:24
 */
@Data
public class JobSeekerInfosVo extends JobSeekerInfos {


    //职位编码
    private String jobCode;
    //存储路径
    private String resumePath;
    //处理状态
    private Integer dealStatus;
    //是否有效
    private Boolean isValid;
    //附件id
    private Long ResumeDeliveryId;

}
