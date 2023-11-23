package com.base.basic.app.service.impl;

import com.base.basic.app.service.DataBaseService;
import com.base.basic.domain.vo.v0.ColumnVO;
import com.base.basic.domain.vo.v0.TableVO;
import com.base.basic.infra.mapper.DataBaseMapper;
import com.base.common.util.convert.DateConvertUtil;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DataBaseServiceImpl implements DataBaseService {
    Logger logger = LoggerFactory.getLogger(DataBaseServiceImpl.class);
    @Autowired
    @SuppressWarnings("all")
    private DataBaseMapper dataBaseMapper;

    @Override
    public PageInfo<TableVO> pageList(PageParmaters pageParmaters, TableVO tableVO){
        PageInfo<TableVO> pageInfo = PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> dataBaseMapper.tableList(tableVO));
        for(TableVO table:pageInfo.getList()){
            table.setCreateTimeString(
                    DateConvertUtil.dateTimeString(table.getCreateTime())
            );
        }
        return pageInfo;
    }

    @Override
    public List<ColumnVO> columnList(String tableSchema, String tableName){
        return dataBaseMapper.columnList(tableSchema, tableName);
    }

    @Override
    public List<Map<String, Object>> tableDataPage(PageParmaters pageParmaters, String tableSchema, String tableName, String whereSql) {
        String limitSql = new StringBuilder("LIMIT ")
                .append((pageParmaters.getPage()-1) * pageParmaters.getLimit())
                .append(",")
                .append(pageParmaters.getLimit()).toString();
        List<Map<String, Object>> tableData = this.tableData(tableSchema, tableName, whereSql, limitSql);
        return tableData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean tableDataDelete(String tableSchema, String tableName, List<Object> data){
        return Boolean.FALSE;
    }

    @Override
    public Long count(String tableName, String whereSql){
        return dataBaseMapper.count(tableName, whereSql);
    }

    @Override
    public List<Map<String, Object>> tableData(String tableSchema, String tableName) {
        List<Map<String,Object>> resultData = dataBaseMapper.executeSelect(this.dynamicSelect(tableSchema, tableName, null, null));
        for(Map<String,Object> data:resultData){
            data.remove("row_sequence");
        }
        return resultData;
    }

    @Override
    public List<Map<String, Object>> tableData(String tableSchema, String tableName, String whereSql) {
        List<Map<String,Object>> resultData = dataBaseMapper.executeSelect(this.dynamicSelect(tableSchema, tableName, whereSql, null));
        for(Map<String,Object> data:resultData){
            data.remove("row_sequence");
        }
        return resultData;
    }

    @Override
    public List<Map<String, Object>> tableData(String tableSchema, String tableName, String whereSql, String limitSql) {
        List<Map<String,Object>> resultData = dataBaseMapper.executeSelect(this.dynamicSelect(tableSchema, tableName, whereSql, limitSql));
        for(Map<String,Object> data:resultData){
            data.remove("row_sequence");
        }
        return resultData;
    }

    /**
     * 动态生成查询sql
     * @param tableSchema
     * @param tableName
     * @param whereSql
     * @param limitSql
     * @return
     */
    private String dynamicSelect(String tableSchema, String tableName, String whereSql, String limitSql){
        List<ColumnVO> columnVOS = dataBaseMapper.columnList(tableSchema, tableName);

        StringBuilder sql = new StringBuilder("SELECT ");
        for(ColumnVO columnVO:columnVOS){
            sql.append(columnVO.getColumnName()).append(",");
        }
        // 去掉最后一个逗号
        sql.deleteCharAt(sql.length()-1);
        sql.append(" FROM ").append(tableName);
        if(StringUtils.isNotEmpty(whereSql)){
            sql.append(" WHERE 1 = 1 AND ").append(whereSql);
        }
        if(StringUtils.isNotEmpty(limitSql)){
            sql.append(" ").append(limitSql);
        }
        return sql.toString();
    }
}
