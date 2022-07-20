package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.base.basic.app.service.LoginService;
import com.base.common.util.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password, String captcha){
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            // 调用密码验证逻辑
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            JSONObject principal = (JSONObject)JSONObject.toJSON(authentication.getPrincipal());
            return JwtUtils.createToken(principal.getString("username"));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void loginOut(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    }
}
