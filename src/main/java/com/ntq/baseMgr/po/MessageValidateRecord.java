package com.ntq.baseMgr.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>@description:短信校验码记录 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.po
 * @className:
 * @author: shuangyang
 * @date: 17-4-11 下午15:17
 */
@Getter
@Setter
public class MessageValidateRecord {
    private Long id;

    private Long phoneNum;

    private String token;

    private Date valideTime;

    private Integer sendSuccess;

}