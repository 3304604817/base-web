package com.base.basic.api.controller.v0;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yang.gao
 * @description
 * @date 2022/10/6 19:40
 */
@Api(tags="邮件服务")
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @ApiOperation(value = "发送邮件")
    @PostMapping("/send")
    public void send(){
        //邮件设置1：一个简单的邮件
        SimpleMailMessage message=new SimpleMailMessage();
        message.setSubject("hello,world!");//设置标题
        message.setText("你好，世界！");//填入文本内容

        message.setTo("3304604817@qq.com");// 接收邮件的一方，可以设置成自己的邮箱测试
        message.setFrom("3304604817@qq.com"); //发送邮件的一方邮箱
        mailSender.send(message);//发送
    }
}
