package com.ntq.baseMgr.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>@description:职位的Vo类 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.vo
 * @className:
 * @author: shuangyang
 * @date: 17-4-13 下午1:36
 */
@Getter
@Setter
public class CompanyPositionInfoVo {
    /*主键ID*/
    private Long id;
    /*职位编号*/
    private Long positionNo;
    /*职位名称*/
    private String positionName;
    /*创建时间*/
    private Date serverCreateDate;
    /*职位状态*/
    private Integer postionStatus;
    /*发时间*/
    private Date publishTime;
    /*简历数量*/
    private Integer resumeCount;
}
