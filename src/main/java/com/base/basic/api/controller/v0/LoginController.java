package com.base.basic.api.controller.v0;

import com.alibaba.fastjson.JSONObject;
import com.base.basic.app.service.LoginService;
import com.base.common.annotation.Access;
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

@Api(tags="登录")
@RestController
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Access(accessNoToken = true)
    @ApiOperation(value = "重定向首页")
    @PostMapping("/index")
    public void index(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        response.sendRedirect("/index.html");
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "重定向登录页面")
    @GetMapping("/redirect-login")
    public void login(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        response.sendRedirect("/page/login-0.html");
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "生成验证码")
    @GetMapping("/kaptcha")
    public ResponseEntity kaptcha() {
        loginService.kaptcha();
        return new ResponseEntity(HttpStatus.OK);
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "用户登录")
    @PostMapping("/security-login")
    public ResponseEntity login(@RequestParam(value = "username", required = true) String username,
                                    @RequestParam(value = "password", required = true) String password,
                                    @RequestParam(value = "captcha", required = true) String captcha) {
        String jwtToken = loginService.login(username, password, captcha);
        return new ResponseEntity(JSONObject.parseObject("{\"jwtToken\":\""+ jwtToken +"\"}"), HttpStatus.OK);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/login-out")
    public ResponseEntity loginOut() {
        loginService.loginOut();
        return new ResponseEntity(HttpStatus.OK);
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "第三方登录")
    @PostMapping("/third-login")
    public ResponseEntity thirdLogin(@RequestParam(value = "username", required = true) String username) {
        String jwtToken = loginService.thirdLogin(username);
        return new ResponseEntity(JSONObject.parseObject("{\"jwtToken\":\""+ jwtToken +"\"}"), HttpStatus.OK);
    }
}
