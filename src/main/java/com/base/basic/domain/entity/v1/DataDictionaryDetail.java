package com.base.basic.domain.entity.v1;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据字典明细
 */
@Data
@ApiModel("数据字典明细")
@Table(name = "data_dictionary_detail")
public class DataDictionaryDetail extends BaseEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_DIC_ID = "dicId";
    public static final String FIELD_NOTES = "notes";
    public static final String FIELD_CONTENT = "content";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

	@ApiModelProperty(value = "数据字典头ID")
    private Long dicId;

	@ApiModelProperty(value = "注释")
    private String notes;

	@ApiModelProperty(value = "数据字典内容")
    private String content;
}
