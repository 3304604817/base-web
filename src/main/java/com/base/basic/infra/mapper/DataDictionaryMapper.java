package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v1.DataDictionary;
import com.base.common.util.mybatis.mapper.SupperMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 数据字典Mapper
 */
public interface DataDictionaryMapper extends SupperMapper<DataDictionary> {

    List<DataDictionary> list(DataDictionary parameters);
}
