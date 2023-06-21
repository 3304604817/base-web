package com.base.basic.domain.entity.v1;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("缓存")
@Table(name = "db_cache")
public class DbCache extends BaseEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_CACHE_TYPE = "cacheType";
    public static final String FIELD_CACHE_KEY = "cacheKey";
    public static final String FIELD_CACHE_VALUE = "cacheValue";
    public static final String FIELD_REMARK = "remark";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ApiModelProperty(value = "缓存类型")
    private String cacheType;

    @ApiModelProperty(value = "key")
    private String cacheKey;

    @ApiModelProperty(value = "value")
    private String cacheValue;

    @ApiModelProperty(value = "标签")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getCacheValue() {
        return cacheValue;
    }

    public void setCacheValue(String cacheValue) {
        this.cacheValue = cacheValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
