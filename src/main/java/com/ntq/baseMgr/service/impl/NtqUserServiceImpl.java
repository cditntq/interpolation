package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.mapper.CompanyInfosMapper;
import com.ntq.baseMgr.mapper.CompanyPositionInfosMapper;
import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.service.NtqUserService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.vo.CompanyPositionInfoExtVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>@description:内推圈Service接口实现 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service
 * @className:
 * @author: shuangyang
 * @date: 17-4-13 下午5:33
 */
@Service
public class NtqUserServiceImpl implements NtqUserService {

    @Autowired
    private CompanyPositionInfosMapper companyPositionInfosMapper;

    @Autowired
    private CompanyInfosMapper companyInfosMapper;

    /**
     * 分页查询公司职位信息
     *
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<CompanyPositionInfoExtVo> queryCompanyPositionInfoVoListByCondition(Page<CompanyPositionInfoExtVo> page) throws Exception{
        List<CompanyPositionInfoExtVo> companyPositionInfoExtVoList = companyPositionInfosMapper.queryCompanyPositionInfoVoListByCondition(page);
        page.setResults(companyPositionInfoExtVoList);
        page.setSuccess(true);
        return page;
    }

    /**
     * 通过id编号获取公司信息
     *
     * @param companyInfoId 公司自增id
     * @return
     */
    @Override
    public ResponseResult<CompanyInfos> getCompanyInfoById(Long companyInfoId) throws Exception{
        ResponseResult<CompanyInfos> responseResult = new ResponseResult<>();
        responseResult.setCode(StatusCode.GET_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.GET_SUCCESS.getMessage());
        responseResult.setData(companyInfosMapper.getCompanyInfoById(companyInfoId));
        return responseResult;

    }

    /**
     * 通过id编号获取职位相关信息
     *
     * @param companyPositionInfoId 职位ID编号
     * @return
     */
    @Override
    public ResponseResult<CompanyPositionInfos> getCompanyPositionInfoById(Long companyPositionInfoId) throws Exception{
        ResponseResult<CompanyPositionInfos> responseResult = new ResponseResult<>();
        responseResult.setCode(StatusCode.GET_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.GET_SUCCESS.getMessage());
        responseResult.setData(companyPositionInfosMapper.getCompanyPositionInfoById(companyPositionInfoId));
        return responseResult;

    }
}
