package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v1.UserRole;
import com.base.common.util.mybatis.mapper.SupperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper extends SupperMapper<UserRole> {

    List<UserRole> list(UserRole userRole);

    /**
     * 获取用户角色
     * @param loginName
     * @return
     */
    List<UserRole> userRole(@Param("loginName") String loginName);
}
