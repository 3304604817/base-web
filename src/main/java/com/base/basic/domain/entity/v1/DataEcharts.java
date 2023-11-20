package com.base.basic.domain.entity.v1;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@ApiModel("数据可视化")
@Table(name = "db_data_echarts")
public class DataEcharts extends BaseEntity {
    public static final String FIELD_ID = "id";
    public static final String FIELD_ECHART_TYPE = "echartType";
    public static final String FIELD_ECHART_KEY = "echartKey";
    public static final String FIELD_ECHART_DATA = "echartData";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ApiModelProperty(value = "图表类型")
    private String echartType;

    @ApiModelProperty(value = "")
    private String echartKey;

    @ApiModelProperty(value = "数据")
    private String echartData;

    @ApiModelProperty(value = "数据")
    @Transient
    private String creationDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEchartType() {
        return echartType;
    }

    public void setEchartType(String echartType) {
        this.echartType = echartType;
    }

    public String getEchartKey() {
        return echartKey;
    }

    public void setEchartKey(String echartKey) {
        this.echartKey = echartKey;
    }

    public String getEchartData() {
        return echartData;
    }

    public void setEchartData(String echartData) {
        this.echartData = echartData;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
