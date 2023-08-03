package com.base.basic.domain.entity.v1;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("接口变量信息")
@Table(name = "db_interface_params")
public class InterfaceParams extends BaseEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_INTERFACE_ID = "interfaceId";
    public static final String FIELD_PARAM_KEY = "paramKey";
    public static final String FIELD_PARAM_VALUE = "paramValue";
    public static final String FIELD_MEANING = "tenantId";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ApiModelProperty(value = "接口主键ID")
    private Long interfaceId;

    @ApiModelProperty(value = "变量名")
    private String paramKey;

    @ApiModelProperty(value = "变量值")
    private String paramValue;

    @ApiModelProperty(value = "租户")
    private Long tenantId;

    public InterfaceParams(){}
    public InterfaceParams(Long interfaceId){
        this.interfaceId = interfaceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}
