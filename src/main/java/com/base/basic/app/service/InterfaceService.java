package com.base.basic.app.service;

import java.util.Map;

public interface InterfaceService {

    /**
     * 以账号密码的形式推送Soap接口
     * @param interfaceCode
     * @param content
     * @return
     */
    String sendSoap(String interfaceCode, String content);

    /**
     * 推送Restful接口
     * @param interfaceCode
     * @param method
     * @param uriVariables
     * @param headerVariables
     * @param content
     * @return
     */
    String sendRestful(String interfaceCode,
                       String method,
                       Map<String, ?> uriVariables,
                       Map<String, String> headerVariables,
                       String content);
}
