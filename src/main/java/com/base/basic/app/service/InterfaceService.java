package com.base.basic.app.service;

import com.base.common.util.http.RestfulResponse;

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
     * @param interfaceCode 接口编码-必传
     * @param method 请求方式 GET/POST/PUT-必传
     * @param uriVariables URL参数-非必传
     * @param headerVariables header参数-非必传
     * @param content 请求体-非必传
     * @return
     */
    RestfulResponse sendRestful(String interfaceCode,
                                String method,
                                Map<String, ?> uriVariables,
                                Map<String, String> headerVariables,
                                String content);
}
