package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.mapper.*;
import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.*;
import com.ntq.baseMgr.service.IUploadFileService;
import com.ntq.baseMgr.service.JobSeekerInfosService;
import com.ntq.baseMgr.util.*;
import com.ntq.baseMgr.vo.JobSeekerPositionDealVo;
import com.ntq.baseMgr.vo.UploadFileVo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>@description: 求职者信息处理Service</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.serivce.impl
 * @className:
 * @author: shuangyang
 * @date: 17-3-19 下午2:37
 */
@Service
public class JobSeekerInfosServiceImpl implements JobSeekerInfosService {

    @Value("#{configProperties['mail_from']}")
    private String resumPath;//简历存放地址
    private static Logger logger = LoggerFactory.getLogger(JobSeekerInfosServiceImpl.class);
    @Autowired
    private JobSeekerInfosMapper jobSeekerInfosMapper;
    @Autowired
    private JobSeekerResumeDeliveryMapper jobSeekerResumeDeliveryMapper;
    @Autowired
    private IUploadFileService uploadFileService;//上传文件的service
    @Autowired
    private MailSenderServiceImpl mailSenderServiceImpl;//邮件发送服务
    @Autowired
    private MessageValidateRecordMapper messageValidateRecordMapper;
    @Autowired
    private CompanyInfosMapper companyInfosMapper;
    @Autowired
    private CompanyPositionInfosMapper companyPositionInfosMapper;


    public ResponseResult<Void> insertJobSeekerInfo(JobSeekerInfosVo jobSeekerInfosVo, UploadFileVo vo, HttpServletRequest request) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        //测试数据录入 start--
        jobSeekerInfosVo.setIsAddNtqweixin(1);//设置为已经添加
        jobSeekerInfosVo.setRecordOfFormalSchooling(3);//设置默认为本科
        //测试数据录入 end--
        //当前录入时时间
        Date currentDate = new Date();
        jobSeekerInfosVo.setServerCreateDate(currentDate);
        jobSeekerInfosVo.setServerUpdateDate(currentDate);
        jobSeekerInfosVo.setIsValid(1);//设置默认有效
        //录入求职者信息
        //1.插入用户信息并返回id
        jobSeekerInfosMapper.insertAndGetKey(jobSeekerInfosVo);
        Long jobSeekerInfoId = jobSeekerInfosVo.getId();

        //2.拼接简历名称 "username_"+"职位编码.doc"
        vo.setName(jobSeekerInfosVo.getJobSeekerName() + "_" + jobSeekerInfosVo.getJobCode() + ".doc");
        //3.上传附件处理并返回存储路径
        String storePath = uploadFileService.uploadForm(vo);

