package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.Interface;
import com.base.basic.domain.entity.v1.Role;
import com.base.common.util.http.RestfulResponse;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface InterfaceService {

    PageInfo<Interface> pageList(PageParmaters pageParmaters, Interface searchBody);

    /**
     * 以账号密码的形式推送Soap接口
     * @param interfaceCode
     * @param content
     * @return
     */
    String sendSoap(String interfaceCode, String content);

    /**
     * 以账号密码的形式推送Soap接口并指定超时时间，单位秒
     * @param interfaceCode
     * @param content
     * @param readTimeout
     * @param writeTimeout
     * @param connectTimeout
     * @return
     */
    String sendSoap(String interfaceCode, String content, Long readTimeout, Long writeTimeout, Long connectTimeout);

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
