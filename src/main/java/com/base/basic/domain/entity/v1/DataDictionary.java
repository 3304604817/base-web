package com.base.basic.domain.entity.v1;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * 数据字典
 */
@ApiModel("数据字典")
@Table(name = "db_data_dictionary")
public class DataDictionary extends BaseEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_TAG = "tag";
    public static final String FIELD_DATA_KEY = "dataKey";
    public static final String FIELD_DATA_VALUE = "dataValue";
    public static final String FIELD_MEANING = "meaning";
    public static final String FIELD_ENABLED_FLAG = "enabledFlag";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

	@ApiModelProperty(value = "标签")
    private String tag;

	@ApiModelProperty(value = "关键字英文")
    private String dataKey;

	@ApiModelProperty(value = "值英文")
    private String dataValue;

	@ApiModelProperty(value = "中文注释")
    private String meaning;

	@ApiModelProperty(value = "是否启用。1启用，0未启用")
    private Integer enabledFlag;

    @ApiModelProperty(value = "数据字典明细")
    @Transient
    private DataDictionaryDetail dataDictionaryDetail;

    @ApiModelProperty(value = "标签多值")
    @Transient
    private List<String> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public DataDictionaryDetail getDataDictionaryDetail() {
        return dataDictionaryDetail;
    }

    public void setDataDictionaryDetail(DataDictionaryDetail dataDictionaryDetail) {
        this.dataDictionaryDetail = dataDictionaryDetail;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
