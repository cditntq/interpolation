package com.ntq.baseMgr.controller;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.po.CompanyPositionInfosWithBLOBs;
import com.ntq.baseMgr.service.CompanyInfoService;
import com.ntq.baseMgr.service.CompanyPositionInfoService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * <p>@description: 职位信息相关的Controller</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.controller
 * @className:
 * @author: shuangyang
 * @date: 17-4-7 下午1:12
 */
@Controller
@RequestMapping(value = "/companyPositionInfo")
public class CompanyPositionInfoController {
    @Autowired
    private CompanyPositionInfoService companyPositionInfoService;//求职者service
    @Autowired
    private CompanyInfoService companyInfoService;


    /**
     * 企业新增发布职位信息
     *
     * @param companyPositionInfosWithBLOBs
     * @return
     */
    @RequestMapping(value = "/addCompanyPositionInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> addCompanyPositionInfo(@RequestBody CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs) {
        ResponseResult<Void> result = new ResponseResult<>();
        try {
            return companyPositionInfoService.addCompanyPositionInfo(companyPositionInfosWithBLOBs);
        } catch (Exception e) {
            result.setCode(StatusCode.INSERT_FAIL.getCode());
            result.setMessage(StatusCode.INSERT_FAIL.getMessage());

        }
        return result;
    }

    /**
     * 通过id查看职位信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getCompanyPositionInfoById")
    @ResponseBody
    public ResponseResult<CompanyPositionInfosWithBLOBs> getCompanyPositionInfoById(Long id) {
        ResponseResult<CompanyPositionInfosWithBLOBs> result = new ResponseResult<>();
        try {
            return companyPositionInfoService.getCompanyPositionInfoById(id);
        } catch (Exception e) {
            result.setCode(StatusCode.GET_FAIL.getCode());
            result.setMessage(StatusCode.GET_FAIL.getMessage());

        }
        return result;
    }

    /**
     * 分页查询求职者信息
     *
     * @param page 分页对象
     * @return
     */
    @RequestMapping(value = "/queryCompanyPositionInfoListByCondition")
    @ResponseBody
    public Page<CompanyPositionInfos> queryCompanyPositionInfoListByCondition(Page<CompanyPositionInfos> page) {
        try {
            return companyPositionInfoService.queryCompanyPositionInfoListByCondition(page);
        } catch (Exception e) {
            page.setSuccess(false);
        }
        return page;
    }

    /**
     * 重新发布职位 1:当为审核状态时，更新当前信息,当不为审核状态新增加
     *
     * @param companyPositionInfosWithBLOBs
     * @return
     */
    @RequestMapping(value = "/updateOrInsertCompanyPositionInfo")
    @ResponseBody
    public ResponseResult<Void> updateOrInsertCompanyPositionInfo(CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs) {
        ResponseResult<Void> result = new ResponseResult<>();
        try {
            return companyPositionInfoService.updateOrInsertCompanyPositionInfo(companyPositionInfosWithBLOBs);
        } catch (Exception e) {
            result.setCode(StatusCode.INSERT_FAIL.getCode());
            result.setMessage(StatusCode.INSERT_FAIL.getMessage());

        }
        return result;
    }



/*
    */

    /**
     * 根据id批量删除职位信息
     *
     * @param ids
     * @return
     * @throws Exception
     *//*

    @RequestMapping(value = "/deleteJobSeekerInfoListByIds")
    @ResponseBody
    public ResponseResult<Void> deleteCompanyPositionInfoListByIds(String ids) {
        ResponseResult<Void> result = new ResponseResult<>();
        try {
            return companyPositionInfoService.deleteCompanyPositionInfoListByIds(ids);
        } catch (Exception e) {
            result.setCode(StatusCode.DELETE_FAIL.getCode());
            result.setMessage(StatusCode.DELETE_FAIL.getMessage());

        }
        return result;
    }
*/
    @RequestMapping(value = "/getTest")
    @ResponseBody
    public CompanyPositionInfos getTest() {
        CompanyPositionInfos companyPositionInfos = companyPositionInfoService.getTest();
        return companyPositionInfos;
    }
}
