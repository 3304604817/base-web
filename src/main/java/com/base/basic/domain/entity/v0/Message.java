package com.base.basic.domain.entity.v0;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息发送
 *
 * @author 3304604817@qq.com 2022-10-06 22:47:18
 */
@Data
@EqualsAndHashCode
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
}
