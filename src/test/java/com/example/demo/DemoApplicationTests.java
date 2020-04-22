package com.example.demo;

import com.example.demo.entity.CustomAttribute;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private CustomAttribute customAttribute;

    @Autowired
    private JavaMailSender mailSender;


    @Test
    public void contextLoads() {

       Assert.assertEquals(customAttribute.getName(), "程序猿DD");
    }

    @Test
    public void sendSimpleMail() throws Exception {
        List<String> mailList = Arrays.asList("244979134@qq.com", "1019926961@qq.com", "1103713906@qq.com");
        mailList.forEach(mail -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("673230298@qq.com");
            message.setTo(mail);
            message.setSubject("我可以骚你吗?");
            message.setText("Can I Fuck you?");

            mailSender.send(message);
        });

    }

    @Test
    public void sendAttachmentsMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("673230298@qq.com");
        helper.setTo("15903028826@163.com");
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");

        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\viruser.v-desktop\\Desktop\\20170430141036847.png"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);

        mailSender.send(mimeMessage);

    }


    @Test
    public void sendTemplateMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("673230298@qq.com");//邮箱写自己可以测试用的
        helper.setTo("15903028826@163.com");//邮箱写自己可以测试用的
        helper.setSubject("主题：模板邮件");
        VelocityContext context = new VelocityContext();
        context.put("username", "didi");
        StringWriter stringWriter = new StringWriter();
        // 需要注意第1个参数要全路径，否则会抛异常
        VelocityEngine velocityEngine = new VelocityEngine();
       // velocityEngine.mergeTemplate("d\\:/templates/template.vm", "UTF-8", context, stringWriter);   ---路径如何表示?
        helper.setText(stringWriter.toString(), true);
        mailSender.send(mimeMessage);
    }

}
