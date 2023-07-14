package com.base.basic.domain.entity.v1;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@ApiModel("定时任务")
@Table(name = "db_scheduled")
public class Scheduled extends BaseEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_SCHEDULED_NAME = "scheduledName";
    public static final String FIELD_BEAN_NAME = "beanName";
    public static final String FIELD_PARAM = "param";
    public static final String FIELD_CRON = "cron";
    public static final String FIELD_STATUS = "status";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ApiModelProperty(value = "定时任务名称")
    @NotBlank(message = "任务名称不能为空")
    private String scheduledName;

    @ApiModelProperty(value = "定时任务Bean名称")
    @NotBlank(message = "Bean不能为空")
    private String beanName;

    @ApiModelProperty(value = "参数")
    private String param;

    @ApiModelProperty(value = "cron")
    @NotBlank(message = "cron不能为空")
    private String cron;

    @ApiModelProperty(value = "是否启用")
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScheduledName() {
        return scheduledName;
    }

    public void setScheduledName(String scheduledName) {
        this.scheduledName = scheduledName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
