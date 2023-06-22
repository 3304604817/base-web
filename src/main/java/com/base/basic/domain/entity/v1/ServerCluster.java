package com.base.basic.domain.entity.v1;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 服务器集群
 */
@ApiModel("服务器集群")
@Table(name = "db_server_cluster")
public class ServerCluster extends BaseEntity {
    public static final String FIELD_ID = "id";
    public static final String FIELD_IP = "ip";
    public static final String FIELD_PORT = "port";
    public static final String FIELD_UP_DATE = "upDate";
    public static final String FIELD_DOWN_DATE = "downDate";
    public static final String FIELD_STATUS = "status";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ApiModelProperty(value = "服务IP")
    private String ip;

    @ApiModelProperty(value = "端口号")
    private Long port;

    @ApiModelProperty(value = "上线时间")
    private Date upDate;

    @ApiModelProperty(value = "下线时间")
    private Date downDate;

    @ApiModelProperty(value = "是否在线。UP/DOWN")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    public Date getDownDate() {
        return downDate;
    }

    public void setDownDate(Date downDate) {
        this.downDate = downDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
