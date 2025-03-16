package com.base.basic.job;


import com.base.common.scheduled.runner.ScheduledRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 获取并发送本机信息
 */
@Component("LocalMsgJobHandler")
public class LocalMsgJobHandler implements ScheduledRunner {

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void run(String args) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("本机信息");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("3304604817@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
//        message.setTo("gshen1024@gmail.com");
        message.setTo("3304604817@qq.com");
        // 设置邮件的正文
        message.setText("这是测试邮件的正文");
        // 发送邮件
        javaMailSender.send(message);
    }
}
