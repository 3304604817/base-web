package com.base.basic.app.service;

import com.base.basic.domain.entity.v0.IamUser;

import javax.mail.MessagingException;

/**
 * @author yang.gao
 * @description
 * @date 2022/10/1 12:00
 */
public interface RegisterService {

    /**
     * 发送邮箱验证码
     */
    void sendVerifyCode(String mail) throws MessagingException;

    /**
     * 根据邮箱注册用户
     * @param iamUser
     * @return
     * @throws Exception
     */
    IamUser byEmail(IamUser iamUser) throws Exception;

    /**
     * 根据手机注册用户
     * @param iamUser
     * @return
     * @throws Exception
     */
    IamUser byPhone(IamUser iamUser) throws Exception;
}
