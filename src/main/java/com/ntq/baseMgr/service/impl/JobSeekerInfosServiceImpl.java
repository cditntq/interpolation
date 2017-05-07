package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.mapper.*;
import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.*;
import com.ntq.baseMgr.service.IUploadFileService;
import com.ntq.baseMgr.service.JobSeekerInfosService;
import com.ntq.baseMgr.util.*;
import com.ntq.baseMgr.vo.JobSeekerPositionDealVo;
import com.ntq.baseMgr.vo.MessageValidateRecordExtVo;
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
import java.text.SimpleDateFormat;
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
    private String resumePath;//简历存放地址
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

        //1.职位相关信息核实
        Long jobCode = jobSeekerInfosVo.getJobCode();
        CompanyPositionInfos companyPositionInfo=companyPositionInfosMapper.getCompanyPositionInfoByPositionNo(jobCode);
        //查询职位信息为空的处理
        if (null==companyPositionInfo) {
            responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
            responseResult.setMessage("当前职位编号对应的职位不存在,请核实职位信息");
            return responseResult;
        }
        //查询职位为已经下架的时候的处理
        if (5==companyPositionInfo.getPostionStatus()) {
            responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
            responseResult.setMessage("当前职位编号对应的职位已经下架");
            return responseResult;
        }
        //2.录入求职者信息,以及求职简历信息
        //当前录入时时间
        Date currentDate = new Date();
        jobSeekerInfosVo.setServerCreateDate(currentDate);
        jobSeekerInfosVo.setServerUpdateDate(currentDate);
        jobSeekerInfosVo.setIsValid(1);//设置默认有效
        //录入求职者信息
        //2.1.插入用户信息并返回id
        jobSeekerInfosMapper.insertAndGetKey(jobSeekerInfosVo);
        Long jobSeekerInfoId = jobSeekerInfosVo.getId();

        //2.2.拼接简历名称 "username_"+"职位编码.doc"
        vo.setName(jobSeekerInfosVo.getJobSeekerName() + "_" + jobSeekerInfosVo.getJobCode() + ".doc");
        //2.3.上传附件处理并返回存储路径
        String storePath = uploadFileService.uploadForm(vo);

        //2.4.存储附件相关信息
        JobSeekerResumeDelivery delivery = new JobSeekerResumeDelivery();
        delivery.setIsValid(1);//默认设置有效
        delivery.setResumePath(storePath);
        delivery.setJobSeekerInfosId(jobSeekerInfoId);
        delivery.setServerCreateDate(currentDate);
        delivery.setServerUpdateDate(currentDate);
        delivery.setJobCode(jobSeekerInfosVo.getJobCode().toString());
        delivery.setResumeName(vo.getName());
        delivery.setDealStatus(1);//默认处理状态
        delivery.setIsValid(1);//设置默认有效
        delivery.setIsFeedback(1);//设置默认未反馈
        jobSeekerResumeDeliveryMapper.insertJobSeekerResumDelivery(delivery);
        responseResult.setCode(StatusCode.INSERT_SUCCESS.getCode());
        responseResult.setMessage("初次录入简历成功");
        //记录当前的求职者到session
        JobSeekerInfos jobSeekerInfo = jobSeekerInfosVo;
        SessionUtil.setSession(ConstantUtil.JOBSEEKER_INFOS, jobSeekerInfo);
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
        //1.验证该手机号是否已经注册
        JobSeekerInfos jobSeekerInfo = jobSeekerInfosMapper.getJobSeekerInfoByPhoneNo(phoneNumber);
        if (null == jobSeekerInfo) {
            responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
            responseResult.setFailureMessage("该用户未注册,请核实");
            return responseResult;
        }
        //2.获取验证码
        MessageValidateRecordExtVo messageValidateRecordExtVo = MessageCodeUtil.sendAndGetMessageCode(phoneNumber);
        MessageValidateRecord messageValidateRecord = messageValidateRecordExtVo.getMessageValidateRecord();
        if ("2".equals(messageValidateRecordExtVo.getCode())) {//请求成功
            // System.out.println("短信提交成功");//插入数验证码的数据库
            messageValidateRecordExtVo.getMessageValidateRecord().setSendSuccess(1);//发送成功
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

        //1.查求职者有无与该号码匹配的的信息
        JobSeekerInfos jobSeekerInfos = jobSeekerInfosMapper.getJobSeekerInfoByPhoneNo(phoneNumber);
        if (jobSeekerInfos == null) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("没有与该手机号匹配的用户！请认真核实");
            return responseResult;
        }
        //2.匹配验证码
        MessageValidateRecord messageValidateRecord = messageValidateRecordMapper.getMessageValidateRecord(phoneNumber, verifyCode);
        //验证码匹配失败
        if (null == messageValidateRecord) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("验证码输入错误");
            return responseResult;
        }
        //判断超期
        //比较两个日期相差的天数
        //差的天数
        double diffDays = DateUtil.getDaysMargin(messageValidateRecord.getValideTime());
        //分钟数e
        double diffMinutes = DateUtil.getMinutesMargin(messageValidateRecord.getValideTime());
        //判断验证码的安全性
        if (diffDays > 0 || diffMinutes > 2) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("验证码超时已失效！请重新获取");
            return responseResult;
        } else {//记录jobSeekerInfo到session会话
            session.setAttribute(ConstantUtil.JOBSEEKER_INFOS, jobSeekerInfos);
            //转跳到 到职位信息的列表
            responseResult.setCode(StatusCode.OK.getCode());
            responseResult.setMessage(StatusCode.OK.getMessage());
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

    /**
     * 短信验证获取
     * 验证求职者用户是否已存在，1.2如果不存在就发送验证码
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public ResponseResult<Void> getMessageAfterValidatePhoneNumber(Long phoneNumber) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        //   1.验证用户是否已存在(依据用户手机号)
        JobSeekerInfos jobSeekerInfo = jobSeekerInfosMapper.getJobSeekerInfoByPhoneNo(phoneNumber);
        //可以插入用户信息
        if (null == jobSeekerInfo) {
            //2 发送短信验证码
            MessageValidateRecordExtVo messageValidateRecordExtVo = MessageCodeUtil.sendAndGetMessageCode(phoneNumber);
            if ("2".equals(messageValidateRecordExtVo.getCode())) {//请求成功
                // System.out.println("短信提交成功");//插入数验证码的数据库
                messageValidateRecordExtVo.getMessageValidateRecord().setSendSuccess(1);//发送成功
                responseResult.setCode(StatusCode.INSERT_SUCCESS.getCode());
                responseResult.setMessage("短信发送成功");
            } else {//异常处理
                messageValidateRecordExtVo.getMessageValidateRecord().setSendSuccess(1);//发送失败
                responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
                responseResult.setFailureMessage("短信发送失败");
            }
            //插入验证码信息
            messageValidateRecordMapper.insertMessageValidateRecord(messageValidateRecordExtVo.getMessageValidateRecord());

        } else {//返回
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setMessage("当前手机号已被注册,请核实");
            return responseResult;
        }
        return responseResult;
    }

    @Override
    public ResponseResult<Void> verifyJobSeekerPhoneNumber(Long phoneNumber, String verifyCode) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        MessageValidateRecord messageValidateRecord = messageValidateRecordMapper.getMessageValidateRecord(phoneNumber, verifyCode);
        //验证码匹配失败
        if (null == messageValidateRecord) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("验证码输入错误");
            return responseResult;
        }
        //差的天数
        int diffDays = DateUtil.getDaysMargin(messageValidateRecord.getValideTime());
        //分钟数e
        double diffMinutes = DateUtil.getMinutesMargin(messageValidateRecord.getValideTime());
        //判断验证码的安全性
        if (diffDays > 0 || diffMinutes > 2) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("验证码超时已失效！请重新获取");
            return responseResult;
        } else {
            responseResult.setCode(StatusCode.GET_SUCCESS.getCode());
            responseResult.setFailureMessage("验证码验证成功");
            return responseResult;
        }
    }
}
