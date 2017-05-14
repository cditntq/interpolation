package com.ntq.baseMgr.controller;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.service.NtqCompanyPositionDealService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.vo.CompanyPositionInfoExtVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * <p>@description: 内推圈审核公司职位相关信息(这个页面的忘记是否测试过 todo后面记得测试 )</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.controller
 * @className:
 * @author: shuangyang
 * @date: 17-4-17 下午9:32
 */
@Controller
@RequestMapping("/ntqCompanyPositionDeal")
public class NtqCompanyPositionDealController {
    private final Logger logger = LoggerFactory.getLogger(NtqCompanyPositionDealController.class);
    @Autowired
    private NtqCompanyPositionDealService ntqCompanyPositionDealService;

    /**
     * 分页查询公司职位信息(todo 未做测试)
     *
     * @param page 分页对象
     * @return
     */
    @RequestMapping(value = "/queryCompanyPositionInfoVoListByCondition")
    @ResponseBody
    public Page<CompanyPositionInfoExtVo> queryCompanyPositionInfoVoListByCondition(@RequestBody Page<CompanyPositionInfoExtVo> page) {
        try {
            page = ntqCompanyPositionDealService.queryCompanyPositionInfoVoListByCondition(page);
        } catch (Exception e) {
            page.setSuccess(false);
            logger.error("the method queryCompanyPositionInfoListByCondition in the controller of NtqCompanyPositionDealController throw Exception:", e);
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
            responseResult = ntqCompanyPositionDealService.getCompanyInfoById(companyInfoId);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.GET_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.GET_FAIL.getMessage());
            logger.error("the method getCompanyInfoById in the controller of NtqCompanyPositionDealController throw Exception:", e);
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
            responseResult = ntqCompanyPositionDealService.getCompanyPositionInfoById(positionId);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.GET_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.GET_FAIL.getMessage());
            logger.error("the method getCompanyPositionInfoById in the controller of NtqCompanyPositionDealController throw Exception:", e);
        }
        return responseResult;
    }


    /**
     * 通过职位id发送给公司职位拒绝发送，包括理由
     *
     * @param positionId 职位ID编号
     * @param message    拒绝理由
     * @return
     */
    @RequestMapping(value = "/rejectPositionRelease")
    @ResponseBody
    public ResponseResult<Void> rejectPositionRelease(Long positionId, String message) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            responseResult = ntqCompanyPositionDealService.updatePositionRemarkAndSendMail(positionId, message);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage(StatusCode.Fail.getMessage());
            logger.error("the method rejectPositionRelease in the controller of NtqCompanyPositionDealController throw Exception:", e);
        }
        return responseResult;
    }


    /**
     * 发布职位 todo 差一些没有完善
     *
     * @param positionId 职位ID编号
     * @return
     */
    @RequestMapping(value = "/positionRelease")
    @ResponseBody
    public ResponseResult<Void> positionRelease(Long positionId) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            responseResult = ntqCompanyPositionDealService.updateCompanyPositionInfo(positionId);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.UPDATE_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.UPDATE_FAIL.getMessage());
            logger.error("the method positionRelease in the controller of NtqCompanyPositionDealController throw Exception:", e);
        }
        return responseResult;
    }


}