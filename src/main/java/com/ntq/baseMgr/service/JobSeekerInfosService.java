package com.ntq.baseMgr.service;


import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.po.JobSeekerInfosExtDto;
import com.ntq.baseMgr.po.JobSeekerInfosVo;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.vo.JobSeekerPositionVo;
import com.ntq.baseMgr.vo.UploadFileVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>@description: 求职者信息的service</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service
 * @className:
 * @author: shuangyang
 * @date: 17-3-19 下午2:35
 */
public interface JobSeekerInfosService {

    /**
     * 录入求职者个人信息以及简历
     * @param jobSeekerInfosVo
     * @param vo
     * @param request
     */
    ResponseResult<Void> insertJobSeekerInfo(JobSeekerInfosVo jobSeekerInfosVo, UploadFileVo vo, HttpServletRequest request) throws Exception;

    /**
     * 分页查询求职者信息
     * @param page 页码
     * @return
     */
    Page<JobSeekerInfosExtDto> queryJobSeekerInfosListByCondition( Page<JobSeekerInfosExtDto> page) throws Exception;


    /**
     * 批量删除求职者个人信息以及附件信息
     * @param ids
     * @return
     */

    ResponseResult<Void> deleteBatchJobSeekerInfoList(String ids) throws Exception;

    /**
     * 通过求职者信息id查看投递简历相信信息
     * @param id
     * @return
     */
    ResponseResult<JobSeekerInfosVo> getJobSeekerInfoVoById(Long id);
    /**
     * 更新简历状态
     * @param resumeDeliveryId 传递简历的id
     * @param dealStatus 处理状态
     * @return
     */
    ResponseResult<Void> updateResumeDeliveryDealStatus(long resumeDeliveryId, int dealStatus) throws Exception;

    /**
     * 简历相关意见反馈
     * @param jobSeekerEmail 求职者邮箱
     * @param feedBackMessage 反馈信息
     * @return
     */
    ResponseResult<String> resumeFeedBack(String jobSeekerEmail, String feedBackMessage) throws Exception;

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

    /**
     * 求职者投递岗位分页查询
     * @param page 分页参数
     * @return
     */
    Page<JobSeekerPositionVo> queryJobSeekerPositionVoList(Page<JobSeekerPositionVo> page)throws Exception;
    /**
     * 通过id编号获取公司信息
     *
     * @param id 公司自增id
     * @return
     */
    ResponseResult<CompanyInfos> getCompanyInfoById(Long companyInfoId) throws Exception;
    /**
     * 通过id编号获取职位相关信息
     *
     * @param id 职位ID编号
     * @return
     */
    ResponseResult<CompanyPositionInfos> getCompanyPositionInfoById(Long positionId)throws Exception;




}
