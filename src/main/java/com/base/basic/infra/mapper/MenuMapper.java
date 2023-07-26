package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v1.Menu;
import com.base.basic.domain.entity.v1.Role;
import com.base.common.util.mybatis.mapper.SupperMapper;

import java.util.List;

public interface MenuMapper extends SupperMapper<Menu> {

    List<Menu> list(Menu role);
}
