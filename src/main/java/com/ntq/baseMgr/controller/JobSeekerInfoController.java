package com.ntq.baseMgr.controller;


import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.JobSeekerInfosExtDto;
import com.ntq.baseMgr.po.JobSeekerInfosVo;
import com.ntq.baseMgr.service.JobSeekerInfosService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.vo.UploadFileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    /**
     * 求职者页面管理,暂时不清楚为什么被请求了两次
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/jobSeekerManagment")
    public String index(HttpSession httpSession) throws Exception {
        return "jobSeekerManagment";
    }


    /**
     * 求职者信息录入以及简历上传
     *
     * @param request
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addJobSeekerInfoAndResume", method = RequestMethod.POST)
    public String addJobSeekerInfoAndResume(JobSeekerInfosVo jobSeekerInfosVo, UploadFileVo vo, HttpServletRequest request) throws Exception {
        jobSeekerInfosService.insertJobSeekerInfo(jobSeekerInfosVo, vo, request);
        return "sentResum";
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
     * 分页查询求职者信息
     *
     * @param page 分页对象
     * @return
     */
    @RequestMapping(value = "/queryJobSeekerInfoListByCondition")
    @ResponseBody
    public Page<JobSeekerInfosExtDto> queryJobSeekerInfosListByCondition(Page<JobSeekerInfosExtDto> page) {
        try {
            return jobSeekerInfosService.queryJobSeekerInfosListByCondition(page);
        } catch (Exception e) {
          page.setSuccess(false);
        }
        return page;
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
        ResponseResult<Void> result=new ResponseResult<>();
        try {
            return jobSeekerInfosService.deleteBatchJobSeekerInfoList(ids);
        } catch (Exception e) {
            result.setCode(StatusCode.DELETE_FAIL.getCode());
            result.setMessage(StatusCode.DELETE_FAIL.getMessage());

        }
        return result;
    }

    /**
     * 更新简历状态
     *
     * @param resumeDeliveryId 传递简历的id
     * @param dealStatus       处理状态
     * @return
     */
    @RequestMapping(value = "/updateResumeDeliveryDealStatus")
    @ResponseBody
    public ResponseResult<Void> updateResumeDeliveryDealStatus(long resumeDeliveryId, int dealStatus) {
        ResponseResult<Void> responseResult=new ResponseResult<>();
        try {
            responseResult= jobSeekerInfosService.updateResumeDeliveryDealStatus(resumeDeliveryId, dealStatus);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.UPDATE_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.UPDATE_FAIL.getMessage());

        }
        return responseResult;
    }

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

}
