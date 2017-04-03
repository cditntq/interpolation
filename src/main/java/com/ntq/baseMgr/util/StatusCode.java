package com.ntq.baseMgr.util;

import java.util.HashMap;
import java.util.Map;


/**
 * @Date: 14-10-8
 * 1. 一切正常的响应状态以 0 返回 1返回的为失败
 * 2. 查询结果异常状态码区间 20000~20099
 * 3. 参数缺失异常状态码区间 20100~20199
 * 4. ERP内部异常状态码区间 20900~20999
 * @Time: 下午4:22
 */
public enum StatusCode {

    OK(1000, "操作成功"),
    Fail(1001, "操作失败"),
    QUERY_NULL_COMMERCIAL(20001, "该商户不存在"),
    QUERY_NULL_RESULT(20099, "该记录不存在, 请检查请求参数"),
    PARAM_NULL(20100, "参数为空"),
    /*邮件操作*/
    MAIL_SENDER_SUCCESS(3000, "邮件发送成功"),
    MAIL_SENDER_FAIL(3001, "邮件发送失败"),
    /*增删查改*/
    INSERT_SUCCESS(4000, "插入成功"),
    INSERT_FAIL(40001, "插入失败"),
    UPDATE_SUCCESS(5000, "更新成功"),
    UPDATE_FAIL(50001, "更新失败"),
    DELETE_SUCCESS(6001, "删除成功"),
    DELETE_FAIL(60001, "删除失败"),
    GET_SUCCESS(7001, "查询成功"),
    GET_FAIL(70001, "查询失败");


    /**
     * 商品库相关
     *//*
    MERCHANDISE_NOT_EXIST(20090, "该条形码对应的商品信息不存在");*/

    private int code;
    private String message;
    private static Map<Integer, StatusCode> cachedMap = new HashMap<Integer, StatusCode>();

    static {
        for (StatusCode rc : StatusCode.values()) {
            if (cachedMap.containsKey(rc.code)) {
               /* throw new MessageException("重复的状态码: " + rc.toString());*/
            }
            cachedMap.put(rc.code, rc);
        }
    }

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static StatusCode getStatusCode(int code) {
        return valueOf(code);
    }

    public static StatusCode valueOf(int code) {
        return cachedMap.get(code);
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StatusCode:[code=" + code + ",message=" + message + "]";
    }
}
