package com.ntq.baseMgr.po;

import lombok.Data;

/**
 * <p>@description: </p>
 *
 * @projectName: bbs_maven_project
 * @packageName: com.ntq.baseMgr.po
 * @className:
 * @author: shuangyang
 * @date: 17-4-1 下午7:54
 */
@Data
public class MailBean {
    private String from;
    private String fromName;
    private String[] toEmails;
    private String subject;
    private String context;
}