        //4.存储附件相关信息
        JobSeekerResumeDelivery delivery = new JobSeekerResumeDelivery();
        delivery.setIsValid(1);//默认设置有效
        delivery.setResumePath(storePath);
        delivery.setJobSeekerInfosId(jobSeekerInfoId);
        delivery.setServerCreateDate(currentDate);
        delivery.setServerUpdateDate(currentDate);
        delivery.setJobCode(jobSeekerInfosVo.getJobCode());
        delivery.setResumeName(vo.getName());
        delivery.setDealStatus(1);//默认处理状态
        delivery.setIsValid(1);//设置默认有效
        delivery.setIsFeedback(1);//设置默认未反馈
        jobSeekerResumeDeliveryMapper.insertJobSeekerResumDelivery(delivery);
        responseResult.setCode(StatusCode.INSERT_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.INSERT_SUCCESS.getMessage());
        return responseResult;
    }

    @Override
    public Page<JobSeekerInfosExtDto> queryJobSeekerInfosListByCondition(Page<JobSeekerInfosExtDto> page) throws Exception {
          /*  Page<JobSeekerInfosExtDto> pageResult=new Page<>();*/
        List<JobSeekerInfosExtDto> jobSeekerInfosExtDtos = jobSeekerInfosMapper.queryJobSeekerInfosListByCondition(page);
        page.setResults(jobSeekerInfosExtDtos);
        page.setSuccess(true);
        return page;
    }

    @Override
    public ResponseResult<Void> deleteBatchJobSeekerInfoList(String ids) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        //解析字符串
        List<Long> idList = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        //逻辑删除用户主表以及附表信息 todo 目前没有实现在同一个mybatis里面两个更新语句
        //1.删除主表信息
        jobSeekerInfosMapper.deleteBatchJobSeekerInfoListAndResumeDelivery(idList);
        //2.删除附件信息
        jobSeekerResumeDeliveryMapper.deleteBatchJobSeekerResumeDeliveryList(idList);
        responseResult.setCode(StatusCode.DELETE_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.DELETE_SUCCESS.getMessage());
        return responseResult;
    }

    /**
     * 插入求职者个人信息并返回key
     *
     * @param record
     * @return
     */
    public Long insertAndReturnKey(JobSeekerInfos record) {
        record.setServerCreateDate(new Date());
        record.setServerUpdateDate(new Date());
        record.setIsValid(1);//默认设置信息有效d
        return jobSeekerInfosMapper.insertAndGetKey(record);
    }


    /**
     * 通过求职者信息id查看投递简历相信信息
     *
     * @param id
     * @return
     */

    public ResponseResult<JobSeekerInfosVo> getJobSeekerInfoVoById(Long id) {
        ResponseResult<JobSeekerInfosVo> responseResult = new ResponseResult<>();
        try {
         /*   Map<String, JobSeekerInfosVo> map = new HashMap<>();*/
            //1.查询求职者的个人相关信息
        /*    JobSeekerInfosVo jobSeekerInfosVo = (JobSeekerInfosVo) jobSeekerInfosMapper.getJobSeekerInfoById(id);
            //2.查询对应的求职者的简历信息的处理状态
            JobSeekerResumeDelivery jobSeekerResumeDelivery = jobSeekerResumeDeliveryService.getJobSeekerResumeDeliveryByJobSeekerId(id);
            //获取是否有效
            jobSeekerInfosVo.setIsValid(jobSeekerResumeDelivery.getIsValid());
            //获取存储路径
            jobSeekerInfosVo.setResumePath(jobSeekerResumeDelivery.getResumePath());
            //获取处理状态
            jobSeekerInfosVo.setDealStatus(jobSeekerResumeDelivery.getDealStatus());*/
            //封装返回信息
            //  responseResult.setData(map);
            responseResult.setCode(StatusCode.OK.getCode());
            responseResult.setMessage(StatusCode.OK.getMessage());
        } catch (Exception e) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage(StatusCode.Fail.getMessage());
        }
        return responseResult;
    }

    /**
     * 更新简历状态
     *
     * @param resumeDeliveryId 传递简历的id
     * @param dealStatus       处理状态
     * @return
     */
    @Override
    public ResponseResult<Void> updateResumeDeliveryDealStatus(long resumeDeliveryId, int dealStatus) throws Exception {
        ResponseResult<Void> rep = new ResponseResult<>();
        jobSeekerResumeDeliveryMapper.updateResumeDeliveryDealStatus(resumeDeliveryId, dealStatus);
        rep.setCode(StatusCode.UPDATE_SUCCESS.getCode());
        rep.setMessage(StatusCode.UPDATE_SUCCESS.getMessage());
        return rep;
    }

    /**
     * 简历相关意见反馈
     *
     * @param jobSeekerEmail  求职者邮箱
     * @param feedBackMessage 反馈信息
     * @return
     */
    @Override
    public ResponseResult<String> resumeFeedBack(String jobSeekerEmail, String feedBackMessage) throws Exception {
        ResponseResult<String> responseResult = new ResponseResult<>();
        System.out.println(resumPath);
        MailBean mailBean = new MailBean();
        mailBean.setToEmails(new String[]{jobSeekerEmail});
        mailBean.setSubject("简历修改");
        mailBean.setContext(feedBackMessage);
        mailSenderServiceImpl.sendMail(mailBean);
        responseResult.setCode(StatusCode.MAIL_SENDER_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.MAIL_SENDER_SUCCESS.getMessage());
        return responseResult;
    }

    /**
     * 获取验证码
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public ResponseResult<Void> getMessageCode(Long phoneNumber) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        //1. 调用接口获取手机号并得到验证码
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(MessageCodeUtil.GET_MESSAGE_CODE_URL);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");
        //1.1生成验证码
        int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
        //1.2生成短信内容
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
        NameValuePair[] data = {//提交短信
                new NameValuePair("account", MessageCodeUtil.APIID),
                new NameValuePair("password", MessageCodeUtil.APIKEY),
                new NameValuePair("mobile", phoneNumber.toString()),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);
        client.executeMethod(method);
        String SubmitResult = method.getResponseBodyAsString();
        //解析请求得到的参数
        Document doc = DocumentHelper.parseText(SubmitResult);
        Element root = doc.getRootElement();
        String code = root.elementText("code");
        String msg = root.elementText("msg");
        String smsid = root.elementText("smsid");
        //验证码录入数据库
        MessageValidateRecord messageValidateRecord = new MessageValidateRecord();
        messageValidateRecord.setToken(String.valueOf(mobile_code));
        messageValidateRecord.setValideTime(new Date());//验证码发送时间
        messageValidateRecord.setPhoneNum(phoneNumber);//电话
        if ("2".equals(code)) {//请求成功
            // System.out.println("短信提交成功");//插入数验证码的数据库
            messageValidateRecord.setSendSuccess(1);//发送成功
            responseResult.setCode(StatusCode.INSERT_SUCCESS.getCode());
            responseResult.setMessage("短信发送成功");
        } else {//异常处理
            messageValidateRecord.setSendSuccess(1);//发送失败
            responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
            responseResult.setFailureMessage("短信发送失败");
        }
        messageValidateRecordMapper.insertMessageValidateRecord(messageValidateRecord);
        return responseResult;
    }

    /**
     * 转跳验证
     *
     * @param session
     * @param phoneNumber 手机号
     * @param verifyCode  验证码
     * @return
     */
    @Override
    public ResponseResult<Void> verifyMessageCode(HttpSession session, Long phoneNumber, String verifyCode) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        //1.匹配验证码
        MessageValidateRecord messageValidateRecord = messageValidateRecordMapper.getMessageValidateRecord(phoneNumber, verifyCode);
        //验证码匹配失败
        if (null == messageValidateRecord) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("验证码输入错误");
            return responseResult;
        }
        //2.查求职者有无与该号码匹配的的信息
        JobSeekerInfos jobSeekerInfos = jobSeekerInfosMapper.getJobSeekerInfoByPhoneNo(phoneNumber);
        if (jobSeekerInfos != null) {
            session.setAttribute(ConstantUtil.JOBSEEKER_INFOS, jobSeekerInfos);
            //转跳到 到职位信息的列表
            responseResult.setCode(StatusCode.OK.getCode());
            responseResult.setMessage(StatusCode.OK.getMessage());
        } else {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("没有与该手机号匹配的用户！请认真核实");
        }
        return responseResult;
    }

    /**
     * 求职者投递岗位分页查询
     *
     * @param page 分页参数
     * @return
     */
    @Override
    public Page<JobSeekerPositionDealVo> queryJobSeekerPositionVoList(Page<JobSeekerPositionDealVo> page) throws Exception {

        // 获取当前用户的jobSeekerInfoId
        JobSeekerInfos jobSeekerInfos = (JobSeekerInfos) SessionUtil.getSessionAttribute(ConstantUtil.JOBSEEKER_INFOS);
        Long jobSeekerInfosId = jobSeekerInfos.getId();
        page.getParams().put("jobSeekerInfosId", jobSeekerInfosId);
        //执行查询
        page.setResults(companyPositionInfosMapper.queryJobSeekerPositionVoList(page));
        page.setSuccess(true);
        return page;
    }

    /**
     * 通过id编号获取公司信息
     *
     * @param companyInfoId@return
     */
    @Override
    public ResponseResult<CompanyInfos> getCompanyInfoById(Long companyInfoId) throws Exception {
        ResponseResult<CompanyInfos> responseResult = new ResponseResult<>();
        responseResult.setCode(StatusCode.GET_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.GET_SUCCESS.getMessage());
        responseResult.setData(companyInfosMapper.getCompanyInfoById(companyInfoId));
        return responseResult;
    }

    /**
     * 通过id编号获取职位相关信息
     *
     * @param positionId@return
     */
    @Override
    public ResponseResult<CompanyPositionInfos> getCompanyPositionInfoById(Long positionId) throws Exception {
        ResponseResult<CompanyPositionInfos> responseResult = new ResponseResult<>();
        responseResult.setCode(StatusCode.GET_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.GET_SUCCESS.getMessage());
        responseResult.setData(companyPositionInfosMapper.getCompanyPositionInfoById(positionId));
        return responseResult;
    }
}
