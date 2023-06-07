package com.base.basic.domain.entity.v1;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 接口请求日志
 */
@ApiModel("审计日志")
@Table(name = "db_audit_log")
public class AuditLog extends BaseEntity {

    public static final String FIELD_ID = "id";
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

	@ApiModelProperty(value = "请求地址")
    private String url;

	@ApiModelProperty(value = "GET/POST/PUT/DELETE")
    private String method;

	@ApiModelProperty(value = "请求体")
    private String requestBody;

    @ApiModelProperty(value = "远程请求地址")
    private String remoteAddr;

    @ApiModelProperty(value = "远程请求端口")
    private Long remotePort;

    @ApiModelProperty(value = "请求状态")
    private Long status;

    @ApiModelProperty(value = "响应体")
    private String responseBody;

    @ApiModelProperty(value = "用户名")
    private String loginName;

    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(Long remotePort) {
        this.remotePort = remotePort;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
