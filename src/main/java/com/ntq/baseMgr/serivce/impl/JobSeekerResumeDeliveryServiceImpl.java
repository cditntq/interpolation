package com.ntq.baseMgr.serivce.impl;

import com.ntq.baseMgr.dao.JobSeekerResumeDeliveryMapper;
import com.ntq.baseMgr.entity.JobSeekerResumeDelivery;
import com.ntq.baseMgr.service.JobSeekerResumeDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>@description: 求职者求职简历相关信息service实现</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.serivce.impl
 * @className: JobSeekerResumeDeliveryServiceImpl
 * @author: shuangyang
 * @date: 17-3-19 下午4:37
 */
@Service
public class JobSeekerResumeDeliveryServiceImpl extends BaseServiceImpl<JobSeekerResumeDelivery,Long> implements JobSeekerResumeDeliveryService{
    @Autowired
    private JobSeekerResumeDeliveryMapper jobSeekerResumeDeliveryMapper;
    @Autowired
    public void setBaseMapper() throws Exception {
        super.setBaseMapper(jobSeekerResumeDeliveryMapper);
    }


    /**
     * 查询对应的求职者的简历信息的处理状态
     * @param jobSeekerId 求职者编号
     * @return
     */
    @Override
    public JobSeekerResumeDelivery getJobSeekerResumeDeliveryByJobSeekerId(Long jobSeekerId) {
        return jobSeekerResumeDeliveryMapper.getJobSeekerResumeDeliveryByJobSeekerId(jobSeekerId);
    }
}
