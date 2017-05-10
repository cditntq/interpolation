package com.ntq.baseMgr.controller;


import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.po.JobSeekerInfosVo;
import com.ntq.baseMgr.service.JobSeekerInfosService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.vo.JobSeekerPositionDealVo;
import com.ntq.baseMgr.vo.JobSeekerResumeWithFile;
import com.ntq.baseMgr.vo.UploadFileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>@description:求职者相关信息控制器 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.controller
 * @className:
 * @author: shuangyang
 * @date: 17-3-19 下午2:27
 */

@Controller
@RequestMapping("/jobSeekerInfo")
public class JobSeekerInfoController {
    @Autowired
    private JobSeekerInfosService jobSeekerInfosService;//求职者service

    private final Logger logger = LoggerFactory.getLogger(JobSeekerInfoController.class);

/*    *//**
     * 求职者页面管理,暂时不清楚为什么被请求了两次
     *
     * @return
     * @throws Exception
     *//*
    @RequestMapping(value = "/jobSeekerManagment")
    public String index(HttpSession httpSession) {
        return "jobSeekerManagment";
    }*/

    /**
     * 已经录入简历的求职者点击获取验证码
     *
     * @param phoneNumber 电话号码
     * @return 返回
     */
    @RequestMapping(value = "/getMessageCode", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<Void> getMessageCode(Long phoneNumber) {
        ResponseResult<Void> responseResult = new ResponseResult<>();

        try {
            responseResult = jobSeekerInfosService.getMessageCode(phoneNumber);
        } catch (Exception e) {
            logger.error("the jobSeekerInfo getMessageCode", e);
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("验证码发送失败");
        }
        return responseResult;
    }

    /**
     * 验证码校验是否为已注册的公司
     * 这里需要处理的是当前的用户  但需要注意的在处理验证码失败的情况 返回操作的true 成功 能够转跳,false验证失败暂时没做
     *
     * @param session
     * @param phoneNumber 手机号码
     * @param verifyCode  收到的验证码
     * @return
     */
    @RequestMapping(value = "/verifyMessageCode", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<Void> verifyMessageCode(HttpSession session, Long phoneNumber, String verifyCode) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            return jobSeekerInfosService.verifyMessageCode(session, phoneNumber, verifyCode);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("短信验证失败");

        }
        return responseResult;
    }


