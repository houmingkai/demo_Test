package com.example.demo.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private JavaMailSender mailSender;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


//    @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
//    @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
//    @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
//    @Scheduled(cron="*/5 * * * * *") ：通过cron表达式定义规则

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("发送时间:"+new Date());
        List<String> mailList = Arrays.asList("244979134@qq.com","1103713906@qq.com");
        mailList.forEach(mail -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("673230298@qq.com");
            message.setTo(mail);
            message.setSubject("我是你爸爸?");
            message.setText("现在是北京时间:"+new Date());

            mailSender.send(message);
        });
    }

}
