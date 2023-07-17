package com.base.basic.app.service.impl;

import com.base.basic.app.service.RoleService;
import com.base.basic.domain.entity.v1.DbCache;
import com.base.basic.domain.entity.v1.Role;
import com.base.basic.domain.entity.v1.Scheduled;
import com.base.basic.infra.mapper.RoleMapper;
import com.base.basic.infra.mapper.ScheduledMapper;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    @SuppressWarnings("all")
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> pageList(PageParmaters pageParmaters, Role searchBody){
        return PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> roleMapper.list(searchBody));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role add(Role role){
        roleMapper.insertSelective(role);
        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role edit(Role role){
        roleMapper.updateByIdSelective(role);
        return role;
    }
}
