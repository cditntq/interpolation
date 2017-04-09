package com.ntq.baseMgr.mapper;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.po.CompanyPositionInfosWithBLOBs;

import java.util.List;

public interface CompanyPositionInfosMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyPositionInfosWithBLOBs record);

    int insertSelective(CompanyPositionInfosWithBLOBs record);

    CompanyPositionInfosWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyPositionInfosWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CompanyPositionInfosWithBLOBs record);

    int updateByPrimaryKey(CompanyPositionInfos record);

    /**
     * 批量插入职位信息
     * @param companyPositionInfosWithBLOBsList
     */
    void insertByBatch(List<CompanyPositionInfosWithBLOBs> companyPositionInfosWithBLOBsList);

    /**
     * 添加进职位
     * @param companyPositionInfosWithBLOBs
     */
    void addCompanyPositionInfo(CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs);
    /**
     * 通过id查看职位信息
     *
     * @param id
     * @return
     */
    CompanyPositionInfosWithBLOBs getCompanyPositionInfoById(Long id);
    /**
     * 分页查询职位信息
     *
     * @param page 分页对象
     * @return
     */
    List<CompanyPositionInfos> queryCompanyPositionInfoListByCondition(Page<CompanyPositionInfos> page);

    /**
     * 更新职位信息
     * @param companyPositionInfosWithBLOBs
     */
    void updateCompanyPositionInfo(CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs);
}