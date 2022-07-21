package com.base.basic.domain.vo.v0;

import lombok.Data;

@Data
public class ColumnVO {
    // 库名
    private String tableSchema;

    // 表名
    private String tableName;

    // 列名
    private String columnName;

    // 是否可为空
    private String isNullable;

    // 字段类型
    private String dataType;

    // 键
    private String columnKey;

    // 注释
    private String columnComment;
}
