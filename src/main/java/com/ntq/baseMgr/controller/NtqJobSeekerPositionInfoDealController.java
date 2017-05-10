package com.ntq.baseMgr.controller;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.service.NtqJobSeekerPositionService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.vo.JobSeekerPositionDealVo;
import com.ntq.baseMgr.vo.JobSeekerPositionDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>@description: todo 职位信息和预览,职位的发布和拒绝的方法以及逻辑被错误地写在了NtqUserController里面</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.controller
 * @className:
 * @author: shuangyang
 * @date: 17-4-17 下午9:32
 */
@Controller
@RequestMapping(value = "/ntqJobSeekerPositionDeal")
public class NtqJobSeekerPositionInfoDealController {

    private final Logger logger = LoggerFactory.getLogger(NtqJobSeekerPositionInfoDealController.class);

    @Autowired
    private NtqJobSeekerPositionService ntqJobSeekerPositionService;

    /**
     * 分页查询获取求职者职位的详细信息
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryJobSeekerPositionDetailVoByCondition")
    @ResponseBody
    public Page<JobSeekerPositionDetailVo> queryJobSeekerPositionDetailVoByCondition(@RequestBody Page<JobSeekerPositionDetailVo> page) {
        try {
            page = ntqJobSeekerPositionService.queryJobSeekerPositionDetailVoByCondition(page);
        } catch (Exception e) {
            page.setSuccess(false);
            logger.error("the method queryJobSeekerPositionDetailVoByCondition in the controller of ntqJobSeekerPositionInfoDealController throw Exception:", e);
        }
        return page;
    }

    /**
     * 根据职位编号获取职位相关信息(todo 待测试)
     *
     * @param positionId
     * @return
     */
    @RequestMapping(value = "/getCompanyPositionInfoById")
    @ResponseBody
    public ResponseResult<CompanyPositionInfos> getCompanyPositionInfoById(Long positionId) {
        ResponseResult<CompanyPositionInfos> responseResult = new ResponseResult<>();
        try {
            return ntqJobSeekerPositionService.getCompanyPositionInfoById(positionId);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.GET_FAIL.getCode());
            responseResult.setMessage(StatusCode.GET_FAIL.getMessage());
            logger.error("the method getCompanyPositionInfoById in the controller of ntqJobSeekerPositionInfoDealController throw Exception:", e);
        }
        return responseResult;
    }

    /**
     * 根据公司编号获取公司相关信息(todo 待测试)
     *
     * @param companyInfoId
     * @return
     */
    @RequestMapping(value = "/getCompanyInfoById")
    @ResponseBody
    public ResponseResult<CompanyInfos> getCompanyInfoById(Long companyInfoId) {
        ResponseResult<CompanyInfos> responseResult = new ResponseResult<>();
        try {
            return ntqJobSeekerPositionService.getCompanyInfoById(companyInfoId);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.GET_FAIL.getCode());
            responseResult.setMessage(StatusCode.GET_FAIL.getMessage());
            logger.error("the method getCompanyInfoById in the controller of ntqJobSeekerPositionInfoDealController throw Exception:", e);
        }
        return responseResult;
    }


    /**
     * 更新简历状态
     *
     * @param jobSeekerPositionDealVo 求职者职位处理状态
     * @return
     */
    @RequestMapping(value = "/updateResumeDeliveryDealStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> updateResumeDeliveryDealStatus(@RequestBody JobSeekerPositionDealVo jobSeekerPositionDealVo) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            return ntqJobSeekerPositionService.updateResumeDeliveryDealStatus(jobSeekerPositionDealVo);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setMessage("简历状态更新失败");
        }
        return responseResult;

    }
  /*  *//**
     * 通过职位id发送给公司职位拒绝发送，包括理由
     *
     * @param positionId 职位ID编号
     * @param message    拒绝理由
     * @return
     *//*
    @RequestMapping(value = "/rejectPositionRelease")
    @ResponseBody
    public ResponseResult<Void> rejectPositionRelease(Long positionId, String message) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            responseResult = userService.updatePositionRemarkAndSendMail(positionId, message);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage(StatusCode.Fail.getMessage());
        }
        return responseResult;
    }


    *//**
     * 发布职位 todo 差一些没有完善
     *
     * @param positionId 职位ID编号
     * @return
     *//*
    @RequestMapping(value = "/positionRelease")
    @ResponseBody
    public ResponseResult<Void> positionRelease(Long positionId) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            responseResult = userService.updateCompanyPositionInfo(positionId);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.UPDATE_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.UPDATE_FAIL.getMessage());
        }
        return responseResult;
    }*/

}
