package com.ntq.baseMgr.mapper;

import com.ntq.baseMgr.po.JobSeekerResumeDelivery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerResumeDeliveryMapper {

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

    void updateResumeDeliveryDealStatus(@Param(value = "resumeDeliveryId") long resumeDeliveryId, @Param(value = "dealStatus") int dealStatus);

    /**
     * 插入新求职者的简历相关信息
     * @param delivery
     */
    void insertJobSeekerResumDelivery(JobSeekerResumeDelivery delivery);
}