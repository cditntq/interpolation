package com.ntq.baseMgr.controller;


import com.ntq.baseMgr.po.JobSeekerInfosExtDto;
import com.ntq.baseMgr.po.JobSeekerInfosVo;
import com.ntq.baseMgr.service.JobSeekerInfosService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.vo.UploadFileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
     * @param page           页码
     * @param size           分页大小
     * @param whereCondition 查询条件
     * @return
     */
    @RequestMapping(value = "/queryJobSeekerInfoListByCondition")
    @ResponseBody
    public ResponseResult<List<JobSeekerInfosExtDto>> queryJobSeekerInfosListByCondition(int page, int size, String whereCondition) {
         return jobSeekerInfosService.queryJobSeekerInfosListByCondition(page, size, whereCondition);
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
    public ResponseResult<String> deleteJobSeekerInfoListByIds(String ids) {
//        return   jobSeekerInfosService.resumeFeedBack("247677858@qq.com","简历可能有问题——测试");
        return jobSeekerInfosService.deleteBatchJobSeekerInfoList(ids);
    }

    /**
     * 更新简历状态
     * @param resumeDeliveryId 传递简历的id
     * @param dealStatus 处理状态
     * @return
     */
    @RequestMapping(value = "/updateResumeDeliveryDealStatus")
    @ResponseBody
    public ResponseResult<String> updateResumeDeliveryDealStatus(long resumeDeliveryId, int dealStatus){
        return  jobSeekerInfosService.updateResumeDeliveryDealStatus(resumeDeliveryId,dealStatus);
    }

    /**
     * 简历相关意见反馈
     * @param jobSeekerEmail 求职者邮箱
     * @param feedBackMessage 反馈信息
     * @return
     */
    public ResponseResult<String> resumeFeedBack(String jobSeekerEmail,String feedBackMessage){
        return jobSeekerInfosService.resumeFeedBack(jobSeekerEmail,feedBackMessage);
    }

}
