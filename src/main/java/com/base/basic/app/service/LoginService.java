package com.base.basic.app.service;

public interface LoginService {

    /**
     * 生成图形验证码
     */
    void kaptcha();

    /**
     * 校验验证码
     */
    boolean validateCaptcha(String sessionCode);

    String login(String username, String password, String captcha);

    String thirdLogin(String username);

    void loginOut();
}
