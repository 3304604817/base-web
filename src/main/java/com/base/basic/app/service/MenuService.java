package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.Menu;
import com.base.basic.domain.entity.v1.Role;
import com.base.basic.domain.vo.v0.MenuVO;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MenuService {

    MenuVO initMenu();

    /**
     * 分页查询菜单信息
     * @param pageParmaters
     * @param searchBody
     * @return
     */
    PageInfo<Menu> pageList(PageParmaters pageParmaters, Menu searchBody);

    /**
     * 查所有菜单树状
     * @return
     */
    List<Menu> treeList();

    /**
     * 新建菜单
     * @param menu
     * @return
     */
    Menu add(Menu menu);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    Menu update(Menu menu);
}
