package com.base.common.util.convert;

import com.alibaba.fastjson.JSON;

public class StringConvertUtil {

    /**
     * 帕斯卡命名法：字符转驼峰并首字母大写
     * @param tableName
     * @return
     */
    public static String pascalCase(String tableName){
        // 统一先转成小写
        tableName = tableName.toLowerCase();
        // 首字母转大写
        tableName = new StringBuilder(tableName.substring(0, 1).toUpperCase())
                .append(tableName.substring(1))
                .toString();
        // _ 转驼峰
        while (tableName.contains("_")){
            int index = tableName.indexOf('_');
            tableName = new StringBuilder(tableName.substring(0, index))
                    .append(tableName.substring(index + 1, index + 2).toUpperCase())
                    .append(tableName.substring(index + 2)).toString();
        }
        return tableName;
    }

    /**
     * 骆驼命名法：字符转驼峰
     * @param columnName
     * @return
     */
    public static String camelCase(String columnName){
        // 统一先转成小写
        columnName = columnName.toLowerCase();
        // _ 转驼峰
        while (columnName.contains("_")){
            int index = columnName.indexOf('_');
            columnName = new StringBuilder(columnName.substring(0, index))
                    .append(columnName.substring(index + 1, index + 2).toUpperCase())
                    .append(columnName.substring(index + 2)).toString();
        }
        return columnName;
    }
}
