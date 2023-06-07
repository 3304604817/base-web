package com.base.basic.domain.entity.v0;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 消息发送
 *
 * @author 3304604817@qq.com 2022-10-06 22:47:18
 */
@Table(name = "db_message")
public class Message extends BaseEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_SENDER = "sender";
    public static final String FIELD_RECEIVER = "receiver";
    public static final String FIELD_TYPE_CODE = "typeCode";
    public static final String FIELD_SUBJECT = "subject";
    public static final String FIELD_CONTENT = "content";
    public static final String FIELD_VERIFY_CODE = "verifyCode";
    public static final String FIELD_TENANT_ID = "tenantId";

    @ApiModelProperty("表ID，主键")
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

	@ApiModelProperty(value = "发送人（邮箱/手机号）")
    private String sender;

	@ApiModelProperty(value = "接收人（邮箱/手机号）")
    private String receiver;

	@ApiModelProperty(value = "M:邮件 S:短信 W:微信")
    private String typeCode;

	@ApiModelProperty(value = "消息标题")
    private String subject;

	@ApiModelProperty(value = "消息内容")
    private String content;

	@ApiModelProperty(value = "验证码，针对邮件或者短信的验证码")
    private String verifyCode;

	@ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "过期时间")
    @Transient
    private Date expireDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
