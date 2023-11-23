package com.base.basic.infra.mapper;

import com.base.basic.domain.vo.v0.ColumnVO;
import com.base.basic.domain.vo.v0.TableVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataBaseMapper {

    /**
     * 获取表信息
     * @param tableVO
     * @return
     */
    List<TableVO> tableList(TableVO tableVO);

    /**
     * 根据表名获取字段信息
     * @param tableName
     * @return
     */
    List<ColumnVO> columnList(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

    Long count(@Param("tableName") String tableName, @Param("whereSql") String whereSql);

    /**
     * 执行动态查询 SQL
     * @param sql
     * @return
     */
    @MapKey("row_sequence")
    List<Map<String,Object>> executeSelect(@Param("sql") String sql);
}
