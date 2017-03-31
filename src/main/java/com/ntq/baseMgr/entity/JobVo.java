package com.ntq.baseMgr.entity;

import lombok.Data;

/**
 * <p>@description: </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.entity
 * @className:
 * @author: shuangyang
 * @date: 17-3-30 下午8:50
 */
@Data
public class JobVo {

    //职位编码
    private String jobCode;
    //存储路径
    private String resumePath;
    //处理状态
    private Integer dealStatus;
    //是否有效
    private Boolean isValid;
    //附件id
    private Long resumeDeliveryId;


}
