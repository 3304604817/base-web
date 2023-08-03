package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v1.InterfaceParams;
import com.base.common.util.mybatis.mapper.SupperMapper;

import java.util.List;

public interface InterfaceParamsMapper extends SupperMapper<InterfaceParams> {

    List<InterfaceParams> list(InterfaceParams param);
}
