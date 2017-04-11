package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.po.MessageValidateRecord;
import com.ntq.baseMgr.service.MessageValidateRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * <p>@description: </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service.impl
 * @className:
 * @author: shuangyang
 * @date: 17-4-11 下午4:04
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MessageValidateRecordServiceImplTest    {
    @Autowired
    private MessageValidateRecordService messageValidateRecordService;

    /**
     * 用于第三方接口插入测试
     */
    @Test
    public void insertMessageValidateRecord(){

        try {
            MessageValidateRecord messageValidateRecord = new MessageValidateRecord();
            messageValidateRecord.setPhoneNum(15123247206L);
            messageValidateRecord.setToken("123456");
            messageValidateRecord.setValideTime(new Date());
            messageValidateRecord.setSendSuccess(1);
            messageValidateRecordService.insertMessageValidateRecord(messageValidateRecord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}