package com.base.basic.app.service.impl;

import com.base.basic.app.service.DataBaseService;
import com.base.basic.domain.vo.v0.ColumnVO;
import com.base.basic.infra.mapper.DataBaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataBaseServiceImpl implements DataBaseService {
    @Autowired
    @SuppressWarnings("all")
    private DataBaseMapper dataBaseMapper;

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
