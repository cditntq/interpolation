package com.ntq.baseMgr.dao;

import com.ntq.baseMgr.entity.JobSeekerResumeDelivery;

import java.util.List;

public interface JobSeekerResumeDeliveryMapper extends BaseMapper<JobSeekerResumeDelivery,Long> {

    /**
     * 查询对应的求职者的简历信息的处理状态
     * @param jobSeekerId 求职者编号
     * @return
     */
    JobSeekerResumeDelivery getJobSeekerResumeDeliveryByJobSeekerId(Long jobSeekerId);

    /**
     * 逻辑删除附件信息 is_valid=2 失效
     * @param ids
     */
    void deleteBatchJobSeekerResumeDeliveryList(List<Long> ids);
}