package com.ntq.baseMgr.controller;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.service.NtqUserService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.vo.CompanyPositionInfoExtVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ntqUser")
public class NtqUserController {

    @Autowired
    private NtqUserService userService;

    /**
     * 分页查询公司职位信息
     *
     * @param page 分页对象
     * @return
     */
    @RequestMapping(value = "/queryCompanyPositionInfoVoListByCondition")
    @ResponseBody
    public Page<CompanyPositionInfoExtVo> queryCompanyPositionInfoVoListByCondition(@RequestBody Page<CompanyPositionInfoExtVo> page) {
        try {
            page = userService.queryCompanyPositionInfoVoListByCondition(page);
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
            responseResult = userService.getCompanyInfoById(companyInfoId);
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
            responseResult = userService.getCompanyPositionInfoById(positionId);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.GET_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.GET_FAIL.getMessage());
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
            responseResult = userService.updatePositionRemarkAndSendMail(positionId, message);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage(StatusCode.Fail.getMessage());
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
            responseResult = userService.updateCompanyPositionInfo(positionId);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.UPDATE_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.UPDATE_FAIL.getMessage());
        }
        return responseResult;
    }


}