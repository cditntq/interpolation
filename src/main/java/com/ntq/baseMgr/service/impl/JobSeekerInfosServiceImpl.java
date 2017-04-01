package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.mapper.JobSeekerInfosMapper;
import com.ntq.baseMgr.po.JobSeekerInfosExtDto;
import com.ntq.baseMgr.po.JobSeekerResumeDelivery;
import com.ntq.baseMgr.service.IUploadFileService;
import com.ntq.baseMgr.service.JobSeekerResumeDeliveryService;
import com.ntq.baseMgr.mapper.JobSeekerResumeDeliveryMapper;
import com.ntq.baseMgr.po.JobSeekerInfos;
import com.ntq.baseMgr.po.JobSeekerInfosVo;
import com.ntq.baseMgr.service.JobSeekerInfosService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.vo.UploadFileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>@description: 求职者信息处理Service</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.serivce.impl
 * @className:
 * @author: shuangyang
 * @date: 17-3-19 下午2:37
 */
@Service
public class JobSeekerInfosServiceImpl extends BaseServiceImpl<JobSeekerInfos, Long> implements JobSeekerInfosService {

    private static Logger logger = LoggerFactory.getLogger(JobSeekerInfosServiceImpl.class);
    @Autowired
    private JobSeekerInfosMapper jobSeekerInfosMapper;
    @Autowired
    private JobSeekerResumeDeliveryMapper jobSeekerResumeDeliveryMapper;
    @Autowired
    private IUploadFileService uploadFileService;//上传文件的service
    @Autowired
    private JobSeekerResumeDeliveryService jobSeekerResumeDeliveryService;//处理上传附件相关信息

    @Autowired
    public void setBaseMapper() throws Exception {
        super.setBaseMapper(jobSeekerInfosMapper);
    }


    public void insertJobSeekerInfo(JobSeekerInfosVo jobSeekerInfosVo, UploadFileVo vo, HttpServletRequest request) throws Exception {
        //录入求职者信息
        //1.拼接简历名称 "username_"+"职位编码.doc"
        vo.setName(jobSeekerInfosVo.getJobSeekerName() + "_" + jobSeekerInfosVo.getJobCode() + ".doc");
        //2.上传附件处理并返回存储路径
        String storePath = uploadFileService.uploadForm(vo);
        //3.插入用户信息并返回id
        // insertAndGetKey(jobSeekerInfosVo);
        Long insertKey = jobSeekerInfosVo.getId();
        //4.存储附件相关信息
        JobSeekerResumeDelivery delivery = new JobSeekerResumeDelivery();
        delivery.setIsValid(true);
        delivery.setResumePath(storePath);
        delivery.setJobSeekerInfosId(insertKey);
        delivery.setServerCreateDate(new Date());
        delivery.setServerUpdateDate(new Date());
        delivery.setJobCode(jobSeekerInfosVo.getJobCode());
        delivery.setDealStatus(1);//默认处理状态
        jobSeekerResumeDeliveryService.insert(delivery);
    }

