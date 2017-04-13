package com.ntq.baseMgr.util;

/**
 * <p>@description:发送验证码相关信息 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.util
 * @className:
 * @author: shuangyang
 * @date: 17-4-12 下午11:18
 */
public class MessageCodeUtil {
    private MessageCodeUtil() {
    }

    //短信获取地址
    public final static String GET_MESSAGE_CODE_URL = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
    // 短信接口账户
    public final static String APIID = "C67034314";
    //短信接口密码
    public final static String APIKEY = "71dca19705ac93bae09961d89ddd9766";
}
