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
@ApiModel("大乐透中奖历史")
@Table(name = "db_lottery_dlt_history")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(String drawNum) {
        this.drawNum = drawNum;
    }

    public Date getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(Date drawTime) {
        this.drawTime = drawTime;
    }

    public Long getFrontArea1() {
        return frontArea1;
    }

    public void setFrontArea1(Long frontArea1) {
        this.frontArea1 = frontArea1;
    }

    public Long getFrontArea2() {
        return frontArea2;
    }

    public void setFrontArea2(Long frontArea2) {
        this.frontArea2 = frontArea2;
    }

    public Long getFrontArea3() {
        return frontArea3;
    }

    public void setFrontArea3(Long frontArea3) {
        this.frontArea3 = frontArea3;
    }

    public Long getFrontArea4() {
        return frontArea4;
    }

    public void setFrontArea4(Long frontArea4) {
        this.frontArea4 = frontArea4;
    }

    public Long getFrontArea5() {
        return frontArea5;
    }

    public void setFrontArea5(Long frontArea5) {
        this.frontArea5 = frontArea5;
    }

    public Long getEndArea1() {
        return endArea1;
    }

    public void setEndArea1(Long endArea1) {
        this.endArea1 = endArea1;
    }

    public Long getEndArea2() {
        return endArea2;
    }

    public void setEndArea2(Long endArea2) {
        this.endArea2 = endArea2;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getDrawTimeFm() {
        return drawTimeFm;
    }

    public void setDrawTimeFm(String drawTimeFm) {
        this.drawTimeFm = drawTimeFm;
    }

    public String getDrawTimeTo() {
        return drawTimeTo;
    }

    public void setDrawTimeTo(String drawTimeTo) {
        this.drawTimeTo = drawTimeTo;
    }

    public Long getFrontAreaSum() {
        return frontAreaSum;
    }

    public void setFrontAreaSum(Long frontAreaSum) {
        this.frontAreaSum = frontAreaSum;
    }

    public Long getEndAreaSum() {
        return endAreaSum;
    }

    public void setEndAreaSum(Long endAreaSum) {
        this.endAreaSum = endAreaSum;
    }

    public Long getAllSum() {
        return allSum;
    }

    public void setAllSum(Long allSum) {
        this.allSum = allSum;
    }
}
