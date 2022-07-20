package com.base.basic.api.controller.v0;

import com.base.basic.app.service.LoginService;
import com.base.basic.domain.entity.v0.BaseResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.base.basic.domain.entity.v0.BaseResponseEntity.*;

@Api(tags="登录")
@RestController
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "重定向登录页面")
    @RequestMapping("/redirect-login")
    public void login(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        response.sendRedirect("/page/login-0.html");
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/security-login")
    public BaseResponseEntity login(@RequestParam(value = "username", required = true) String username,
                                    @RequestParam(value = "password", required = true) String password,
                                    @RequestParam(value = "captcha", required = true) String captcha) {
        String jwtToken = loginService.login(username, password, captcha);
        logger.info("Generate Token: {}", jwtToken);
        return Objects.nonNull(jwtToken) ? new BaseResponseEntity(CODE_200, STATUS_SUCCESS, jwtToken) : new BaseResponseEntity(CODE_302, STATUS_FAIL, null);
    }

    @ApiOperation(value = "退出登录")
    @RequestMapping("/login-out")
    public ResponseEntity loginOut() {
        loginService.loginOut();
        return new ResponseEntity(HttpStatus.OK);
    }
}
