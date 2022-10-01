package com.base.basic.app.service;

import com.base.basic.domain.entity.v0.IamUser;

/**
 * @author yang.gao
 * @description
 * @date 2022/10/1 12:00
 */
public interface RegisterService {

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
