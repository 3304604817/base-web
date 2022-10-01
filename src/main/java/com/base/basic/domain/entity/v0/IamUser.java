package com.base.basic.domain.entity.v0;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@Table(name = "iam_user")
public class IamUser extends BaseEntity {
    public static final String FIELD_ID = "id";
    public static final String FIELD_LOGIN_NAME = "loginName";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_ORGANIZATION_ID = "organizationId";
    public static final String FIELD_HASH_PASSWORD = "hashPassword";
    public static final String FIELD_REAL_NAME = "realName";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_INTERNATIONAL_TEL_CODE = "internationalTelCode";
    public static final String FIELD_LANGUAGE = "language";
    public static final String FIELD_TIME_ZONE = "timeZone";
    public static final String FIELD_IS_ENABLED = "isEnabled";
    public static final String FIELD_IS_LOCKED = "isLocked";
    public static final String FIELD_USER_TYPE = "userType";

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String loginName;

    private String email;

    private Long organizationId;

    private String hashPassword;

    private String realName;

    private String phone;

    private String internationalTelCode;

    private String language;

    private String timeZone;

    private Boolean isEnabled;

    private Boolean isLocked;

    private String userType;

    // 注册密码
    @Transient
    private String pwd;

    // 注册密码确认
    @Transient
    private String confirmPwd;

    public IamUser(){}

    public IamUser(String loginName){
        this.loginName = loginName;
    }
}
