package com.ntq.baseMgr.service;


import com.ntq.baseMgr.po.JobSeekerResumeDelivery;

/**
 * <p>@description: 求职者求职简历相关信息</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service
 * @className:
 * @author: shuangyang
 * @date: 17-3-19 下午4:36
 */
public interface JobSeekerResumeDeliveryService extends BaseService<JobSeekerResumeDelivery,Long> {

    /**
     * 查询对应的求职者的简历信息的处理状态
     * @param jobSeekerId 求职者编号
     * @return
     */
    JobSeekerResumeDelivery getJobSeekerResumeDeliveryByJobSeekerId(Long jobSeekerId);
}
