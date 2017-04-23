package com.ntq.baseMgr.util;

import com.ntq.baseMgr.po.MessageValidateRecord;
import com.ntq.baseMgr.vo.MessageValidateRecordExtVo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Date;

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


    /**
     * 短信发送
     *
     * @param phoneNumber
     * @return
     * @throws Exception
     */
    public static MessageValidateRecordExtVo sendAndGetMessageCode(Long phoneNumber) throws Exception {
        MessageValidateRecordExtVo messageValidateRecordExtVo = new MessageValidateRecordExtVo();
        //1. 调用接口获取手机号并得到验证码
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(MessageCodeUtil.GET_MESSAGE_CODE_URL);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");
        //1.1生成验证码
        int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
        //1.2生成短信内容
        String content = "您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。";
        NameValuePair[] data = {//提交短信
                new NameValuePair("account", MessageCodeUtil.APIID), //查看用户名请登录用户中心->验证码、通知短信->帐户及签名设置->APIID
                new NameValuePair("password", MessageCodeUtil.APIKEY),  //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
                new NameValuePair("mobile", phoneNumber.toString()),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);
        client.executeMethod(method);
        String SubmitResult = method.getResponseBodyAsString();
        //解析请求得到的参数
        Document doc = DocumentHelper.parseText(SubmitResult);
        Element root = doc.getRootElement();
        String code = root.elementText("code");
        //验证码录入数据库
        MessageValidateRecord messageValidateRecord = new MessageValidateRecord();
        messageValidateRecord.setToken(String.valueOf(mobile_code));
        messageValidateRecord.setValideTime(new Date());//验证码发送时间

        messageValidateRecord.setPhoneNum(phoneNumber);//电话
        //包裹短信相关信息和发送成功与否的code
        messageValidateRecordExtVo.setMessageValidateRecord(messageValidateRecord);
        messageValidateRecordExtVo.setCode(code);
        return messageValidateRecordExtVo;
    }
}
