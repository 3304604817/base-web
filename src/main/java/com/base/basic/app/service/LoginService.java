package com.base.basic.app.service;

public interface LoginService {

    String login(String username, String password, String captcha);

    void loginOut();
}
