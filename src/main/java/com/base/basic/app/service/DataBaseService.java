package com.base.basic.app.service;

import com.base.basic.domain.vo.v0.ColumnVO;
import com.base.basic.domain.vo.v0.TableVO;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface DataBaseService {

    PageInfo<TableVO> pageList(PageParmaters pageParmaters, TableVO tableVO);

    /**
     * 根据库名表名查列信息
     */
    List<ColumnVO> columnList(String tableSchema, String tableName);

    /**
     * 指定表名和查询条件查询表数据分页
     */
    List<Map<String,Object>> tableDataPage(PageParmaters pageParmaters, String tableSchema, String tableName, String whereSql);

    /**
     * 删除数据
     */
    Boolean tableDataDelete(String tableSchema, String tableName, List<Object> data);

    /**
     * 指定表名和查询条件查询表数据条数
     */
    Long count(String tableName, String whereSql);

    /**
     * 指定表名和查询条件查询表数据
     */
    List<Map<String,Object>> tableData(String tableSchema, String tableName);
    List<Map<String,Object>> tableData(String tableSchema, String tableName, String whereSql);
    List<Map<String,Object>> tableData(String tableSchema, String tableName, String whereSql, String limitSql);
}