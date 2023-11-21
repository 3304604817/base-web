package com.base.basic.app.service;

import java.util.List;
import java.util.Map;

public interface DataBaseService {

    /**
     * 查询表数据
     * @param tableName
     * @param whereSql
     * @return
     */
    List<Map<String,Object>> tableData(String tableName, String whereSql);
}
