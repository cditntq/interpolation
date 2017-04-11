package com.ntq.baseMgr.service.impl;


import com.ntq.baseMgr.po.CompanyInfos;
import com.ntq.baseMgr.po.CompanyPositionInfosWithBLOBs;
import com.ntq.baseMgr.service.CompanyInfoService;
import com.ntq.baseMgr.service.MessageValidateRecordService;
import com.ntq.baseMgr.vo.CompanyInfoWithPositionInfoListVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>@description: CompanyInfoServiceImplTest</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service.impl
 * @className:
 * @author: shuangyang
 * @date: 17-4-11 下午1:51
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CompanyInfosServiceImplTest {

    @Autowired
    private CompanyInfoService companyInfosServiceImpl;
    @Autowired
    private MessageValidateRecordService messageValidateRecordService;


    /**
     * 新录入公司录入信息和此时发布的职位
     */
    @Test
    public void addCompanyInfoWithPositionInfoList() {
      /* jobSeekerInfosService.resumeFeedBack("247677858@qq.com","简历可能有问题——测试");*/

        try {
            CompanyInfos companyInfos = new CompanyInfos();
            companyInfos.setCompanyName("新的测试");
            companyInfos.setCompanyPhone(15123247206L);
            companyInfos.setCompanySynopsis("公司简介");
            companyInfos.setRecruiterName("杨爽");
            companyInfos.setRecruitType(1);
            companyInfos.setCompanyType(1);
            companyInfos.setResumeMail("247677858@qq.com");
            companyInfos.setContactWeixinId("weChat");
            companyInfos.setFinancingType(1);
            companyInfos.setIsNewlyEstablished(1);


            CompanyPositionInfosWithBLOBs companyPositionInfosWithBLOBs = new CompanyPositionInfosWithBLOBs();
            companyPositionInfosWithBLOBs.setPositionDesc("职位信息");
            companyPositionInfosWithBLOBs.setPositionName("高级开发");
            companyPositionInfosWithBLOBs.setPositionType(1);//职位类型
            companyPositionInfosWithBLOBs.setQualificationsType(1);//教育水平
            companyPositionInfosWithBLOBs.setLowWorkingLife(1);//职位最低年限
            companyPositionInfosWithBLOBs.setHighWorkingLife(3);//职位最高年限
            companyPositionInfosWithBLOBs.setHighSalary(new BigDecimal(300));
            companyPositionInfosWithBLOBs.setLowSalary(new BigDecimal(200));
            companyPositionInfosWithBLOBs.setWorkAddress("天府新区xxx大道");
            companyPositionInfosWithBLOBs.setPositionRequirements("具备xxx技能,从入门到放弃");
            companyPositionInfosWithBLOBs.setIsDiscussPersonally(1);//工资面议与否

            List<CompanyPositionInfosWithBLOBs> list = new ArrayList<>();
            list.add(companyPositionInfosWithBLOBs);
            CompanyInfoWithPositionInfoListVo companyInfoWithPositionInfoListVo = new CompanyInfoWithPositionInfoListVo();
            companyInfoWithPositionInfoListVo.setCompanyInfo(companyInfos);
            companyInfoWithPositionInfoListVo.setCompanyPositionInfosWithBlobList(list);
            companyInfosServiceImpl.addCompanyInfoWithPositionInfoList(companyInfoWithPositionInfoListVo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 用于第三方接口插入测试
     */
    @Test
    public void getMessageValidateRecord(){

        try {
   /*         MessageValidateRecord messageValidateRecord = new MessageValidateRecord();
            messageValidateRecord.setPhoneNum(15123247206L);
            messageValidateRecord.setToken("123456");
            messageValidateRecord.setValideTime(new Date());
            messageValidateRecord.setSendSuccess(1);*/
            //companyInfosServiceImpl.verifyMessageCode(15123247206l,123456);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
