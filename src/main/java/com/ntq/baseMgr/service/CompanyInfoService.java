package com.ntq.baseMgr.service;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.vo.CompanyInfoWithPositionInfoListVo;

import javax.servlet.http.HttpSession;

/**
 * <p>@description:公司信息Service接口 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service
 * @className:
 * @author: shuangyang
 * @date: 17-4-3 下午5:40
 */
public interface CompanyInfoService {

    /**
     * 新增公司信息录入
     *
     * @param companyInfos
     * @return
     */
    ResponseResult<Void> addCompanyInfos(CompanyInfos companyInfos) throws Exception;

    /**
     * 通过id编号获取公司信息
     *
     * @param id
     * @return
     */
    ResponseResult<CompanyInfos> getJobSeekerInfoVoById(Long id) throws Exception;

    /**
     * 分页查询求职者信息
     *
     * @param page 分页对象
     * @return
     */
    Page<CompanyInfos> queryCompanyInfoListByCondition(Page<CompanyInfos> page) throws Exception;

    /**
     * 根据id批量删除求职者个人信息包括简历
     *
     * @param ids
     * @return
     * @throws Exception
     */
    ResponseResult<Void> deleteCompanyInfoListByIds(String ids) throws Exception;

    /**
     * 公司信息更新
     *
     * @param companyInfos 公司实体
     * @return
     */
    ResponseResult<Void> updateCompanyInfos(CompanyInfos companyInfos) throws Exception;

    /**
     * 新的公司和其发布的职位录入
     *
     * @param companyInfoWithPositionInfoListVo 公司信息
     * @return
     */
    ResponseResult<Void> addCompanyInfoWithPositionInfoList(CompanyInfoWithPositionInfoListVo companyInfoWithPositionInfoListVo) throws Exception;

    /**
     * 获取验证码
     *
     * @param phoneNumber
     * @return
     */
    ResponseResult<Void> getMessageCode(Long phoneNumber) throws Exception;

    /**
     * 转跳验证
     *
     * @param session
     * @param phoneNumber 手机号
     * @param verifyCode  验证码
     * @return
     */
    ResponseResult<Void> verifyMessageCode(HttpSession session, Long phoneNumber, String verifyCode) throws Exception;

    CompanyInfos getTest();
}

