package com.ntq.baseMgr.po;

import lombok.Data;

/**
 * <p>@description:邮件发送实体 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.po
 * @className:
 * @author: shuangyang
 * @date: 17-4-1 下午7:54
 */
@Data
public class MailBean {

    private String from;
    /*发送方名称*/
    private String fromName;
    /*接收方邮件列表*/
    private String[] toEmails;
    /*主题*/
    private String subject;
    /*邮件内容*/
    private String context;


}
