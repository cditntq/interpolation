package com.ntq.baseMgr.mapper;

import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.po.CompanyPositionInfosWithBLOBs;
import com.ntq.baseMgr.vo.CompanyPositionInfoExtVo;
import com.ntq.baseMgr.vo.CompanyPositionInfoVo;
import com.ntq.baseMgr.vo.JobSeekerPositionDealVo;
import com.ntq.baseMgr.vo.JobSeekerPositionDetailVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyPositionInfosMapper {

    int insert(CompanyPositionInfosWithBLOBs record);


    CompanyPositionInfosWithBLOBs selectByPrimaryKey(Long id);


    /**
     * 批量插入职位信息
     *
     * @param companyPositionInfosWithBLOBsList
     */
    void insertByBatch(List<CompanyPositionInfosWithBLOBs> companyPositionInfosWithBLOBsList);

    /**
     * 添加进职位
     *
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
    List<CompanyPositionInfoVo> queryCompanyPositionInfoListByCondition(Page<CompanyPositionInfoVo> page);

    /**
     * 更新职位信息
     *
     * @param companyPositionInfosWithBLOBs
     */
    void updateCompanyPositionInfo(CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs);
    /**
     * 分页查询公司职位信息
     *
     * @param page 分页对象
     * @return
     */
    List<CompanyPositionInfoExtVo> queryCompanyPositionInfoVoListByCondition(Page<CompanyPositionInfoExtVo> page);

    /**
     * 更新职位信息  后面封装为一个类参数
     * @param positionId
     * @param message
     * @param dealStatus 处理状态
     */
    void updateCompanyPositionInfoById(@Param(value = "positionId") Long positionId, @Param(value = "message") String message, @Param(value = "dealStatus")Integer dealStatus);
    /**
     * 求职者投递岗位分页查询
     *
     * @param page 分页参数
     * @return
     */
    List<JobSeekerPositionDetailVo> queryJobSeekerPositionVoList(Page<JobSeekerPositionDetailVo> page);

    /**
     * 通过职位编号查询职位信息
     * @param companyPositionNo 职位编号
     * @return 职位信息实体
     */
    CompanyPositionInfos getCompanyPositionInfoByPositionNo(Long companyPositionNo);
}