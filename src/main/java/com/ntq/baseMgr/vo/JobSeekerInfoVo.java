package com.ntq.baseMgr.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>@description:对应投递职位的求职者 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.vo
 * @className:
 * @author: shuangyang
 * @date: 17-4-13 下午3:50
 */

@Setter
@Getter
public class JobSeekerInfoVo {
    //姓名
    private String jobSeekerName;

    //电话
    private Long jobSeekerPhone;
    //学历
    private Integer recordOfFormalSchooling;
    //还差一个工作年限


}
