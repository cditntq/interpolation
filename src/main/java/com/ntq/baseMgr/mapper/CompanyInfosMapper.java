package com.ntq.baseMgr.mapper;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;

import java.util.List;

public interface CompanyInfosMapper{


    /**
     * 新增公司信息录入
     *
     * @param companyInfos
     * @return
     */
    void addCompanyInfos(CompanyInfos companyInfos);
    /**
     * 通过id编号获取公司信息
     *
     * @param id
     * @return
     */
    CompanyInfos getJobSeekerInfoVoById(Long id);
    /**
     * 分页查询求职者信息
     *
     * @param page 分页对象
     * @return
     */
    List<CompanyInfos> queryCompanyInfoListByCondition(Page<CompanyInfos> page);
    /**
     * 根据id批量删除求职者个人信息包括简历
     *
     * @param ids
     * @return
     */
    void deleteCompanyInfoListByIds(List<Long> ids);
    /**
     * 公司信息更新
     *
     * @param companyInfos 公司实体
     * @return
     */
    void updateCompanyInfos(CompanyInfos companyInfos);

    /**
     * 插入并返回主键
     * @param record
     * @return
     */
    Long insertAndGetKey(CompanyInfos record);

    /**
     * 通过电话号码拿到公司细细你
     * @param companyPhone
     * @return
     */
    CompanyInfos getCompanyInfoByPhoneNo(Long companyPhone);
}