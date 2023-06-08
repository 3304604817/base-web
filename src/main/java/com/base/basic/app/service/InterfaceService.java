package com.base.basic.app.service;

public interface InterfaceService {

    /**
     * 以账号密码的形式推送Soap接口
     * @param interfaceCode
     * @param content
     * @return
     */
    String sendSoap(String interfaceCode, String content);
}