    /**
     * 求职者信息录入以及简历上传
     *
     * @param jobSeekerInfosVo 求职者信息录入
     * @param vo               简历上传
     * @param request          request请求
     * @return
     */
    @RequestMapping(value = "/addJobSeekerInfoAndResume", method = RequestMethod.POST)
    public ResponseResult<Void> addJobSeekerInfoAndResume(JobSeekerInfosVo jobSeekerInfosVo, UploadFileVo vo, HttpServletRequest request) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        //测试的数据
        try {
            responseResult = jobSeekerInfosService.insertJobSeekerInfo(jobSeekerInfosVo, vo, request);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
            responseResult.setFailureMessage("求职者初次录入职位失败");
            logger.error("the method addJobSeekerInfoAndResume in the controller of jobSeekerInfo throw Exception:", e);
        }
        return responseResult;
    }


    /**
     * 通过求职者信息id查看投递简历相信信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getJobSeekerInfoVoById")
    @ResponseBody
    public ResponseResult<JobSeekerInfosVo> getJobSeekerInfoVoById(Long id) {
        return jobSeekerInfosService.getJobSeekerInfoVoById(id);
    }


    /**
     * 根据id批量删除求职者个人信息包括简历
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteJobSeekerInfoListByIds")
    @ResponseBody
    public ResponseResult<Void> deleteJobSeekerInfoListByIds(String ids) {
        ResponseResult<Void> result = new ResponseResult<>();
        try {
            return jobSeekerInfosService.deleteBatchJobSeekerInfoList(ids);
        } catch (Exception e) {
            result.setCode(StatusCode.DELETE_FAIL.getCode());
            result.setMessage(StatusCode.DELETE_FAIL.getMessage());

        }
        return result;
    }

    /**
     * 短信验证获取
     * 验证求职者用户是否已存在，1.2如果不存在就发送验证码
     *
     * @param phoneNumber
     * @return
     */
    @RequestMapping(value = "/getMessageAfterValidatePhoneNumber", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<Void> getMessageAfterValidatePhoneNumber(Long phoneNumber) {
        ResponseResult<Void> responseResult = null;
        try {
            responseResult = jobSeekerInfosService.getMessageAfterValidatePhoneNumber(phoneNumber);
        } catch (Exception e) {
            responseResult = new ResponseResult<>();
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setMessage("操作失败！请检查");
            logger.error("the method getMessageAfterValidatePhoneNumber Failed:", e);
        }
        return responseResult;
    }

    /**
     * 注册求职者信息验证
     *
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    @RequestMapping(value = "/verifyJobSeekerPhoneNumber", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<Void> verifyJobSeekerPhoneNumber(Long phoneNumber, String verifyCode) {
        ResponseResult<Void> responseResult = null;
        try {
            responseResult = jobSeekerInfosService.verifyJobSeekerPhoneNumber(phoneNumber, verifyCode);
        } catch (Exception e) {
            responseResult = new ResponseResult<>();
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setMessage("注册的求职者短信验证失败");
            logger.error("the method verifyJobSeekerPhoneNumber failed", e);
        }
        return responseResult;

    }

/*    *//**
     * 更新简历状态
     *
     * @param resumeDeliveryId 传递简历的id
     * @param dealStatus       处理状态
     * @return
     *//*
    @RequestMapping(value = "/updateResumeDeliveryDealStatus")
    @ResponseBody
    public ResponseResult<Void> updateResumeDeliveryDealStatus(long resumeDeliveryId, int dealStatus) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            responseResult = jobSeekerInfosService.updateResumeDeliveryDealStatus(resumeDeliveryId, dealStatus);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.UPDATE_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.UPDATE_FAIL.getMessage());

        }
        return responseResult;
    }*/

    /**
     * 简历相关意见反馈
     *
     * @param jobSeekerEmail  求职者邮箱
     * @param feedBackMessage 反馈信息
     * @return
     */
    @RequestMapping(value = "/resumeFeedBack")
    @ResponseBody
    public ResponseResult<String> resumeFeedBack(String jobSeekerEmail, String feedBackMessage) {
        ResponseResult<String> responseResult = new ResponseResult<>();
        try {
            responseResult = jobSeekerInfosService.resumeFeedBack(jobSeekerEmail, feedBackMessage);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage(StatusCode.Fail.getMessage());
        }
        return responseResult;
    }


    /**
     * 求职者投递岗位分页查询
     *
     * @param page 分页参数
     * @return
     */
    @RequestMapping(value = "/queryJobSeekerPositionVoList")
    @ResponseBody
    public Page<JobSeekerPositionDealVo> queryJobSeekerPositionVoList(@RequestBody Page<JobSeekerPositionDealVo> page) {
        try {
            return jobSeekerInfosService.queryJobSeekerPositionVoList(page);
        } catch (Exception e) {
            page.setSuccess(false);
        }
        return page;
    }

    /**
     * 通过id编号获取公司信息
     *
     * @param companyInfoId 公司自增id
     * @return
     */
    @RequestMapping(value = "/getCompanyInfoById")
    @ResponseBody
    public ResponseResult<CompanyInfos> getCompanyInfoById(Long companyInfoId) {
        ResponseResult<CompanyInfos> responseResult = new ResponseResult<>();
        try {
            responseResult = jobSeekerInfosService.getCompanyInfoById(companyInfoId);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.GET_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.GET_FAIL.getMessage());
        }
        return responseResult;
    }

    /**
     * 通过id编号获取职位相关信息
     *
     * @param positionId 职位ID编号
     * @return
     */
    @RequestMapping(value = "/getCompanyPositionInfoById")
    @ResponseBody
    public ResponseResult<CompanyPositionInfos> getCompanyPositionInfoById(Long positionId) {
        ResponseResult<CompanyPositionInfos> responseResult = new ResponseResult<>();
        try {
            responseResult = jobSeekerInfosService.getCompanyPositionInfoById(positionId);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.GET_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.GET_FAIL.getMessage());
        }
        return responseResult;
    }



    /**
     * 新增投递简历
     *
     * @param jobSeekerResumeWithFile 职位编号和附件( todo 测试)
     * @return
     */
    @RequestMapping(value = "/addJobSeekerResume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> addJobSeekerResume(@RequestBody JobSeekerResumeWithFile jobSeekerResumeWithFile) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            responseResult = jobSeekerInfosService.addJobSeekerResume(jobSeekerResumeWithFile);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
            responseResult.setFailureMessage("插入失败");
            logger.error("the method addJobSeekerResume in the controller of jobSeekerInfo throw Exception:", e);
        }
        return responseResult;
    }


}
