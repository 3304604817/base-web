package com.base.basic.app.service;

import com.base.basic.domain.vo.v0.TableVO;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface DataBaseService {

    PageInfo<TableVO> pageList(PageParmaters pageParmaters, TableVO tableVO);

    /**
     * 指定表名和查询条件查询表数据
     * @param tableName
     * @param whereSql
     * @return
     */
    List<Map<String,Object>> tableData(String tableName, String whereSql);
}
