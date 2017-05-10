package com.ntq.baseMgr.service;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.vo.CompanyPositionInfoExtVo;

/**
 * <p>@description: 内推圈Service接口定义</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service
 * @className:
 * @author: shuangyang
 * @date: 17-4-13 下午5:32
 */
public interface NtqCompanyPositionDealService {
    /**
     * 分页查询公司职位信息
     *
     * @param page 分页对象
     * @return
     */
    Page<CompanyPositionInfoExtVo> queryCompanyPositionInfoVoListByCondition(Page<CompanyPositionInfoExtVo> page) throws Exception;
    /**
     * 通过id编号获取公司信息
     *
     * @param id 公司自增id
     * @return
     */
    ResponseResult<CompanyInfos> getCompanyInfoById(Long id) throws Exception;
    /**
     * 通过id编号获取职位相关信息
     *
     * @param id 职位ID编号
     * @return
     */
    ResponseResult<CompanyPositionInfos> getCompanyPositionInfoById(Long id) throws Exception;
    /**
     * 通过职位id发送给公司职位拒绝发送，包括理由
     *
     * @param positionId 职位ID编号
     * @param message    拒绝理由
     * @return
     */
    ResponseResult<Void> updatePositionRemarkAndSendMail(Long positionId, String message) throws Exception;
    /**
     * 通过id编号获取公司信息
     *
     * @param positionId 公司自增id
     * @return
     */
    ResponseResult<Void> updateCompanyPositionInfo(Long positionId);
}
