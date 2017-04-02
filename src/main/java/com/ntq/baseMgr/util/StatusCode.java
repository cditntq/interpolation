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
    DELETE_SUCCESS(5000, "删除成功"),
    DELETE_FAIL(50001, "删除失败");
    /**
     * 品牌相关
     *//*
    BRAND_ID_NOTNULL(20101, "品牌编号不能为空"),
    BRAND_NAME_NOTNULL(20102, "品牌名称不能为空"),
    BRAND_CONTACT_NOTNULL(20103, "品牌责任人不能为空"),
    BRAND_PHONE_NOTNULL(20104, "品牌联系人手机号不能为空"),
    BRAND_NOT_EXISTS(20105, "品牌不存在"),
    BRAND_HAS_EXISTS(20108, "品牌已经存在"),
    BRAND_ID_TOO_LONG(20106, "品牌编号过长"),
    BRAND_NAME_EXISTS(20107, "品牌名称重复"),

    *//**
     * 商户相关
     *//*
    COMMERCIAL_ID_NOTNULL(20201, "商户编号不能为空"),
    COMMERCIAL_NAME_NOTNULL(20202, "商户名称不能为空"),
    COMMERCIAL_BRANCH_NAME_NOTNULL(20203, "商户分店名称不能为空"),
    COMMERCIAL_MEAL_TYPE_FIRST_LEVEL_NOTNULL(20204, "商户一级业态不能为空"),
    COMMERCIAL_MEAL_TYPE_SECOND_LEVEL_NOTNULL(20205, "商户二级业态不能为空"),
    COMMERCIAL_TYPE_NOTNULL(20206, "商户菜系类型不能为空"),
    COMMERCIAL_CONSUME_PERSON_NOTNULL(20207, "商户人均消费不能为空"),
    COMMERCIAL_PRIVINCE_CODE_NOTNULL(20208, "商户省份编码不能为空"),
    COMMERCIAL_PRIVINCE_NAME_NOTNULL(20209, "商户省份名称不能为空"),
    COMMERCIAL_CITY_CODE_NOTNULL(20210, "商户城市编码不能为空"),
    COMMERCIAL_CITY_NAME_NOTNULL(20211, "商户城市名称不能为空"),
    COMMERCIAL_AREA_CODE_NOTNULL(20212, "商户区编码不能为空"),
    COMMERCIAL_AREA_NAME_NOTNULL(20213, "商户区名称不能为空"),
    COMMERCIAL_TRADE_AREA_CODE_NOTNULL(20214, "商户商圈编码不能为空"),
    COMMERCIAL_TRADE_AREA_NAME_NOTNULL(20215, "商户商圈名称不能为空"),
    COMMERCIAL_ADDRESS_NOTNULL(20216, "商户详细地址不能为空"),
    COMMERCIAL_CONTACT_NOTNULL(20217, "商户联系人不能为空"),
    COMMERCIAL_CONTACT_PHONE_NOTNULL(20218, "商户联系人电话不能为空"),
    COMMERCIAL_PHONE_NOTNULL(20219, "商户电话不能为空"),
    COMMERCIAL_NOT_EXISTS(20220, "商户不存在"),
    COMMERCIAL_ID_TOO_LONG(20221, "商户编号过长"),
    COMMERCIAL_EXIST_PADNUM(20222, "商户下已有该pad编号"),
    COMMERCIAL_EXIST_MAINPOS(20223, "商户下已存在主POS"),
    COMMERCIAL_NAME_REPEAT(20224, "商户名称重复"),

    *//**
     * 实物相关
     *//*
    GOODS_NO_SUCH_GOODS(20601, "查不到此实物"),
    GOODS_UNAVAILABLE(20602, "此实物不可用"),
    GOODS_COMMERCIAL_BINDED(20603, "此实物已绑定商户"),
    GOODS_NOT_ONPOS(20604, "此实物非OnPOS设备"),
    GOODS_MAC_NOTNULL(20605, "设备MAC地址不能为空"),
    GOODS_PAD_NUM_NOTNULL(20606, "设备编号不能为空"),
    GOODS_MAIN_POS_NOTNULL(20607, "是否主收银设备不能为空"),
    GOODS_PAD_NO_TOO_LONG(20608, "PAD编号过长"),
    GOODS_SN_NOTNULL(20609, "设备SN不能为空"),
    APPLICATION_TYPE_NOTNULL(20610, "应用类型不能为空"),
    DEVICE_TYPE_NOTNULL(20611, "设备类型不能为空"),
    SECRET_KEY_NOTNULL(20612, "商户密钥不能为空"),
    BINDDEVICE_TYPE_NOTNULL(20613, "绑定设备类型不能为空"),
    DEVICETYPE_ONE_GOODSNO_NOTNULL(20614, "商用设备实物编号不能为空"),
    GOODS_STATUS_NOT_CHOUSHOU(20615, "实物状态不是已出售"),*/


/*    *//**
     * 异常相关
     *//*
    QUERY_EXCEPTION(20900, "ERP内部查询异常"),
    REDIS_NOT_CONFIGURED_EXCEPTION(20901, "ERP内部异常-RedisManager未加载"),
    JSON_SERIALIZE_EXCEPTION(90001, "JSON序列化出错"),
    JSON_DESERIALIZE_EXCEPTION(90002, "JSON反序列化出错"),

    */
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
