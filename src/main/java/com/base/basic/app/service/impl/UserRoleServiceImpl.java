package com.base.basic.app.service.impl;

import com.base.basic.app.service.UserRoleService;
import com.base.basic.domain.entity.v1.UserRole;
import com.base.basic.infra.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    @SuppressWarnings("all")
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(UserRole userRole){
        UserRole delUserRole = new UserRole();
        delUserRole.setRoleId(userRole.getRoleId());
        userRoleMapper.delete(delUserRole);
        for(Long userId:userRole.getUserIds()){
            UserRole userRoleDTO = new UserRole();
            userRoleDTO.setRoleId(userRole.getRoleId());
            userRoleDTO.setUserId(userId);
            userRoleMapper.insertSelective(userRoleDTO);
        }
        return Boolean.TRUE;
    }
}
