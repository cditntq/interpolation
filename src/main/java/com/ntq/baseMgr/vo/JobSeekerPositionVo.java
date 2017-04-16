package com.ntq.baseMgr.vo;

import lombok.Data;

import java.util.Date;

/**
 * <p>@description: 投递者当前职位的状态</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.po
 * @className:
 * @author: shuangyang
 * @date: 17-4-16 下午7:06
 */
@Data
public class JobSeekerPositionVo {


    /*职位主键ID*/
    private Long positionId;
    /*公司id*/
    private Long companyInfosId;
    /*公司名称*/
    private String companyName;
    /*职位编号*/
    private Long positionNo;
    /*职位名称*/
    private String positionName;
    /*处理状态*/
    private Integer dealStatus;
    /*创建时间*/
    private Date serverCreateDate;

}
