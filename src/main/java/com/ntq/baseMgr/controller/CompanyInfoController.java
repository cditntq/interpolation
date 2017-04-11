package com.ntq.baseMgr.controller;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.service.CompanyInfoService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.vo.CompanyInfoWithPositionInfoListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

 /*   *
     * 公司页面跳转
     *
     * @return*/

    @RequestMapping(value = "/companyInfosPage")
    public String index(HttpSession httpSession) {
        return "companyInfos";
    }





    /**
     * 发布职位首页(两个按钮：1 新公司发布职位,2已经录入公司发布职位)
     *
     * @return
     */
    @RequestMapping(value = "/positionReleaseIndex")
    public String positionReleaseIndex() {
        return "companyPositionReleaseIndex";
    }

    /**
     *验证码校验是否为已注册的公司
     *
     * @param session     TODO 这里需要处理的是当前的用户  但需要注意的在处理验证码失败的情况 返回操作的true 成功 能够转跳,false验证失败暂时没做
     * @param phoneNumber 手机号码
     * @param verifyCode  收到的验证码
     * @return
     */
    @RequestMapping(value = "/verifyMessageCode", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<Void> verifyMessageCode(HttpSession session, Long phoneNumber, String verifyCode) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            return companyInfoService.verifyMessageCode(session, phoneNumber, verifyCode);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage(StatusCode.Fail.getMessage());

        }
        return responseResult;
    }

    /**
     * 新的公司和其发布的职位录入
     *
     * @param companyInfoWithPositionInfoListVo                       公司信息以及职位信息
     * @return
     */
    @RequestMapping(value = "/addCompanyInfoWithPositionInfoList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> addCompanyInfoWithPositionInfoList(@RequestBody CompanyInfoWithPositionInfoListVo companyInfoWithPositionInfoListVo) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            responseResult = companyInfoService.addCompanyInfoWithPositionInfoList(companyInfoWithPositionInfoListVo);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.INSERT_FAIL.getMessage());
        }
        return responseResult;
    }

    /**
     * 公司信息更新
     *
     * @param companyInfos 公司实体
     * @return
     */
    @RequestMapping(value = "/updateCompanyInfos", method = RequestMethod.POST)
    public ResponseResult<Void> updateCompanyInfos(CompanyInfos companyInfos) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            responseResult = companyInfoService.updateCompanyInfos(companyInfos);
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
            responseResult = companyInfoService.getJobSeekerInfoVoById(id);
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
            page = companyInfoService.queryCompanyInfoListByCondition(page);
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
            result = companyInfoService.deleteCompanyInfoListByIds(ids);
        } catch (Exception e) {
            result.setCode(StatusCode.DELETE_FAIL.getCode());
            result.setFailureMessage(StatusCode.DELETE_FAIL.getMessage());

        }
        return result;
    }
    @RequestMapping(value = "/getTest")
    @ResponseBody
    public CompanyInfos getTest(){
        return companyInfoService.getTest();

    }
}
