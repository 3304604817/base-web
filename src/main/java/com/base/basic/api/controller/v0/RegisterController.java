package com.base.basic.api.controller.v0;

import com.base.basic.app.service.RegisterService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.entity.v0.base.BaseResponseEntity;
import com.base.common.util.jwt.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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

    @Autowired
    private RegisterService registerService;

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