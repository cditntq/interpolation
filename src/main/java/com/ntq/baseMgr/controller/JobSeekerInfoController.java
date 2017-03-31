package com.ntq.baseMgr.controller;

import com.ntq.baseMgr.entity.JobSeekerInfos;
import com.ntq.baseMgr.service.JobSeekerInfosService;
import com.ntq.baseMgr.vo.JobSeekerInfosVo;
import com.ntq.baseMgr.vo.UploadFileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/JobSeekerInfo")
public class JobSeekerInfoController {
    @Autowired
    private JobSeekerInfosService jobSeekerInfosService;//求职者service

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
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryJobSeekerInfoListByCondition")
    @ResponseBody
    public JobSeekerInfosVo getJobSeekerInfoVoById(Long id){
        return jobSeekerInfosService.getJobSeekerInfoVoById(id);
    }

    //todo 更改求职者的相关信息

    /**
     * 分页查询求职者信息
     * @param page 页码
     * @param size  分页大小
     * @param whereCondition 查询条件
     * @return
     */
    @RequestMapping(value = "/queryJobSeekerInfoListByCondition")
    @ResponseBody
    public List<JobSeekerInfos> queryJobSeekerInfosListByCondition(int page,int size,String whereCondition){
        List<JobSeekerInfos> jobSeekerInfosList=jobSeekerInfosService.queryJobSeekerInfosListByCondition(page,size,whereCondition);
        return jobSeekerInfosList;
    }
    /**
     * 根据id批量删除求职者个人信息包括简历 todo
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteByIds")
    @ResponseBody
    public int deleteByIds(String ids) {
        return jobSeekerInfosService.deleteBatch(ids);
    }

}
