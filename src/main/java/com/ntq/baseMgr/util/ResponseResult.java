package com.ntq.baseMgr.util;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Neil Dong on 2015/8/12.
 * 接口通用返回实体
 */
@Data
public class ResponseResult<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseResult.class);
    private boolean success = true;//成功状态
    private Integer code;
    private String message = "";//返回消息
    //   private Object[] messageParams = new Object[0];//国际化动态参数

    private T data;//返回的数据

    public ResponseResult() {
        LOGGER.info("私有构造方法");
    }

    public ResponseResult(String message) {
        this.message = message;
    }

    public ResponseResult(String message, Object... params) {
        this.message = message;
        // this.messageParams = params;
    }

    /**
     * 设置失败消息
     * 此方法会将success设置为false
     *
     * @param message 消息
     */
    public void setFailureMessage(String message) {
        this.success = false;
        this.message = message;
    }

    /**
     * 设置失败消息
     * 此方法会将success设置为false
     *
     * @param message 消息
     * @param params  消息中的参数，国际化的时候传入与placeholder匹配的动态参数
     */
    public void setFailureMessageWithParams(String message, Object... params) {
        setFailureMessage(message);
        // this.messageParams = params;
    }

    /**
     * 设置消息
     *
     * @param message 消息
     * @param params  消息中的参数，国际化的时候传入与placeholder匹配的动态参数
     */
    public void setMessageWithParams(String message, Object... params) {
        setMessage(message);
        //this.messageParams = params;
    }


}
