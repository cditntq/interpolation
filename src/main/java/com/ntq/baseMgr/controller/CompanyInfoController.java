package com.ntq.baseMgr.controller;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.service.CompanyInfoService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * <p>@description:公司信息的Controller </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.controller
 * @className:
 * @author: shuangyang
 * @date: 17-4-3 下午5:21
 */
@Controller
@RequestMapping(value = "/companyInfo")
public class CompanyInfoController {
    private final Logger logger = LoggerFactory.getLogger(CompanyInfoController.class);

    @Autowired
    private CompanyInfoService companyInfoService;//公司信息service

    /**
     * 公司页面跳转
     *
     * @return
     */
    @RequestMapping(value = "/companyInfosPage")
    public String index(HttpSession httpSession) {
        return "companyInfos";
    }


    /**
     * 公司信息录入
     * @param companyInfos 公司实体
     * @return
     */
    @RequestMapping(value = "/addCompanyInfos", method = RequestMethod.POST)
    public ResponseResult<Void> addCompanyInfos(CompanyInfos companyInfos){
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            responseResult=  companyInfoService.addCompanyInfos(companyInfos);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.INSERT_FAIL.getMessage());
        }
        return responseResult;
    }

    /**
     * 公司信息更新
     * @param companyInfos 公司实体
     * @return
     */
    @RequestMapping(value = "/updateCompanyInfos", method = RequestMethod.POST)
    public ResponseResult<Void> updateCompanyInfos(CompanyInfos companyInfos)  {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            responseResult= companyInfoService.updateCompanyInfos(companyInfos);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.UPDATE_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.UPDATE_FAIL.getMessage());
        }
        return responseResult;
    }

    /**
     * 通过id编号获取公司信息
     *
     * @param id 公司自增id
     * @return
     */
    @RequestMapping(value = "/getCompanyInfosById")
    @ResponseBody
    public ResponseResult<CompanyInfos> getCompanyInfosById(Long id) {
        ResponseResult<CompanyInfos> responseResult = new ResponseResult<>();
        try {
            responseResult= companyInfoService.getJobSeekerInfoVoById(id);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.UPDATE_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.UPDATE_FAIL.getMessage());
        }
        return responseResult;
    }

    /**
     * 分页查询求职者信息
     *
     * @param page 分页对象
     * @return
     */
    @RequestMapping(value = "/queryCompanyInfoListByCondition")
    @ResponseBody
    public Page<CompanyInfos> queryCompanyInfoListByCondition(Page<CompanyInfos> page) {
        try {
            page= companyInfoService.queryCompanyInfoListByCondition(page);
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
     */
    @RequestMapping(value = "/deleteCompanyInfoListByIds")
    @ResponseBody
    public ResponseResult<Void> deleteCompanyInfoListByIds(String ids) {
        ResponseResult<Void> result = new ResponseResult<>();
        try {
            result= companyInfoService.deleteCompanyInfoListByIds(ids);
        } catch (Exception e) {
            result.setCode(StatusCode.DELETE_FAIL.getCode());
            result.setFailureMessage(StatusCode.DELETE_FAIL.getMessage());

        }
        return result;
    }

}
