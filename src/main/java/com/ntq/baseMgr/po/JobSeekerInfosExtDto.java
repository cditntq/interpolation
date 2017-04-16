package com.ntq.baseMgr.po;

import lombok.Data;

/**
 * <p>@description: </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.po
 * @className:
 * @author: shuangyang
 * @date: 17-3-30 下午10:17
 */
@Data
public class JobSeekerInfosExtDto {
    //主表id
    private Long id;
    //职位编码
    private String jobCode;
    /*求职者名称*/
    private String jobSeekerName;
    //电话
    private Long jobSeekerPhone;
    /*微信*/
    private String jobSeekerWeixin;
    //存储路径
    private String resumePath;

    private Long resumeDeliveryId;
    /*求职者邮箱*/
    private String jobSeekerEmail;
    private Integer dealStatus;



}
