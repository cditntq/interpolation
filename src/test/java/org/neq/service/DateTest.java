package org.neq.service;

import com.ntq.baseMgr.po.CompanyInfos;

/**
 * <p>@description: </p>
 *
 * @projectName: interpolation
 * @packageName: org.neq.service
 * @className:
 * @author: shuangyang
 * @date: 17-4-16 下午5:56
 */
public class DateTest {
    public static void main(String[] args) {
        CompanyInfos companyInfo = new CompanyInfos();
        companyInfo.setCompanyName("雅堂");
        companyInfo.setCompanyPhone(123456l);
        companyInfo.setResumeMail("247677858@qq.com");
        companyInfo.setRecruiterName("杨爽");
        StringBuilder stringBuilder = new StringBuilder();
        String context=stringBuilder
                .append("现有")
                .append(companyInfo.getCompanyName())
                .append("公司的hr:").append(companyInfo.getRecruiterName())
                .append(",请求下架职位编号为:").append("12345").append("的职位,ta的联系电话为：").append(companyInfo.getCompanyPhone())
                .append(",邮箱为").append(companyInfo.getResumeMail()).append(",请与ta沟通核实").toString();
        System.out.println(context);
        //mailSenderServiceImpl.sendEmail2ntqEmail();
    }
}
