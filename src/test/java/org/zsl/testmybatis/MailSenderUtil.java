package org.zsl.testmybatis;

import com.ntq.baseMgr.po.MailBean;
import com.ntq.baseMgr.service.impl.JobSeekerInfosServiceImpl;
import com.ntq.baseMgr.service.impl.MailSenderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * <p>@description: </p>
 *
 * @projectName: bbs_maven_project
 * @packageName: com.ntq.baseMgr.util
 * @className:
 * @author: shuangyang
 * @date: 17-4-1 下午7:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MailSenderUtil {
    @Autowired
    private JobSeekerInfosServiceImpl jobSeekerInfosService;
    @Autowired
    private MailSenderServiceImpl mailSenderService;
    @Test
    public  void test1() throws UnsupportedEncodingException, MessagingException {
       /* ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        MailSenderServiceImpl mailSenderService = (MailSenderServiceImpl) ac.getBean("mailSenderServiceImpl");*/
        MailBean mailBean = new MailBean();

        mailBean.setFrom("l247677858my@126.com");
        mailBean.setFromName("XXX");
        mailBean.setSubject("你好测试信息");
        mailBean.setToEmails(new String[]{"247677858@qq.com"});
        mailBean.setContext("这是发送主题");
        mailSenderService.sendMail(mailBean);
    }
    @Test
    public void testSendMail(){
      /* jobSeekerInfosService.resumeFeedBack("247677858@qq.com","简历可能有问题——测试");*/
    }

}
