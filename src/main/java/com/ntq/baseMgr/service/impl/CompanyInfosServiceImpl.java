package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.mapper.CompanyInfosMapper;
import com.ntq.baseMgr.mapper.JobSeekerInfosMapper;
import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.service.CompanyInfoService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
public class CompanyInfosServiceImpl extends BaseServiceImpl<CompanyInfos, Long> implements CompanyInfoService {

    @Autowired
    private CompanyInfosMapper companyInfosMapper;

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
}
