package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.UserRole;

public interface UserRoleService {

    /**
     * 保存用户角色关系
     * @param userRole
     * @return
     */
    Boolean save(UserRole userRole);
}
