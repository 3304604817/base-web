package com.base.basic.app.service.impl;

import com.base.basic.app.service.RegisterService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.entity.v0.Message;
import com.base.basic.infra.mapper.MessageMapper;
import com.base.basic.infra.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author yang.gao
 * @description
 * @date 2022/10/1 12:00
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;

    Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Value("${spring.mail.username}")
    private String serverMail;

    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    @SuppressWarnings("all")
    private MessageMapper messageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendVerifyCode(String mail) throws MessagingException {
        if(StringUtils.isEmpty(mail)){
            throw new MessagingException("接收方邮箱不能为空");
        }

        /**
         * 生成6位验证码
         */
        int verifyCode = (int) ((Math.random() * 9 + 1) * 100000);

        /**
         * 初始化邮件信息
         */
        //邮件设置1：一个简单的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,true);
        // MimeMessageHelper 可以设置发送附件
        String subject = "注册验证码";
        mimeMessageHelper.setSubject(subject);
        // 这里设置true可以读取html语言，为文本设置样式，也可不填
        String text = new StringBuilder("你好 ").append(mail).append("</b> 您的注册验证码是：").append(verifyCode).toString();
        mimeMessageHelper.setText(text,true);

        // 发送方邮箱
        mimeMessageHelper.setFrom(serverMail);
        // 接收方邮箱
        mimeMessageHelper.setTo(mail);

        /**
         * 保存消息
         */
        Message message = new Message();
        message.setSender(serverMail);
        message.setReceiver(mail);
        message.setTypeCode("M");
        message.setSubject(subject);
        message.setContent(text);
        message.setVerifyCode(String.valueOf(verifyCode));
        messageMapper.insertSelective(message);

        mailSender.send(mimeMessage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamUser byEmail(IamUser iamUser) throws Exception {
        if(StringUtils.isEmpty(iamUser.getEmail())){
            throw new Exception("注册邮箱不能为空");
        }
        if(!StringUtils.equals(iamUser.getPwd(), iamUser.getConfirmPwd())){
            throw new Exception("密码不一致");
        }

        Example example = new Example(IamUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(IamUser.FIELD_EMAIL, iamUser.getEmail());
        if(userMapper.selectCountByExample(example) > 0){
            throw new Exception("邮箱已注册");
        }

        /**
         * 验证验证码是否过期
         */
        Message message = new Message();
        message.setTypeCode("M");
        message.setReceiver(iamUser.getEmail());
        message.setVerifyCode(iamUser.getCaptcha());
        // 验证码十分钟内有效
        message.setExpireDate(new Date(System.currentTimeMillis() - 10*60*1000));
        if(messageMapper.list(message).size() < 1){
            throw new Exception("验证码错误或已过期");
        }


        /**
         * 生成账号
         */
        iamUser.setHashPassword(BCrypt.hashpw(iamUser.getPwd(), BCrypt.gensalt()));
        userMapper.insertSelective(iamUser);

        return iamUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamUser byPhone(IamUser iamUser) throws Exception {
        if(StringUtils.isEmpty(iamUser.getPhone())){
            throw new Exception("注册手机不能为空");
        }
        if(!StringUtils.equals(iamUser.getPwd(), iamUser.getConfirmPwd())){
            throw new Exception("密码不一致");
        }

        Example example = new Example(IamUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(IamUser.FIELD_PHONE, iamUser.getPhone());
        if(userMapper.selectCountByExample(example) > 0){
            throw new Exception("该手机号已注册");
        }

        iamUser.setHashPassword(BCrypt.hashpw(iamUser.getPwd(), BCrypt.gensalt()));
        userMapper.insertSelective(iamUser);

        return iamUser;
    }
}
