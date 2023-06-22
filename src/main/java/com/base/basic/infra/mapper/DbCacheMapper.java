package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v1.DbCache;
import com.base.common.util.mybatis.mapper.SupperMapper;

import java.util.List;

public interface DbCacheMapper extends SupperMapper<DbCache> {
    List<DbCache> list(DbCache dbCache);
}
