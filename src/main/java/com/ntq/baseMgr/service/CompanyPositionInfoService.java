package com.ntq.baseMgr.service;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.po.CompanyPositionInfosWithBLOBs;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.vo.CompanyPositionInfoVo;
import com.ntq.baseMgr.vo.JobSeekerInfoVo;

/**
 * <p>@description: 职位信息的接口定义</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service
 * @className:
 * @author: shuangyang
 * @date: 17-4-7 下午1:13
 */
public interface CompanyPositionInfoService {
    /**
     * 添加新的职位信息
     *
     * @param companyPositionInfosWithBLOBs
     * @return
     */
    ResponseResult<Void> addCompanyPositionInfo(CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs) throws Exception;

    /**
     * 通过id查看职位信息
     *
     * @param id
     * @return
     */
    ResponseResult<CompanyPositionInfosWithBLOBs> getCompanyPositionInfoById(Long id);

    /**
     * 分页查询求职者信息
     *
     * @param page 分页对象
     * @return
     */
    Page<CompanyPositionInfoVo> queryCompanyPositionInfoListByCondition(Page<CompanyPositionInfoVo> page) throws Exception;

    /**
     * 重新发布职位 1:当为审核状态时，更新当前信息,当不为审核状态新增加
     *
     * @param companyPositionInfosWithBLOBs
     * @return
     */
    ResponseResult<Void> updateOrInsertCompanyPositionInfo(CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs) throws Exception;

    /**
     * 测试
     * @return
     */
    CompanyPositionInfos getTest();

    /**
     * 分页查询获取求职者list
     * @param page
     * @return
     */
    Page<JobSeekerInfoVo> queryJobSeekerInfoVoList(Page<JobSeekerInfoVo> page) throws Exception;
}
