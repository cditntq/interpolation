package com.ntq.baseMgr.vo;

import com.ntq.baseMgr.po.MessageValidateRecord;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>@description: 包裹短信发送成功的code的短信信息</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.vo
 * @className:
 * @author: shuangyang
 * @date: 17-4-23 上午10:58
 */
@Setter
@Getter
public class MessageValidateRecordExtVo {
    /*短信实体*/
    private MessageValidateRecord messageValidateRecord;
    /*发送成功的验证码 2:成功*/
    private String code;
}
