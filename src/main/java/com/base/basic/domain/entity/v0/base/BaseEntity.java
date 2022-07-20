package com.base.basic.domain.entity.v0.base;

import lombok.Data;
import tk.mybatis.mapper.annotation.Version;

import java.util.Date;

@Data
public class BaseEntity {
    public static final String FIELD_OBJECT_VERSION_NUMBER = "objectVersionNumber";
    public static final String FIELD_CREATE_BY = "createdBy";
    public static final String FIELD_CREATION_DATE = "creationDate";
    public static final String FIELD_LAST_UPDATED_BY = "lastUpdatedBy";
    public static final String FIELD_LAST_UPDATE_BY = "lastUpdateDate";

    @Version
    private Long objectVersionNumber;

    private Long createdBy;

    private Date creationDate;

    private Long lastUpdatedBy;

    private Date lastUpdateDate;
}
