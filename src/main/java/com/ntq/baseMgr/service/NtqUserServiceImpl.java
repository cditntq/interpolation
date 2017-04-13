package com.ntq.baseMgr.service;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.vo.CompanyPositionInfoExtVo;
import org.springframework.stereotype.Service;

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
public class NtqUserServiceImpl implements NtqUserService{
    /**
     * 分页查询公司职位信息
     *
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<CompanyPositionInfoExtVo> queryCompanyInfoVoListByCondition(Page<CompanyPositionInfoExtVo> page) {
        return null;
    }

    /**
     * 通过id编号获取公司信息
     *
     * @param id 公司自增id
     * @return
     */
    @Override
    public ResponseResult<CompanyInfos> getCompanyInfoById(Long id) {
        return null;
    }

    /**
     * 通过id编号获取职位相关信息
     *
     * @param id 职位ID编号
     * @return
     */
    @Override
    public ResponseResult<CompanyPositionInfos> getCompanyPositionInfoById(Long id) {
        return null;
    }
}
