package com.base.basic.domain.entity.v1;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@ApiModel("接口日志")
@Table(name = "db_interface_log")
public class InterfaceLog extends BaseEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_INTERFACE_CODE = "interfaceCode";
    public static final String FIELD_URL = "url";
    public static final String FIELD_METHOD = "method";
    public static final String FIELD_REQUEST_BODY = "requestBody";
    public static final String FIELD_REMOTE_ADDR = "remoteAddr";
    public static final String FIELD_REMOTE_PORT = "remotePort";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_RESPONSE_BODY = "responseBody";
    public static final String FIELD_LOGIN_NAME = "loginName";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ApiModelProperty(value = "接口编码")
    private String interfaceCode;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "GET/POST/PUT/DELETE")
    private String method;

    @ApiModelProperty(value = "请求体")
    private String requestBody;

    @ApiModelProperty(value = "远程请求地址")
    private String remoteAddr;

    @ApiModelProperty(value = "请求状态")
    private Long status;

    @ApiModelProperty(value = "响应体")
    private String responseBody;

    public InterfaceLog(){}
    public InterfaceLog(String interfaceCode, String url, String method, String requestBody, String remoteAddr, Long status, String responseBody) {
        this.interfaceCode = interfaceCode;
        this.url = url;
        this.method = method;
        this.requestBody = requestBody;
        this.remoteAddr = remoteAddr;
        this.status = status;
        this.responseBody = responseBody;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
