package com.ntq.baseMgr.service;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.po.CompanyPositionInfosWithBLOBs;
import com.ntq.baseMgr.util.ResponseResult;

import javax.servlet.http.HttpSession;

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
    Page<CompanyPositionInfos> queryCompanyPositionInfoListByCondition(Page<CompanyPositionInfos> page);

    /**
     * 重新发布职位 1:当为审核状态时，更新当前信息,当不为审核状态新增加
     *
     * @param companyPositionInfosWithBLOBs
     * @return
     */
    ResponseResult<Void> updateOrInsertCompanyPositionInfo(CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs) throws Exception;

   /* *//**
     * 转跳验证
     *
     * @param session
     * @param phoneNumber
     * @param verifyCode
     * @return
     *//*
    ResponseResult<Void> verifyMessageCode(HttpSession session, Long phoneNumber, String verifyCode) throws Exception;*/
}
