package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.mapper.MessageValidateRecordMapper;
import com.ntq.baseMgr.po.MessageValidateRecord;
import com.ntq.baseMgr.service.MessageValidateRecordService;
import com.ntq.baseMgr.util.ResponseResult;
import com.ntq.baseMgr.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>@description:短信验证ServiceImpl接口实现 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.service.impl
 * @className:
 * @author: shuangyang
 * @date: 17-4-11 下午3:40
 */
@Service
public class MessageValidateRecordServiceImpl implements MessageValidateRecordService {
    @Autowired
    private MessageValidateRecordMapper messageValidateRecordMapper;

    @Override
    public ResponseResult<Void> insertMessageValidateRecord(MessageValidateRecord messageValidateRecord) throws Exception {
        ResponseResult<Void> responseResult=new ResponseResult<>();
        messageValidateRecordMapper.insertMessageValidateRecord(messageValidateRecord);
        responseResult.setCode(StatusCode.INSERT_SUCCESS.getCode());
        responseResult.setMessage(StatusCode.INSERT_SUCCESS.getMessage());
        return responseResult;
    }
}
