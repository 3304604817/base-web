package com.base.basic.domain.entity.v1;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 大乐透中奖历史
 */
@Data
@ApiModel("大乐透中奖历史")
@Table(name = "lottery_dlt_history")
public class LotteryDltHistory extends BaseEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_DRAW_NUM = "drawNum";
    public static final String FIELD_DRAW_TIME = "drawTime";
    public static final String FIELD_FRONT_AREA1 = "frontArea1";
    public static final String FIELD_FRONT_AREA2 = "frontArea2";
    public static final String FIELD_FRONT_AREA3 = "frontArea3";
    public static final String FIELD_FRONT_AREA4 = "frontArea4";
    public static final String FIELD_FRONT_AREA5 = "frontArea5";
    public static final String FIELD_END_AREA1 = "endArea1";
    public static final String FIELD_END_AREA2 = "endArea2";
    public static final String FIELD_TENANT_ID = "tenantId";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

	@ApiModelProperty(value = "期号")
    private String drawNum;

	@ApiModelProperty(value = "开奖时间")
    private Date drawTime;

	@ApiModelProperty(value = "前区一号")
    private Long frontArea1;

	@ApiModelProperty(value = "前区二号")
    private Long frontArea2;

	@ApiModelProperty(value = "前区三号")
    private Long frontArea3;

	@ApiModelProperty(value = "前区四号")
    private Long frontArea4;

	@ApiModelProperty(value = "前区五号")
    private Long frontArea5;

	@ApiModelProperty(value = "后区一号")
    private Long endArea1;

	@ApiModelProperty(value = "后区二号")
    private Long endArea2;

	@ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "开奖时间从")
    @Transient
    private String drawTimeFm;

    @ApiModelProperty(value = "开奖时间至")
    @Transient
    private String drawTimeTo;

    @ApiModelProperty(value = "前区合计：前区所有号求和")
    @Transient
    private Long frontAreaSum;

    @ApiModelProperty(value = "后区合计：后区所有号求和")
    @Transient
    private Long endAreaSum;

    @ApiModelProperty(value = "总合计=前区合计+后区合计")
    @Transient
    private Long allSum;
}
