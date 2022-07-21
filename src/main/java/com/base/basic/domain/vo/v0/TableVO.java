package com.base.basic.domain.vo.v0;

import lombok.Data;

import java.util.Date;

@Data
public class TableVO {

    // 库名
    private String tableSchema;

    // 表名
    private String tableName;

    // 表注释
    private String tableComment;

    // 创建时间
    private Date createTime;
}
