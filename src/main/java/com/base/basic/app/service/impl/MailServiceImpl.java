package com.base.basic.app.service.impl;

import com.base.basic.app.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

/**
 * @author yang.gao
 * @description
 * @date 2022/10/6 19:50
 */
@Service
public class MailServiceImpl implements MailService {
    Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
}
