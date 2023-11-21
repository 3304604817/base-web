package com.base.basic.infra.mapper;

import com.base.basic.domain.vo.v0.ColumnVO;
import com.base.basic.domain.vo.v0.TableVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    List<ColumnVO> columnList(@Param("tableName") String tableName);
}
