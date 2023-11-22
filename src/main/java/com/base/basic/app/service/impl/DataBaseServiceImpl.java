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
    public List<Map<String, Object>> tableData(String tableName, String whereSql) {
        List<ColumnVO> columnVOS = dataBaseMapper.columnList(tableName);

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

        List<Map<String,Object>> resultData = dataBaseMapper.executeSelect(sql.toString());
        for(Map<String,Object> data:resultData){
            data.remove("row_sequence");
        }
        return resultData;
    }
}
