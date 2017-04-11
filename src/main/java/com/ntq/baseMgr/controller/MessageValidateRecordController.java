package com.ntq.baseMgr.controller;

import com.ntq.baseMgr.po.MessageValidateRecord;
import com.ntq.baseMgr.service.MessageValidateRecordService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>@description: 验证码的Controller</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.controller
 * @className:
 * @author: shuangyang
 * @date: 17-4-11 下午3:35
 */
@Controller
@RequestMapping(value = "/messageValidate")
public class MessageValidateRecordController {
    @Autowired
    private MessageValidateRecordService messageValidateRecordService;

    /**
     * 第三方短信平台获取验证码 todo 可能需要修改
     * @param messageValidateRecord
     * @return
     */
    @RequestMapping(value = "/insertMessageValidateRecord")
    @ResponseBody
    public ResponseResult<Void> insertMessageValidateRecord(@RequestBody MessageValidateRecord messageValidateRecord){
        ResponseResult<Void> responseResult=new ResponseResult<>();
        try {
            responseResult=messageValidateRecordService.insertMessageValidateRecord(messageValidateRecord);
        } catch (Exception e) {
            responseResult.setCode(StatusCode.INSERT_FAIL.getCode());
            responseResult.setFailureMessage(StatusCode.INSERT_FAIL.getMessage());
        }
        return responseResult;

    }
}
