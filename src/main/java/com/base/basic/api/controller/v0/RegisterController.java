package com.base.basic.api.controller.v0;

import com.base.basic.app.service.RegisterService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

/**
 * @author yang.gao
 * @description
 * @date 2022/10/1 11:56
 */
@Api(tags="注册")
@RestController
@RequestMapping("/register")
public class RegisterController {
    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RegisterService registerService;

    @Access(accessNoToken = true)
    @ApiOperation(value = "发送注册验证码")
    @PostMapping("/send/verify-code")
    public ResponseEntity sendVerifyCode(@RequestBody IamUser iamUser){
        try {
            iamUser.setEmail(iamUser.getLoginName());
            registerService.sendVerifyCode(iamUser.getEmail());
            return new ResponseEntity(HttpStatus.OK);
        } catch (MessagingException e) {
            logger.error("e:{}", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.FOUND);
        }
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "邮箱注册")
    @PostMapping("/by-email")
    public ResponseEntity byEmail(@RequestBody IamUser iamUser) {
        try {
            iamUser.setEmail(iamUser.getLoginName());
            return new ResponseEntity(registerService.byEmail(iamUser), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.FOUND);
        }
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "手机注册")
    @PostMapping("/by-phone")
    public ResponseEntity byPhone(@RequestBody IamUser iamUser) {
        try {
            iamUser.setPhone(iamUser.getLoginName());
            return new ResponseEntity(registerService.byPhone(iamUser), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.FOUND);
        }
    }
}
