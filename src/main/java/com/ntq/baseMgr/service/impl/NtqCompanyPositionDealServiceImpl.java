package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.mapper.CompanyInfosMapper;
import com.ntq.baseMgr.mapper.CompanyPositionInfosMapper;
import com.ntq.baseMgr.page.Page;
import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfos;
import com.ntq.baseMgr.po.CompanyPositionInfosWithBLOBs;
import com.ntq.baseMgr.po.MailBean;
import com.ntq.baseMgr.service.NtqCompanyPositionDealService;
import com.ntq.baseMgr.util.ConstantUtil;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import com.ntq.baseMgr.vo.CompanyPositionInfoExtVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>@description:内推圈公司职位处理Service接口实现 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service
 * @className:
 * @author: shuangyang
 * @date: 17-4-13 下午5:33
 */
@Service
public class NtqCompanyPositionDealServiceImpl implements NtqCompanyPositionDealService {

    @Autowired
    private CompanyPositionInfosMapper companyPositionInfosMapper;

    @Autowired
    private CompanyInfosMapper companyInfosMapper;
    @Autowired
    private MailSenderServiceImpl mailSenderServiceImpl;//邮件发送服务

    /**
     * 分页查询公司职位信息
     *
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<CompanyPositionInfoExtVo> queryCompanyPositionInfoVoListByCondition(Page<CompanyPositionInfoExtVo> page) throws Exception {
        List<CompanyPositionInfoExtVo> companyPositionInfoExtVoList = companyPositionInfosMapper.queryCompanyPositionInfoVoListByCondition(page);

        page.setResults(companyPositionInfoExtVoList);
        page.setSuccess(true);
        return page;
    }

    /**
     * 通过id编号获取公司信息
     *
     * @param companyInfoId 公司自增id
     * @return
     */
    @Override
    public ResponseResult<CompanyInfos> getCompanyInfoById(Long companyInfoId) throws Exception {
        ResponseResult<CompanyInfos> responseResult = new ResponseResult<>();
        responseResult.setCode(StatusCode.GET_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.GET_SUCCESS.getMessage());
        responseResult.setData(companyInfosMapper.getCompanyInfoById(companyInfoId));
        return responseResult;

    }

    /**
     * 通过id编号获取职位相关信息
     *
     * @param companyPositionInfoId 职位ID编号
     * @return
     */
    @Override
    public ResponseResult<CompanyPositionInfos> getCompanyPositionInfoById(Long companyPositionInfoId) throws Exception {
        ResponseResult<CompanyPositionInfos> responseResult = new ResponseResult<>();
        responseResult.setCode(StatusCode.GET_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.GET_SUCCESS.getMessage());
        responseResult.setData(companyPositionInfosMapper.getCompanyPositionInfoById(companyPositionInfoId));
        return responseResult;

    }

    @Override
    public ResponseResult<Void> updatePositionRemarkAndSendMail(Long positionId, String message) throws Exception {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        //1.更新职位的内容,拒绝发布  拒绝发布的编号为 3  rejectRelease//拒绝发布
        companyPositionInfosMapper.updateCompanyPositionInfoById(positionId, message,ConstantUtil.REJECT_RELEASE);
        //2.发送邮件
        //2.1 获取公司id todo 能否只是通过list从当前行获取
        CompanyPositionInfosWithBLOBs companyPositionInfo = companyPositionInfosMapper.getCompanyPositionInfoById(positionId);
        long companyInfoId = companyPositionInfo.getCompanyInfosId();
        //2.2 获取邮箱
        CompanyInfos companyInfos = companyInfosMapper.getCompanyInfoById(companyInfoId);
        String resumeMail = companyInfos.getResumeMail();
        //2.3发送邮件
        //System.out.println(resumPath);
        MailBean mailBean = new MailBean();
        mailBean.setToEmails(new String[]{resumeMail});
        mailBean.setSubject("简历修改");
        mailBean.setContext(message);
        mailSenderServiceImpl.sendMail(mailBean);
        responseResult.setCode(StatusCode.OK.getCode());
        responseResult.setMessage(StatusCode.OK.getMessage());
        return responseResult;
    }

    @Override
    public ResponseResult<Void> updateCompanyPositionInfo(Long positionId) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        companyPositionInfosMapper.updateCompanyPositionInfoById(positionId, ConstantUtil.REMARK, ConstantUtil.DONE_RELEASE);
        responseResult.setCode(StatusCode.UPDATE_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.UPDATE_SUCCESS.getMessage());
        return responseResult;
    }
}
