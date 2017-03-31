package com.ntq.baseMgr.service;

import com.ntq.baseMgr.entity.JobSeekerInfos;
import com.ntq.baseMgr.vo.JobSeekerInfosVo;
import com.ntq.baseMgr.vo.UploadFileVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>@description: 求职者信息的service</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service
 * @className:
 * @author: shuangyang
 * @date: 17-3-19 下午2:35
 */
public interface JobSeekerInfosService extends BaseService<JobSeekerInfos,Long>{

    /**
     * 插入求职者个人信息并返回key
     * @param record
     * @return
     *//*
     Long insertAndReturnKey(JobSeekerInfos record);
*/
    /**
     * 录入求职者个人信息以及简历
     * @param jobSeekerInfosVo
     * @param vo
     * @param request
     */
    void insertJobSeekerInfo(JobSeekerInfosVo jobSeekerInfosVo, UploadFileVo vo, HttpServletRequest request) throws Exception;

    /**
     * 分页查询求职者信息
     * @param page 页码
     * @param size  分页大小
     * @param whereCondition 查询条件
     * @return
     */
    List<JobSeekerInfos> queryJobSeekerInfosListByCondition(int page, int size, String whereCondition);

    /**
     * 通过求职者信息id查看投递简历相信信息
     * @param id
     * @return
     */
    JobSeekerInfosVo getJobSeekerInfoVoById(Long id);

    /**
     * 批量删除求职者个人信息以及附件信息
     * @param ids
     * @return
     */
    @Override
    int deleteBatch(String ids);
}
