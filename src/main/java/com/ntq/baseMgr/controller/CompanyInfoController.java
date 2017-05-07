package com.ntq.baseMgr.controller;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfosWithBLOBs;
import com.ntq.baseMgr.service.CompanyInfoService;
import com.ntq.baseMgr.service.CompanyPositionInfoService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.vo.CompanyInfoWithPositionInfoListVo;
import com.ntq.baseMgr.vo.CompanyPositionInfoVo;
import com.ntq.baseMgr.vo.JobSeekerInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>@description:公司信息的Controller  注意 这里的公司个体的区别以hr的验证码判断</p>
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

    @Autowired
    private CompanyPositionInfoService companyPositionInfoService;//职位的service


    /**
     * 点击获取验证码
     *
     * @param phoneNumber 电话号码
     * @return 返回
     */
    @RequestMapping(value = "/getMessageCode", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<Void> getMessageCode(Long phoneNumber) {
        ResponseResult<Void> responseResult = new ResponseResult<>();

        try {
            responseResult = companyInfoService.getMessageCode(phoneNumber);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("获取验证码失败");
            logger.error("the method getMessageCode in companyInfo controller Failed:",e);
        }
        return responseResult;
    }

    /**
     * 验证码校验是否为已注册的公司
     * 这里需要处理的是当前的用户  但需要注意的在处理验证码失败的情况 返回操作的true 成功 能够转跳,false验证失败暂时没做
     *
     * @param session
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
            responseResult.setFailureMessage("短信验证失败");
            logger.error("the method verifyMessageCode in companyInfo controller Failed:",e);

        }
        return responseResult;
    }


    /**
     * 短信验证获取
     * 验证用户是否已存在，1.2如果不存在就发送验证码
     *
     * @param phoneNumber
     * @return
     */
    @RequestMapping(value = "/getMessageAfterValidatePhoneNumber", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<Void> getMessageAfterValidatePhoneNumber(Long phoneNumber) {
        ResponseResult<Void> responseResult = null;
        try {
            responseResult = companyInfoService.getMessageAfterValidatePhoneNumber(phoneNumber);
        } catch (Exception e) {
            responseResult = new ResponseResult<>();
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setMessage("操作失败！请检查");
            logger.error("the method getMessageAfterValidatePhoneNumber in companyInfo controller Failed:",e);
        }
        return responseResult;
    }

    /**
     * 注册验证
     *
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    @RequestMapping(value = "/verifyHrPhoneNumber", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<Void> verifyHrPhoneNumber(Long phoneNumber, String verifyCode) {
        ResponseResult<Void> responseResult = null;
        try {
            responseResult = companyInfoService.verifyHrPhoneNumber(phoneNumber, verifyCode);
        } catch (Exception e) {
            responseResult = new ResponseResult<>();
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setMessage("操作失败！请检查");
            logger.error("the method verifyHrPhoneNumber in companyInfo controller Failed:",e);
        }
        return responseResult;

    }

    /**
     * 新的公司和其发布的职位录入
     *
     * @param companyInfoWithPositionInfoListVo 公司信息以及职位信息
     * @return
     */
    @RequestMapping(value = "/addCompanyInfoWithPositionInfoList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> addCompanyInfoWithPositionInfoList(@RequestBody CompanyInfoWithPositionInfoListVo companyInfoWithPositionInfoListVo) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        try {
            responseResult = companyInfoService.addCompanyInfoWithPositionInfoList(companyInfoWithPositionInfoListVo);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.INSERT_FAIL.getMessage());
            logger.error("the method addCompanyInfoWithPositionInfoList in companyInfo controller Failed:",e);
        }
        return responseResult;
    }

/*    *//**
     * 公司信息更新
     *
     * @param companyInfos 公司实体
     * @return
     *//*
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
    }*/

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
/*
    *//**
     * 分页查询求职者信息
     *
     * @param page 分页对象
     * @return
     *//*
    @RequestMapping(value = "/queryCompanyInfoListByCondition")
    @ResponseBody
    public Page<CompanyInfos> queryCompanyInfoListByCondition(Page<CompanyInfos> page) {
        try {
            page = companyInfoService.queryCompanyInfoListByCondition(page);
        } catch (Exception e) {
            page.setSuccess(false);
        }
        return page;
    }*/

/*    *//**
     * 根据id批量删除求职者个人信息包括简历
     *
     * @param ids
     * @return
     *//*
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
    }*/

