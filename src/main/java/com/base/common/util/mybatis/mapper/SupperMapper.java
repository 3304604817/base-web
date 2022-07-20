package com.base.common.util.mybatis.mapper;

import com.base.common.util.mybatis.mapper.update.*;
import tk.mybatis.mapper.common.Mapper;

public interface SupperMapper<T> extends
        Mapper<T>,
        OptionalUpdateMapper<T>,
        OptionalUpdateOplMapper<T>,
        UpdateByIdMapper<T>,
        UpdateByIdOplMapper<T>,
        UpdateByIdSelectiveMapper<T>,
        UpdateByIdSelectiveOplMapper<T>{
}
