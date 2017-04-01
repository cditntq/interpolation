package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.po.MailBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * <p>@description: </p>
 *
 * @projectName: bbs_maven_project
 * @packageName: com.ntq.baseMgr.service.impl
 * @className:
 * @author: shuangyang
 * @date: 17-4-1 下午7:55
 */
@Service
public class MailSenderServiceImpl {
    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;

    /**
     * 创建MimeMessage
     * @param mailBean
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public MimeMessage createMimeMessage(MailBean mailBean) throws MessagingException, UnsupportedEncodingException{
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(mailBean.getFrom(), mailBean.getFromName());
        messageHelper.setSubject(mailBean.getSubject());
        messageHelper.setTo(mailBean.getToEmails());
        messageHelper.setText(mailBean.getContext(), true); // html: true
        return mimeMessage;
    }

    public void sendMail(MailBean mailBean) throws UnsupportedEncodingException, MessagingException {
        MimeMessage msg = createMimeMessage(mailBean);
        javaMailSenderImpl.send(msg);
    }
}
