package com.ntq.baseMgr.exception;

/**
 * @Title: CustomException
 * @Description: 系统自定义的异常类型, 实际开发中可能需要定义多种
 * @Company:
 * @Author 杨爽【247677857yh@gmail.com】
 * @Date: 2016/3/31
 * @Time: 9:52
 */
public class CustomException extends Exception {

    //异常信息
    private String message;

    //构造函数
    public CustomException(String message) {
        super(message);
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
