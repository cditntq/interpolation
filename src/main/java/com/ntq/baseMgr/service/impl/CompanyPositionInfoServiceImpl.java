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
        CompanyInfos companyInfos = (CompanyInfos) SessionUtil.getSessionAttribute("companyInfo");//获取公司信息
        Long companyInfoId = companyInfos.getId();
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


        /***********************测试数据*********************************/
   /*     //获取公司列表主键
        Long companyInfoId = 1l;
        Map<String, Object> map = page.getParams();
        map.put(ConstantUtil.COMPANY_INFOS_ID, companyInfoId);//公司编号
        page.setParams(map);
        List<CompanyPositionInfoVo> companyPositionInfoVoList = companyPositionInfosMapper.queryCompanyPositionInfoListByCondition(page);
        page.setResults(companyPositionInfoVoList);
        page.setSuccess(true);*/
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
            //companyPositionInfosMapper.addCompanyPositionInfo(companyPositionInfosWithBLOBs);
            responseResult.setCode(StatusCode.UPDATE_SUCCESS.getCode());
            responseResult.setMessage(StatusCode.UPDATE_SUCCESS.getMessage());
        } else {//创建新的职位

            //1.获取公司主键
            CompanyInfos companyInfos = (CompanyInfos) SessionUtil.getSessionAttribute("companyInfos");//获取公司信息
            Long companyInfoId = companyInfos.getId();
            companyPositionInfosWithBLOBs.setCompanyInfosId(companyInfoId);
            //2.生成职位编号 TODO
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


}
