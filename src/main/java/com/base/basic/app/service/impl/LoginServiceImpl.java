package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kaptcha.Kaptcha;
import com.base.basic.app.service.LoginService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.infra.mapper.UserMapper;
import com.base.common.exception.BaseException;
import com.base.common.util.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Kaptcha kaptcha;
    @Autowired
    private LoginService loginService;

    @Override
    public void kaptcha(){
        String sessionCode = kaptcha.render();
    }

    @Override
    public boolean validateCaptcha(String sessionCode){
        // 设置验证码有效期为120秒
        try {
            return kaptcha.validate(sessionCode, 120);
        }catch (Exception e){
            logger.error("验证码校验异常 {}", e);
            return false;
        }
    }

    @Override
    public String login(String username, String password, String captcha){
        if(!loginService.validateCaptcha(captcha)){
            throw new BaseException("验证码错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        // 调用密码验证逻辑
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        JSONObject principal = (JSONObject)JSONObject.toJSON(authentication.getPrincipal());
        return JwtUtils.createToken(principal.getString("username"), principal.getString("realName"), principal.getString("phone"), principal.getString("email"));
    }

    @Override
    public String thirdLogin(String username){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null);
        // 调用密码验证逻辑
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        JSONObject principal = (JSONObject)JSONObject.toJSON(authentication.getPrincipal());
        return JwtUtils.createToken(principal.getString("username"), principal.getString("realName"), principal.getString("phone"), principal.getString("email"));
    }

    @Override
    public void loginOut(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    }
}
