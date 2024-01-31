package com.base.basic.domain.entity.v1;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("内部接口参数")
@Table(name = "db_inner_params")
public class InnerParams extends BaseEntity {
    public static final String FIELD_API_PARAM_ID = "apiParamId";
    public static final String FIELD_API_ID = "apiId";
    public static final String FIELD_WHERE_NAME = "whereName";
    public static final String FIELD_WHERE_COMPARE = "whereCompare";
    public static final String FIELD_ENABLED_FLAG = "enabledFlag";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long apiParamId;

    @ApiModelProperty(value = "")
    private Long apiId;

    @ApiModelProperty(value = "")
    private String whereName;

    @ApiModelProperty(value = "")
    private String whereCompare;

    @ApiModelProperty(value = "是否启用。1启用，0未启用")
    private Integer enabledFlag;

    public Long getApiParamId() {
        return apiParamId;
    }

    public void setApiParamId(Long apiParamId) {
        this.apiParamId = apiParamId;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getWhereName() {
        return whereName;
    }

    public void setWhereName(String whereName) {
        this.whereName = whereName;
    }

    public String getWhereCompare() {
        return whereCompare;
    }

    public void setWhereCompare(String whereCompare) {
        this.whereCompare = whereCompare;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }
}
