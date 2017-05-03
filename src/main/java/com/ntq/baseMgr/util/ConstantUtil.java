package com.ntq.baseMgr.util;

/**
 * <p>@description： 记录一些常量</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.util
 * @className:
 * @author: shuangyang
 * @date: 17-4-13 上午11:30
 */
public class ConstantUtil {
    //隐藏构造器
    private ConstantUtil() {
    }

    /***************session start**********************/
    public final static String COMPANY_INFOS = "companyInfos";
    public final static String JOBSEEKER_INFOS = "jobSeekerInfos";
    /***************session end**********************/
    //公司id编号
    public final static String COMPANY_INFOS_ID = "companyInfosId";

    //公司id编号
    public final static String REMARK = "已发布";
    /************************职位的状态start**************************/
    public final static Integer REJECT_RELEASE=3;//拒绝发布
    public final static Integer WAITING_WITHDRAW=4;//等待下架
    public final static Integer FINAL_WITHDRAW=5;//已下架
    /************************职位的状态end**************************/

}
