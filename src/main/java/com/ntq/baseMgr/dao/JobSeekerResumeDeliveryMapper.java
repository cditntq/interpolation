package com.ntq.baseMgr.dao;

import com.ntq.baseMgr.entity.JobSeekerResumeDelivery;

public interface JobSeekerResumeDeliveryMapper extends BaseMapper<JobSeekerResumeDelivery,Long> {

    /**
     * 查询对应的求职者的简历信息的处理状态
     * @param jobSeekerId 求职者编号
     * @return
     */
    JobSeekerResumeDelivery getJobSeekerResumeDeliveryByJobSeekerId(Long jobSeekerId);
}