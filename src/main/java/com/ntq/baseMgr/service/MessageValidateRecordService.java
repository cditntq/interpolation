package com.ntq.baseMgr.service;

import com.ntq.baseMgr.po.MessageValidateRecord;
import com.ntq.baseMgr.util.ResponseResult;

/**
 * <p>@description: 短信验证ServiceImpl接口定义</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service
 * @className:
 * @author: shuangyang
 * @date: 17-4-11 下午3:38
 */
public interface MessageValidateRecordService {
    /**
     * 用于第三方接口插入
     * @param messageValidateRecord
     */
    ResponseResult<Void> insertMessageValidateRecord(MessageValidateRecord messageValidateRecord) throws Exception;
}
