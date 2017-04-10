package com.ntq.baseMgr.vo;

import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfosWithBLOBs;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>@description: </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.vo
 * @className:
 * @author: shuangyang
 * @date: 17-4-10 下午7:53
 */
@Getter
@Setter
public class CompanyInfoWithPositionInfoListVo implements Serializable{
    //公司信息
    private CompanyInfos companyInfo;
    //职位的集合
    private List<CompanyPositionInfosWithBLOBs>  companyPositionInfosWithBlobList;

}
