package com.base.common.util.mybatis.helper;

import tk.mybatis.mapper.entity.EntityColumn;

public class SqlUnitHelper {

    private static final String END_IF = "</if>";
    private static final String IF_TEST = "<if test=\"";
    public static final String OPTIONAL_BIND = "<bind name=\"optional\" value=\"@com.base.common.util.mybatis.helper.OptionalHelper@optional()\" />";

    public static String getIfContains(String optionalsName, EntityColumn column, String contents) {
        StringBuilder sql = new StringBuilder();
        sql.append(IF_TEST);
        sql.append(optionalsName).append(".contains(&quot;").append(column.getProperty()).append("&quot;)");
        sql.append("\">");
        sql.append(column.getColumn()).append(" = ").append(contents).append(" ");
        sql.append(END_IF);
        return sql.toString();
    }
}
