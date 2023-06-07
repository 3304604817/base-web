package com.base.basic.domain.vo.v0;


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

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getUpperColumnName() {
        return upperColumnName;
    }

    public void setUpperColumnName(String upperColumnName) {
        this.upperColumnName = upperColumnName;
    }

    public String getCamelColumnName() {
        return camelColumnName;
    }

    public void setCamelColumnName(String camelColumnName) {
        this.camelColumnName = camelColumnName;
    }

    public String getPascalColumnName() {
        return pascalColumnName;
    }

    public void setPascalColumnName(String pascalColumnName) {
        this.pascalColumnName = pascalColumnName;
    }
}
