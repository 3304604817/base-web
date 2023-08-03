package com.base.basic.domain.entity.v1;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 *
 */
@ApiModel("接口信息")
@Table(name = "db_interface")
public class Interface extends BaseEntity {
    public static final String FIELD_ID = "id";
    public static final String FIELD_INTERFACE_CODE = "interfaceCode";
    public static final String FIELD_INTERFACE_NAME = "interfaceName";
    public static final String FIELD_URL = "url";
    public static final String FIELD_ENABLED_FLAG = "enabledFlag";
    public static final String FIELD_TENANT_ID = "tenantId";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ApiModelProperty(value = "接口编码")
    private String interfaceCode;

    @ApiModelProperty(value = "接口名称")
    private String interfaceName;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabledFlag;

    @ApiModelProperty(value = "租户")
    private Long tenantId;

    @ApiModelProperty(value = "接口参数")
    @Transient
    private List<InterfaceParams> interfaceParams;

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

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Boolean enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public List<InterfaceParams> getInterfaceParams() {
        return interfaceParams;
    }

    public void setInterfaceParams(List<InterfaceParams> interfaceParams) {
        this.interfaceParams = interfaceParams;
    }
}
