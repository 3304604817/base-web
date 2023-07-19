package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> list(UserRole userRole);

    /**
     * 保存用户角色关系
     * @param userRole
     * @return
     */
    Boolean save(UserRole userRole);
}
