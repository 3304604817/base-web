package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v1.UserRole;
import com.base.common.util.mybatis.mapper.SupperMapper;

import java.util.List;

public interface UserRoleMapper extends SupperMapper<UserRole> {

    List<UserRole> list(UserRole userRole);
}
