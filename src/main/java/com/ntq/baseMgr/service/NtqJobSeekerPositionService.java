package com.ntq.baseMgr.service;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.vo.JobSeekerPositionDealVo;
import com.ntq.baseMgr.vo.JobSeekerPositionDetailVo;

/**
 * <p>@description: 内推圈处理求职者职位的service接口定义</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service
 * @className:
 * @author: shuangyang
 * @date: 17-4-17 下午9:34
 */
public interface NtqJobSeekerPositionService {
    /**
     * 分页查询求职者应聘详信息List
     *
     * @param page
     * @return
     */
    Page<JobSeekerPositionDetailVo> queryJobSeekerPositionDetailVoByCondition(Page<JobSeekerPositionDetailVo> page) throws Exception;

    /**
     * 通过职位id获取职位信息 todo 这里可能需要连带做预览功能 暂时先不动
     *
     * @param positionId
     * @return
     */
    ResponseResult<CompanyPositionInfos> getCompanyPositionInfoById(Long positionId) throws Exception;

    /**
     * 更新简历状态
     *
     * @param jobSeekerPositionDealVo 求职者职位处理状态
     * @return
     */
    ResponseResult<Void> updateResumeDeliveryDealStatus(JobSeekerPositionDealVo jobSeekerPositionDealVo) throws Exception;
}
