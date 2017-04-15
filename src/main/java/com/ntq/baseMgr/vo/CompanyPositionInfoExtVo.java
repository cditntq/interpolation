package com.ntq.baseMgr.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>@description:ntq查看Vo类 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.vo
 * @className: CompanyPositionInfoExtVo
 * @author: shuangyang
 * @date: 17-4-13 下午5:22
 */
@Setter
@Getter
public class CompanyPositionInfoExtVo {

    /*职位主键ID*/
    private Long id;
    /*公司id*/
    private Long companyInfosId;
    /*公司名称*/
    private String companyName;
    /*职位编号*/
    private Long positionNo;
    /*职位名称*/
    private String positionName;
    /*职位状态*/
    private Integer postionStatus;
    /*创建时间*/
    private Date serverCreateDate;
    /*备注*/
    private String remark;
    /*发时间*/
    private Date publishTime;
}