    @Override
    public ResponseResult<List<JobSeekerInfosExtDto>> queryJobSeekerInfosListByCondition(int page, int size, String whereCondition) {
        ResponseResult<List<JobSeekerInfosExtDto>> responseResult = new ResponseResult<>();
        try {

            //1剪切拼接查询条件
            Map<String, Object> map = new HashMap();
            //1.1 拼接
            String[] split = whereCondition.split("&");
            for (String s : split) {
                String[] keyAndValue = s.split("=");
                if (keyAndValue.length > 1) {
                    switch (keyAndValue[0]) {
                        case "jobCode":
                            map.put("job_code", keyAndValue[1]);
                            break;
                        case "jobSeekerPhone":
                            map.put("job_seeker_phone", keyAndValue[1]);
                            break;
                        case "dealStatus":
                            map.put("deal_status",keyAndValue[1]);
                            break;
                        default:
                            break;
                    }

                }
            }
            List<JobSeekerInfosExtDto> jobSeekerInfosExtDtos = jobSeekerInfosMapper.queryJobSeekerInfosListByCondition((page - 1) * size, page * size, map);
            responseResult.setData(jobSeekerInfosExtDtos);
            responseResult.setCode(StatusCode.OK.getCode());
            responseResult.setMessage(StatusCode.OK.getMessage());
        } catch (Exception e) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage(StatusCode.Fail.getMessage());
        }
        return responseResult;
    }

    @Override
    public ResponseResult<String> deleteBatchJobSeekerInfoList(String ids) {
        ResponseResult<String> responseResult = new ResponseResult<>();
        //解析字符串
        List<Long> idList = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        try {
            //逻辑删除用户主表以及附表信息 todo 目前没有实现在同一个mybatis里面两个更新语句
            //1.删除主表信息
            jobSeekerInfosMapper.deleteBatchJobSeekerInfoListAndResumeDelivery(idList);
            //2.删除附件信息
            jobSeekerResumeDeliveryMapper.deleteBatchJobSeekerResumeDeliveryList(idList);
            responseResult.setData(Boolean.TRUE.toString());
            responseResult.setCode(StatusCode.OK.getCode());
            responseResult.setMessage(StatusCode.OK.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setData(Boolean.FALSE.toString());
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage(StatusCode.Fail.getMessage());
        }
        return responseResult;
    }

    /**
     * 插入求职者个人信息并返回key
     *
     * @param record
     * @return
     */
    @Override
    public Long insertAndReturnKey(JobSeekerInfos record) {
        record.setServerCreateDate(new Date());
        record.setServerUpdateDate(new Date());
        record.setIsValid(true);//默认设置信息有效
        return jobSeekerInfosMapper.insertAndGetKey(record);
    }


/*
    */
/**
 * 分页查询求职者信息
 *
 * @param page           页码
 * @param size           分页大小
 * @param whereCondition 查询条件
 * @return
 *//*

    @Override
    public List<JobSeekerInfos> queryJobSeekerInfosListByCondition(int page, int size, String whereCondition) {
        return jobSeekerInfosMapper.queryJobSeekerInfosListByCondition((page - 1) * size, page * size, whereCondition);
    }
*/

    /**
     * 通过求职者信息id查看投递简历相信信息
     *
     * @param id
     * @return
     */

    public ResponseResult<JobSeekerInfosVo> getJobSeekerInfoVoById(Long id) {
        ResponseResult<JobSeekerInfosVo> responseResult = new ResponseResult<>();
        try {
         /*   Map<String, JobSeekerInfosVo> map = new HashMap<>();*/
            //1.查询求职者的个人相关信息
            JobSeekerInfosVo jobSeekerInfosVo = (JobSeekerInfosVo) jobSeekerInfosMapper.getJobSeekerInfoById(id);
            //2.查询对应的求职者的简历信息的处理状态
            JobSeekerResumeDelivery jobSeekerResumeDelivery = jobSeekerResumeDeliveryService.getJobSeekerResumeDeliveryByJobSeekerId(id);
            //获取是否有效
            jobSeekerInfosVo.setIsValid(jobSeekerResumeDelivery.getIsValid());
            //获取存储路径
            jobSeekerInfosVo.setResumePath(jobSeekerResumeDelivery.getResumePath());
            //获取处理状态
            jobSeekerInfosVo.setDealStatus(jobSeekerResumeDelivery.getDealStatus());
            //封装返回信息
            //  responseResult.setData(map);
            responseResult.setCode(StatusCode.OK.getCode());
            responseResult.setMessage(StatusCode.OK.getMessage());
        } catch (Exception e) {
            responseResult.setCode(StatusCode.Fail.getCode());
            responseResult.setFailureMessage(StatusCode.Fail.getMessage());
        }
        return responseResult;
    }
    /**
     * 更新简历状态
     * @param resumeDeliveryId 传递简历的id
     * @param dealStatus 处理状态
     * @return
     */
    @Override
    public ResponseResult<String> updateResumeDeliveryDealStatus(long resumeDeliveryId, int dealStatus) {
        ResponseResult<String>  rep=new ResponseResult<>();
        try {
            jobSeekerResumeDeliveryMapper.updateResumeDeliveryDealStatus(resumeDeliveryId,dealStatus);
            rep.setCode(StatusCode.OK.getCode());
            rep.setMessage(StatusCode.OK.getMessage());
        }catch (Exception e){
            rep.setCode(StatusCode.Fail.getCode());
            rep.setFailureMessage(StatusCode.Fail.getMessage());
        }
        return rep;
    }
}
