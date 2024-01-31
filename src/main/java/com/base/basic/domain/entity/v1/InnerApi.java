package com.base.basic.domain.entity.v1;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("内部接口")
@Table(name = "db_inner_api")
public class InnerApi extends BaseEntity {
    public static final String FIELD_API_ID = "apiId";
    public static final String FIELD_API_TYPE = "apiType";
    public static final String FIELD_API_URI = "apiUri";
    public static final String FIELD_AUTH_LEVEL = "authLevel";
    public static final String FIELD_ENABLED_FLAG = "enabledFlag";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long apiId;

    @ApiModelProperty(value = "POST/PUT/GET/DELETE")
    private String apiType;

    @ApiModelProperty(value = "接口地址")
    private String apiUri;

    @ApiModelProperty(value = "授权级别PUBLIC是都能访问")
    private String authLevel;

    @ApiModelProperty(value = "是否启用。1启用，0未启用")
    private Integer enabledFlag;

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

    public String getApiUri() {
        return apiUri;
    }

    public void setApiUri(String apiUri) {
        this.apiUri = apiUri;
    }

    public String getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }
}