/*    @RequestMapping(value = "/getTest")
    @ResponseBody
    public CompanyInfos getTest() {
        return companyInfoService.getTest();

    }*/

/*********************************************职位相关********************************************************/


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
            logger.error("the method addCompanyPositionInfo in  the controller of companyInfo throw Exception:",e);
        }
        return result;
    }

    /**
     * 通过id查看职位信息
     *
     * @param positionId
     * @return
     */
    @RequestMapping(value = "/getCompanyPositionInfoById")
    @ResponseBody
    public ResponseResult<CompanyPositionInfosWithBLOBs> getCompanyPositionInfoById(Long positionId) {
        ResponseResult<CompanyPositionInfosWithBLOBs> result = new ResponseResult<>();
        try {
            return companyPositionInfoService.getCompanyPositionInfoById(positionId);
        } catch (Exception e) {
            result.setCode(StatusCode.GET_FAIL.getCode());
            result.setMessage(StatusCode.GET_FAIL.getMessage());
            logger.error("the method getCompanyPositionInfoById in companyInfo controller Failed:",e);
        }
        return result;
    }

    /**
     * 分页查询职位详细信息
     *
     * @param page 分页对象
     * @return
     */
    @RequestMapping(value = "/queryCompanyPositionInfoListByCondition")
    @ResponseBody
    public Page<CompanyPositionInfoVo> queryCompanyPositionInfoListByCondition(@RequestBody Page<CompanyPositionInfoVo> page) {
        try {
            return companyPositionInfoService.queryCompanyPositionInfoListByCondition(page);
        } catch (Exception e) {
            page.setSuccess(false);
            logger.error("the method queryCompanyPositionInfoListByCondition in companyInfo controller Failed:",e);
        }
        return page;
    }

    //

    /**
     * @param companyPositionInfosWithBLOBs
     * @return
     */
    @RequestMapping(value = "/updateOrInsertCompanyPositionInfo")
    @ResponseBody
    public ResponseResult<Void> updateOrInsertCompanyPositionInfo(@RequestBody CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs) {
        ResponseResult<Void> result = new ResponseResult<>();
        try {
            return companyPositionInfoService.updateOrInsertCompanyPositionInfo(companyPositionInfosWithBLOBs);
        } catch (Exception e) {
            result.setCode(StatusCode.INSERT_FAIL.getCode());
            result.setMessage(StatusCode.INSERT_FAIL.getMessage());
            logger.error("the method updateOrInsertCompanyPositionInfo in companyInfo controller Failed:",e);

        }
        return result;
    }


    /**
     * 查看求职者信息
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryJobSeekerInfoVoList")
    @ResponseBody
    public Page<JobSeekerInfoVo> queryJobSeekerInfoVoList(@RequestBody Page<JobSeekerInfoVo> page) {
        try {
            return companyPositionInfoService.queryJobSeekerInfoVoList(page);
        } catch (Exception e) {
            page.setSuccess(false);
            logger.error("the method queryJobSeekerInfoVoList in companyInfo controller Failed:",e);
        }
        return page;
    }

    /**
     * 下架职位
     * 更新职位状态为 4-待下架
     *
     * @param positionId 职位id
     * @param message    职位下架信息
     * @return
     */
    @RequestMapping(value = "/withDrawCompanyPositionInfo")
    @ResponseBody
    public ResponseResult<Void> withDrawCompanyPositionInfo(Long positionId, String message) {
        ResponseResult<Void> result = new ResponseResult<>();
        try {
            return companyPositionInfoService.withDrawCompanyPositionInfo(positionId, message);
        } catch (Exception e) {
            result.setCode(StatusCode.UPDATE_FAIL.getCode());
            result.setMessage("设置职位下架操作失败");
            logger.error("the method withDrawCompanyPositionInfo in companyInfo controller Failed:",e);
        }
        return result;
    }

    /**
     * 根据id批量删除职位信息
     *
     * @param
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
    public Page<Void> getTest() {
        Page<Void> page = new Page<>();
        //测试职位的json数据
        //CompanyPositionInfos companyPositionInfos = companyPositionInfoService.getTest();
        //测试分页查询的json数据
        Map<String, Object> map = new HashMap();
        map.put("jobSeekerNO", "00001");
        map.put("jobSeekerName", "杨爽");
        page.setParams(map);
        return page;
    }


}
