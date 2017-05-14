package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.mapper.CompanyInfosMapper;
import com.ntq.baseMgr.mapper.CompanyPositionInfosMapper;
import com.ntq.baseMgr.mapper.JobSeekerInfosMapper;
import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.po.CompanyPositionInfosWithBLOBs;
import com.ntq.baseMgr.service.CompanyPositionInfoService;
import com.ntq.baseMgr.util.*;
import com.ntq.baseMgr.vo.CompanyPositionInfoVo;
import com.ntq.baseMgr.vo.JobSeekerInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>@description: 职位信息的接口实现</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service
 * @className:
 * @author: shuangyang
 * @date: 17-4-7 下午1:14
 */
@Service
public class CompanyPositionInfoServiceImpl implements CompanyPositionInfoService {
    @Autowired
    private CompanyPositionInfosMapper companyPositionInfosMapper;
    @Autowired
    private CompanyInfosMapper companyInfosMapper;
    @Autowired
    private JobSeekerInfosMapper jobSeekerInfosMapper;
    @Autowired
    private MailSenderServiceImpl mailSenderServiceImpl;//邮件发送服务

    /**
     * 添加新的职位信息
     *
     * @param companyPositionInfosWithBLOBs
     * @return
     */
    @Override
    public ResponseResult<Void> addCompanyPositionInfo(CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        //1.获取公司主键
//        CompanyInfos companyInfos = (CompanyInfos) SessionUtil.getSessionAttribute("companyInfo");//获取公司信息
//        if (null==companyInfos) {
//            responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
//            responseResult.setMessage("新增职位操作失败,当前用户未登录或者登录超时");
//            return responseResult;
//        }
//        Long companyInfoId = companyInfos.getId();
        Long companyInfoId = 1L;
        companyPositionInfosWithBLOBs.setCompanyInfosId(companyInfoId);
        //2.生成职位编号
        CreateSerialNo serialNo = new CreateSerialNo();
        Long positionNo = Long.valueOf(serialNo.getNum());
        companyPositionInfosWithBLOBs.setPositionNo(positionNo);
        //3.设置创建和更新时间
        Date currentDate = new Date();
        companyPositionInfosWithBLOBs.setServerCreateDate(currentDate);
        companyPositionInfosWithBLOBs.setServerUpdateDate(currentDate);
        companyPositionInfosWithBLOBs.setIsValid(1);//默认设置有效
        companyPositionInfosWithBLOBs.setPostionStatus(1);//默认待审核
        //todo 发布日期
        companyPositionInfosMapper.addCompanyPositionInfo(companyPositionInfosWithBLOBs);
        responseResult.setCode(StatusCode.INSERT_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.INSERT_SUCCESS.getMessage());
        return responseResult;
    }

    /**
     * 通过id查看职位信息
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult<CompanyPositionInfosWithBLOBs> getCompanyPositionInfoById(Long id) {
        ResponseResult<CompanyPositionInfosWithBLOBs> responseResult = new ResponseResult<>();
        responseResult.setData(companyPositionInfosMapper.getCompanyPositionInfoById(id));
        responseResult.setCode(StatusCode.GET_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.GET_SUCCESS.getMessage());
        return responseResult;
    }

    /**
     * 分页查询职位信息
     *
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<CompanyPositionInfoVo> queryCompanyPositionInfoListByCondition(Page<CompanyPositionInfoVo> page) throws Exception {
/*
        //添加查询条件
        CompanyInfos companyInfos = (CompanyInfos) SessionUtil.getSessionAttribute(ConstantUtil.COMPANY_INFOS);//获取公司信息
        //判断是否登录
        if (null == companyInfos) {//
            page.setSuccess(false);
        } else {
            //获取公司列表主键
            Long companyInfoId = companyInfos.getId();
            Map<String, Object> map = page.getParams();
            map.put(ConstantUtil.COMPANY_INFOS_ID, companyInfoId);//公司编号
            page.setParams(map);
            List<CompanyPositionInfoVo> companyPositionInfoVoList = companyPositionInfosMapper.queryCompanyPositionInfoListByCondition(page);
            page.setResults(companyPositionInfoVoList);
            page.setSuccess(true);
        }
*/


