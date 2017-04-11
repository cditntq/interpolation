package com.ntq.baseMgr.mapper;

import com.ntq.baseMgr.po.MessageValidateRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageValidateRecordMapper {
    /**
     * 通过手机号和验证码获取数据库里面记录
     *
     * @param phoneNumber 手机号
     * @param verifyCode  验证码
     * @return
     */
    MessageValidateRecord getMessageValidateRecord(@Param(value = "phoneNumber") Long phoneNumber, @Param(value = "verifyCode") String verifyCode);

    /**
     * 通过第三方接口获取插入验证码
     *
     * @param messageValidateRecord
     */
    void insertMessageValidateRecord(MessageValidateRecord messageValidateRecord);
}