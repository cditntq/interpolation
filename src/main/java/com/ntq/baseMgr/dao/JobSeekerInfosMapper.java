package com.ntq.baseMgr.dao;

import com.ntq.baseMgr.entity.JobSeekerInfos;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface JobSeekerInfosMapper extends BaseMapper<JobSeekerInfos,Long> {
    /**
     * 插入求职者个人信息并返回key
     * @param record
     * @return
     */
    Long insertAndReturnKey(JobSeekerInfos record);

    /**
     * 分页查询求职者信息
     * @param start 起始行下表
     * @param end 最终
     * @param whereCondition 查询条件
     * @return
     */
    List<JobSeekerInfos> queryJobSeekerInfosListByCondition(@Param(value = "start") int start, @Param(value = "end") int end, @Param(value = "whereCondition") String whereCondition);

    /**
     * 通过求职者信息id查看投递简这的个人信息
     * @param id
     * @return
     */
    JobSeekerInfos getJobSeekerInfoById(Long id);
}