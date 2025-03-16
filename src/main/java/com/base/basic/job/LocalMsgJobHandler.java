package com.base.basic.job;


import com.base.common.scheduled.runner.ScheduledRunner;
import com.base.common.util.local.LocalAddressUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * 获取并发送本机信息
 */
@Component("LocalMsgJobHandler")
public class LocalMsgJobHandler implements ScheduledRunner {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void run(String args) {
        try {
            JSONObject jsonObject = new JSONObject(args);

            // 构建一个邮件对象
            SimpleMailMessage message = new SimpleMailMessage();
            // 设置邮件主题
            message.setSubject("本机信息");
            // 设置邮件发送者，这个跟application.yml中设置的要一致
            message.setFrom(from);
            // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
            message.setTo(jsonObject.getString("to"));

            // 设置邮件的正文
            StringBuffer text = new StringBuffer();
            for(String iPv4_iPv6:LocalAddressUtils.getIpv4_Ipv6()){
                text.append(iPv4_iPv6).append("\n");
            }
            message.setText(text.toString());

            // 发送邮件
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
