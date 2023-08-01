package com.base.basic.app.service.impl;

import com.base.basic.app.service.RoleService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.entity.v1.DbCache;
import com.base.basic.domain.entity.v1.Menu;
import com.base.basic.domain.entity.v1.Role;
import com.base.basic.domain.entity.v1.Scheduled;
import com.base.basic.infra.constant.BaseConstants;
import com.base.basic.infra.mapper.MenuMapper;
import com.base.basic.infra.mapper.RoleMapper;
import com.base.basic.infra.mapper.ScheduledMapper;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    @SuppressWarnings("all")
    private RoleMapper roleMapper;
    @Autowired
    @SuppressWarnings("all")
    private MenuMapper menuMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean enable(Long roleId){
        Role role = new Role();
        role.setRoleId(roleId);
        role.setEnabledFlag(Boolean.TRUE);
        roleMapper.updateOptional(role,Role.FIELD_ENABLED_FLAG);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disabled(Long roleId){
        Role role = new Role();
        role.setRoleId(roleId);
        role.setEnabledFlag(Boolean.FALSE);
        roleMapper.updateOptional(role,Role.FIELD_ENABLED_FLAG);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean roleMenuSave(Role role){
        // menuIds 要保存的菜单ID
        Set<Long> menuIds = new HashSet<>(8);
        for(String menuId:role.getMenuIds().split(",")){
            menuIds.add(Long.valueOf(menuId));
        }

        List<Menu> menuInfoList = menuMapper.select(new Menu(BaseConstants.menuType.MENU_INFO, null));



        roleMapper.updateOptional(role,Role.FIELD_MENU_IDS);
        return Boolean.TRUE;
    }

    /**
     *
     * @param count 当前递归次数
     * @param maxCount 最大递归次数
     * @param allMenuInfos
     * @param childMenus 子菜单
     */
    private void childMenuIds(int count, int maxCount , List<Menu> allMenuInfos, Menu childMenus){
        if(count >= maxCount)return ;


    }
}
