package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.mapper.CompanyInfosMapper;
import com.ntq.baseMgr.mapper.CompanyPositionInfosMapper;
import com.ntq.baseMgr.mapper.MessageValidateRecordMapper;
import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfosWithBLOBs;
import com.ntq.baseMgr.po.MessageValidateRecord;
import com.ntq.baseMgr.service.CompanyInfoService;
import com.ntq.baseMgr.util.CreateSerialNo;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.util.StringUtil;
import com.ntq.baseMgr.vo.CompanyInfoWithPositionInfoListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <p>@description:公司信息Service接口实现 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service.impl
 * @className:
 * @author: shuangyang
 * @date: 17-4-3 下午5:42
 */
@Service
public class CompanyInfosServiceImpl implements CompanyInfoService {

    @Autowired
    private CompanyInfosMapper companyInfosMapper;//公司Dao接口
    @Autowired
    private CompanyPositionInfosMapper companyPositionInfoMapper;//公司职位Dao接口
    @Autowired
    private MessageValidateRecordMapper messageValidateRecordMapper;//验证码Dao接口

    /**
     * 新增公司信息录入
     *
     * @param companyInfos
     * @return
     */
    @Override
    public ResponseResult<Void> addCompanyInfos(CompanyInfos companyInfos) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        companyInfos.setServerCreateDate(new Date());
        companyInfos.setServerUpdateDate(new Date());
        companyInfosMapper.addCompanyInfos(companyInfos);
        responseResult.setCode(StatusCode.INSERT_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.INSERT_SUCCESS.getMessage());
        return responseResult;
    }

    /**
     * 通过id编号获取公司信息
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult<CompanyInfos> getJobSeekerInfoVoById(Long id) throws Exception {
        ResponseResult<CompanyInfos> responseResult = new ResponseResult<>();
        responseResult.setData(companyInfosMapper.getJobSeekerInfoVoById(id));
        responseResult.setCode(StatusCode.GET_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.GET_SUCCESS.getMessage());
        return responseResult;
    }

    /**
     * 分页查询求职者信息
     *
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<CompanyInfos> queryCompanyInfoListByCondition(Page<CompanyInfos> page) throws Exception {
        List<CompanyInfos> jobSeekerInfosExtDtos = companyInfosMapper.queryCompanyInfoListByCondition(page);
        page.setResults(jobSeekerInfosExtDtos);
        page.setSuccess(true);
        return page;
    }

    /**
     * 根据id批量删除求职者个人信息包括简历
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<Void> deleteCompanyInfoListByIds(String ids) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        //解析字符串
        List<Long> idList = StringUtil.idsStr2List(ids);
        //1.逻辑删除
        companyInfosMapper.deleteCompanyInfoListByIds(idList);
        responseResult.setCode(StatusCode.DELETE_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.DELETE_SUCCESS.getMessage());
        return responseResult;
    }

    /**
     * 公司信息更新
     *
     * @param companyInfos 公司实体
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<Void> updateCompanyInfos(CompanyInfos companyInfos) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        /*设置更新时间*/
        companyInfos.setServerUpdateDate(new Date());
        companyInfosMapper.updateCompanyInfos(companyInfos);
        responseResult.setCode(StatusCode.UPDATE_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.UPDATE_SUCCESS.getMessage());
        return responseResult;
    }

    @Override
    public ResponseResult<Void> addCompanyInfoWithPositionInfoList(CompanyInfoWithPositionInfoListVo companyInfoWithPositionInfoListVo) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        /*1.设置创建和更新时间*/
        Date currentDate = new Date();
        //设置公司
        CompanyInfos companyInfo = companyInfoWithPositionInfoListVo.getCompanyInfo();
        companyInfo.setServerCreateDate(currentDate);
        companyInfo.setServerUpdateDate(currentDate);
        companyInfo.setIsValid(1);//设置默认有效
        // 1.插入公司信息并获取返回的主键
        companyInfosMapper.insertAndGetKey(companyInfo);
        Long companyInfoId = companyInfo.getId();
        //2.插入职位信息
        List<CompanyPositionInfosWithBLOBs> companyPositionInfosWithBlobList = companyInfoWithPositionInfoListVo.getCompanyPositionInfosWithBlobList();
        //2.1 设置职位创建和更新时间以及职位编号
        CreateSerialNo serialNo = new CreateSerialNo();//用于生成职位编号
        for (CompanyPositionInfosWithBLOBs companyPositionInfo : companyPositionInfosWithBlobList) {
            companyPositionInfo.setServerUpdateDate(currentDate);
            companyPositionInfo.setServerCreateDate(currentDate);
            companyPositionInfo.setCompanyInfosId(companyInfoId);//设置关联的公司信息的主表id
            companyPositionInfo.setIsValid(1);//设置默认有效
            companyPositionInfo.setPostionStatus(1);
            //生成职位编号
            companyPositionInfo.setPositionNo(Long.valueOf(serialNo.getNum()));

        }
        //2.2 批量插入
        companyPositionInfoMapper.insertByBatch(companyPositionInfosWithBlobList);
        responseResult.setCode(StatusCode.INSERT_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.INSERT_SUCCESS.getMessage());
        return responseResult;
    }


    @Override
    public ResponseResult<Void> verifyMessageCode(HttpSession session, Long phoneNumber, String verifyCode) {

        ResponseResult<Void> responseResult = new ResponseResult<>();
        //1.匹配验证码
        MessageValidateRecord messageValidateRecord = messageValidateRecordMapper.getMessageValidateRecord(phoneNumber, verifyCode);
        //验证码匹配失败
        if (null == messageValidateRecord) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("验证码输入错误");
            return responseResult;
        }
        //2.查找公司信息有无与该号码匹配的的公司
        CompanyInfos companyInfo = companyInfosMapper.getCompanyInfoByPhoneNo(phoneNumber);
        System.out.println(companyInfo.toString());
        if (companyInfo != null) {
            session.setAttribute("companyInfo", companyInfo);
            //转跳到 到职位信息的列表
            responseResult.setCode(StatusCode.OK.getCode());
            responseResult.setMessage(StatusCode.OK.getMessage());
        } else {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage("没有与该手机号匹配的公司！请认真核实");
        }
        return responseResult;
    }

    @Override
    public CompanyInfos getTest() {
        Long phoneNo = 15123247202L;
        return companyInfosMapper.getCompanyInfoByPhoneNo(phoneNo);
    }
}
