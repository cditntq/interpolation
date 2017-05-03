package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.po.MailBean;
import com.ntq.baseMgr.util.NQTMailSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private NQTMailSenderImpl nqtMailSender;//用于向内推圈的专用邮箱发送邮件
    /*发送方邮件*/
    @Value("#{configProperties['mail_from']}")
    private String senderMail;
    /*发送方邮件*/
    @Value("#{configProperties['mail_username']}")
    private String senderName;

    /*发送给内推圈的邮件*/
    @Value("#{configProperties['deal_message_mail_from']}")
    private String dealMessageMail;

    @Value("#{configProperties['deal_message_mail_username']}")
    private String  dealMessageName;
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
        messageHelper.setFrom(senderMail, senderName);
        messageHelper.setSubject(mailBean.getSubject());
        messageHelper.setTo(mailBean.getToEmails());
        messageHelper.setText(mailBean.getContext(), true); // html: true
        return mimeMessage;
    }

    public void sendMail(MailBean mailBean) throws UnsupportedEncodingException, MessagingException {
        MimeMessage msg = createMimeMessage(mailBean);
        javaMailSenderImpl.send(msg);
    }

    /**
     * 发送消息给内推圈：1.目前只是解决职位下架
     * @param context 发送的内容
     * @param message 主题消息
     */
    public void sendEmail2ntqEmail(String context,String message) throws UnsupportedEncodingException, MessagingException {
        MimeMessage mimeMessage =nqtMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(dealMessageMail, dealMessageName);//邮件发送方 ntq用于发送请求的邮箱
        messageHelper.setSubject(message);
        messageHelper.setTo(senderMail);//邮件接收方 ntq处理简历的邮箱
        messageHelper.setText(context, true); // html: true
        nqtMailSender.send(mimeMessage);

    }
}
