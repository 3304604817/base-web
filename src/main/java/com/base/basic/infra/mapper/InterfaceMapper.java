package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v1.Interface;
import com.base.common.util.mybatis.mapper.SupperMapper;

import java.util.List;

public interface InterfaceMapper extends SupperMapper<Interface> {

    List<Interface> list(Interface param);
}
