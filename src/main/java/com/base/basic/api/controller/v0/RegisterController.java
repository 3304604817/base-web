package com.base.basic.api.controller.v0;

import com.base.basic.app.service.RegisterService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.entity.v0.base.BaseResponseEntity;
import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import static com.base.basic.domain.entity.v0.base.BaseResponseEntity.*;
import static com.base.basic.domain.entity.v0.base.BaseResponseEntity.STATUS_FAIL;

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
    public BaseResponseEntity sendVerifyCode(@RequestBody IamUser iamUser){
        try {
            iamUser.setEmail(iamUser.getLoginName());
            registerService.sendVerifyCode(iamUser.getEmail());
            return new BaseResponseEntity(CODE_200, STATUS_SUCCESS);
        } catch (MessagingException e) {
            logger.error("e:{}", e);
            return new BaseResponseEntity(CODE_302, STATUS_FAIL, e.getMessage());
        }
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "邮箱注册")
    @PostMapping("/by-email")
    public BaseResponseEntity byEmail(@RequestBody IamUser iamUser) {
        try {
            iamUser.setEmail(iamUser.getLoginName());
            return new BaseResponseEntity(CODE_200, STATUS_SUCCESS, registerService.byEmail(iamUser));
        }catch (Exception e){
            return new BaseResponseEntity(CODE_302, STATUS_FAIL, e.getMessage());
        }
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "手机注册")
    @PostMapping("/by-phone")
    public BaseResponseEntity byPhone(@RequestBody IamUser iamUser) {
        try {
            iamUser.setPhone(iamUser.getLoginName());
            return new BaseResponseEntity(CODE_200, STATUS_SUCCESS, registerService.byPhone(iamUser));
        }catch (Exception e){
            return new BaseResponseEntity(CODE_302, STATUS_FAIL, e.getMessage());
        }
    }
}
