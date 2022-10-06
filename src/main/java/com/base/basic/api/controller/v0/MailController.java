package com.base.basic.api.controller.v0;

import com.base.basic.app.service.MailService;
import com.base.basic.app.service.impl.MailServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

/**
 * @author yang.gao
 * @description
 * @date 2022/10/6 19:40
 */
@Api(tags="邮件服务")
@RestController
@RequestMapping("/mail")
public class MailController {
}
