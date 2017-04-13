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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/NtqUser")
public class NtqUserController {

    @Autowired
    private NtqUserService userService;

    /**
     * 分页查询公司职位信息
     *
     * @param page 分页对象
     * @return
     */
    @RequestMapping(value = "/queryCompanyInfoListByCondition")
    @ResponseBody
    public Page<CompanyPositionInfoExtVo> queryCompanyInfoVoListByCondition(Page<CompanyPositionInfoExtVo> page) {
        try {
            page = userService.queryCompanyInfoVoListByCondition(page);
        } catch (Exception e) {
            page.setSuccess(false);
        }
        return page;
    }
    /**
     * 通过id编号获取公司信息
     *
     * @param id 公司自增id
     * @return
     */
    @RequestMapping(value = "/getCompanyInfoById")
    @ResponseBody
    public ResponseResult<CompanyInfos> getCompanyInfoById(Long id) {
        ResponseResult<CompanyInfos> responseResult = new ResponseResult<>();
        try {
            responseResult = userService.getCompanyInfoById(id);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.GET_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.GET_FAIL.getMessage());
        }
        return responseResult;
    }

    /**
     * 通过id编号获取职位相关信息
     *
     * @param id 职位ID编号
     * @return
     */
    @RequestMapping(value = "/getCompanyPositionInfoById")
    @ResponseBody
    public ResponseResult<CompanyPositionInfos> getCompanyPositionInfoById(Long id) {
        ResponseResult<CompanyPositionInfos> responseResult = new ResponseResult<>();
        try {
            responseResult = userService.getCompanyPositionInfoById(id);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.GET_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.GET_FAIL.getMessage());
        }
        return responseResult;
    }







}