        /***********************测试数据*********************************/
        //获取公司列表主键
       // Long companyInfoId = 15l;
        Map<String, Object> map = page.getParams();
        //map.put(ConstantUtil.COMPANY_INFOS_ID, companyInfoId);//公司编号
        page.setParams(map);
        List<CompanyPositionInfoVo> companyPositionInfoVoList = companyPositionInfosMapper.queryCompanyPositionInfoListByCondition(page);
        page.setResults(companyPositionInfoVoList);
        page.setSuccess(true);
        return page;
    }

    @Override
    public ResponseResult<Void> updateOrInsertCompanyPositionInfo(CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        //1 获取职位状态
        int positionStatus = companyPositionInfosWithBLOBs.getPostionStatus();
        if (1 == positionStatus) {
            //更新操作
            companyPositionInfosMapper.updateCompanyPositionInfo(companyPositionInfosWithBLOBs);
            responseResult.setCode(StatusCode.UPDATE_SUCCESS.getCode());
            responseResult.setMessage("职位更新成功");
        } else {//创建新的职位

            //1.获取公司主键
            CompanyInfos companyInfos = (CompanyInfos) SessionUtil.getSessionAttribute("companyInfos");//获取公司信息
            Long companyInfoId = companyInfos.getId();
//            Long companyInfoId=1L;//测试用例
            companyPositionInfosWithBLOBs.setCompanyInfosId(companyInfoId);
            //2.生成职位编号 TODO
            CreateSerialNo createSerialNo = new CreateSerialNo();
            companyPositionInfosWithBLOBs.setPositionNo(Long.valueOf(createSerialNo.getNum()));
            //3.设置创建和更新时间
            Date currentDate = new Date();
            companyPositionInfosWithBLOBs.setServerCreateDate(currentDate);
            companyPositionInfosWithBLOBs.setServerUpdateDate(currentDate);
            companyPositionInfosWithBLOBs.setIsValid(1);//默认设置有效
            companyPositionInfosWithBLOBs.setPostionStatus(1);//默认待审核
            //todo 发布日期
            companyPositionInfosMapper.addCompanyPositionInfo(companyPositionInfosWithBLOBs);
            responseResult.setCode(StatusCode.INSERT_SUCCESS.getCode());
            responseResult.setMessage("新增职位成功");
        }

        return responseResult;
    }

    @Override
    public CompanyPositionInfos getTest() {
        Long id = 1l;
        return companyPositionInfosMapper.getCompanyPositionInfoById(id);
    }

    /**
     * 分页查询获取求职者list
     *
     * @param page
     * @return
     */
    @Override
    public Page<JobSeekerInfoVo> queryJobSeekerInfoVoList(Page<JobSeekerInfoVo> page) throws Exception {
        List<JobSeekerInfoVo> jobSeekerInfoVoList = jobSeekerInfosMapper.queryJobSeekerInfoVoList(page);
        page.setResults(jobSeekerInfoVoList);
        page.setSuccess(true);
        return page;
    }

    /**
     * 下架职位
     * 更新职位状态为 4-待下架
     *
     * @param positionId
     * @return
     */
    @Transactional
    @Override
    public ResponseResult<Void> withDrawCompanyPositionInfo(Long positionId) throws Exception{
        ResponseResult<Void> responseResult = new ResponseResult<>();
        //1.更新职位信息 将职位处理为等待下架的状态
        String message="职位下架请求";
        companyPositionInfosMapper.updateCompanyPositionInfoById(positionId,message, ConstantUtil.WAITING_WITHDRAW);
        //2.发送email 采用内推圈专用的邮箱
        CompanyInfos companyInfo = (CompanyInfos) SessionUtil.getSessionAttribute(ConstantUtil.COMPANY_INFOS);

//        测试数据 start
    /*    CompanyInfos companyInfo = new CompanyInfos();
        companyInfo.setCompanyName("雅堂");
        companyInfo.setCompanyPhone(123456l);
        companyInfo.setResumeMail("247677858@qq.com");
        companyInfo.setRecruiterName("杨爽");*/
        ////        测试数据 end

        //拼接向内推圈专用邮箱发送邮件信息请求下架
        StringBuilder stringBuilder = new StringBuilder();
        //现有xxx公司的hr杨某某，请求下架职位编号为xxx的职位，ta的联系电话为xxx,邮箱为xxx,微信为xxx,请与ta沟通核实
        String context=stringBuilder
                .append("现有")
                .append(companyInfo.getCompanyPhone())
                .append("公司的hr:").append(companyInfo.getRecruiterName())
                .append(",请求下架职位编号为:").append(positionId).append("的职位,ta的联系电话为：").append(companyInfo.getCompanyPhone())
                .append(",邮箱为").append(companyInfo.getResumeMail()).append(",请与ta沟通核实").toString();
        mailSenderServiceImpl.sendEmail2ntqEmail(context,message);
        responseResult.setCode(StatusCode.MAIL_SENDER_SUCCESS.getCode());
        responseResult.setMessage("已将下架信息发送给内推圈请等待通知");
        return responseResult;
    }

}