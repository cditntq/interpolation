package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.mapper.CompanyInfosMapper;
import com.ntq.baseMgr.mapper.CompanyPositionInfosMapper;
import com.ntq.baseMgr.mapper.JobSeekerInfosMapper;
import com.ntq.baseMgr.mapper.JobSeekerResumeDeliveryMapper;
import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.po.JobSeekerInfos;
import com.ntq.baseMgr.po.MailBean;
import com.ntq.baseMgr.service.NtqJobSeekerPositionService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.vo.JobSeekerPositionDealVo;
import com.ntq.baseMgr.vo.JobSeekerPositionDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>@description: 内推圈处理求职者职位的service实现</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service.impl
 * @className:
 * @author: shuangyang
 * @date: 17-4-17 下午9:35
 */
@Service
public class NtqJobSeekerPositionServiceImpl implements NtqJobSeekerPositionService {

    @Autowired
    private JobSeekerResumeDeliveryMapper jobSeekerResumeDeliveryMapper;
    @Autowired
    private JobSeekerInfosMapper jobSeekerInfosMapper;

    @Autowired
    private MailSenderServiceImpl mailSenderService;

    @Autowired
    private CompanyPositionInfosMapper companyPositionInfosMapper;
    @Autowired
    private CompanyInfosMapper companyInfosMapper;

    /**
     * 分页查询求职者应聘详信息List
     *
     * @param page
     * @return
     */
    @Override
    public Page<JobSeekerPositionDetailVo> queryJobSeekerPositionDetailVoByCondition(Page<JobSeekerPositionDetailVo> page) throws Exception{
        List<JobSeekerPositionDetailVo> jobSeekerPositionDetailVoList=jobSeekerInfosMapper.queryJobSeekerPositionDetailVoByCondition(page);
       page.setResults(jobSeekerPositionDetailVoList);
       page.setSuccess(true);
        return page;
    }

    /**
     * 通过职位id获取职位信息 todo
     *
     * @param positionId
     * @return
     */
    @Override
    public ResponseResult<CompanyPositionInfos> getCompanyPositionInfoById(Long positionId)throws Exception {
        ResponseResult<CompanyPositionInfos> responseResult=new ResponseResult<>();
        responseResult.setData(companyPositionInfosMapper.getCompanyPositionInfoById(positionId));
        responseResult.setCode(StatusCode.GET_SUCCESS.getCode());
        responseResult.setMessage("查询职位信息成功");
        return responseResult;
    }

    /**
     * 更新简历状态
     *
     * @param jobSeekerPositionDealVo 求职者职位处理状态
     * @return
     */
    @Override
    public ResponseResult<Void> updateResumeDeliveryDealStatus(JobSeekerPositionDealVo jobSeekerPositionDealVo) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();

        //简历id
        Long resumeId = jobSeekerPositionDealVo.getResumeId();
        //反馈信息
        String feedBackMessage = jobSeekerPositionDealVo.getFeedBackMessage();
        //简历状态
        Integer dealStatus = jobSeekerPositionDealVo.getDealStatus();
        //求职者编号
        Long jobSeekerId = jobSeekerPositionDealVo.getJobSeekerId();
        //1. 通过简历id更新简历状态
        jobSeekerResumeDeliveryMapper.updateResumeDeliveryDealStatus(resumeId, dealStatus);
        //2.发送邮件反馈信息
        //2.1 通过求职者编号获取求职者信息
        JobSeekerInfos jobSeekerInfo = jobSeekerInfosMapper.getJobSeekerInfoById(jobSeekerId);
        //获取邮件

        String jobSeekerEmail = jobSeekerInfo.getJobSeekerEmail();
        //发送邮件
        MailBean mailBean = new MailBean();
        mailBean.setToEmails(new String[]{jobSeekerEmail});
        mailBean.setSubject("简历状态更新");
        mailBean.setContext(feedBackMessage);
        mailSenderService.sendMail(mailBean);
        responseResult.setCode(StatusCode.OK.getCode());
        responseResult.setMessage("简历状态更新成功");
        return responseResult;
    }
    /**
     * 根据公司编号获取公司的基本信息
     * @param companyInfoId
     * @return
     */
    @Override
    public ResponseResult<CompanyInfos> getCompanyInfoById(Long companyInfoId) throws Exception{
        ResponseResult<CompanyInfos> responseResult = new ResponseResult<>();
        responseResult.setData(companyInfosMapper.getCompanyInfoById(companyInfoId));
        responseResult.setCode(StatusCode.GET_SUCCESS.getCode());
        responseResult.setMessage("根据公司编号查询公司信息成功");
        return responseResult;
    }
}
