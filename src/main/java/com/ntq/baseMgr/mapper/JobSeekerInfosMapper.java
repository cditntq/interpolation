package com.ntq.baseMgr.mapper;

import com.ntq.baseMgr.po.JobSeekerInfos;
import com.ntq.baseMgr.po.JobSeekerInfosExtDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface JobSeekerInfosMapper extends BaseMapper<JobSeekerInfos,Long> {
    /**
     * 插入求职者个人信息并返回key
     * @param record
     * @return
     */
    Long insertAndGetKey(JobSeekerInfos record);

    /**
     * 分页查询求职者信息
     * @param start 起始行下表
     * @param end 最终
     * @param params 查询条件
     * @return
     */
    List<JobSeekerInfosExtDto> queryJobSeekerInfosListByCondition(@Param(value = "start") int start, @Param(value = "end") int end, @Param("params") Map<String, Object> params);

    /**
     * 通过求职者信息id查看投递简这的个人信息
     * @param id
     * @return
     */
    JobSeekerInfos getJobSeekerInfoById(Long id);

    /**
     * 逻辑删除用户以及其对应的简历附件
     * @param ids
     */

    void deleteBatchJobSeekerInfoListAndResumeDelivery(List<Long> ids);
}