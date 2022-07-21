package com.base.basic.domain.vo.v0;

import lombok.Data;

@Data
public class ColumnVO {
    /**
     * 库名
     */
    private String tableSchema;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 数据库列名
     */
    private String columnName;

    /**
     * 是否可为空
     */
    private String isNullable;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 键
     */
    private String columnKey;

    /**
     * 注释
     */
    private String columnComment;

    /**
     * 数据库列名大写
     */
    private String upperColumnName;

    /**
     * 数据库列名驼峰
     */
    private String camelColumnName;

    /**
     * 数据库列名驼峰并首字母大学
     */
    private String pascalColumnName;
}
