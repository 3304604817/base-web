package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.DbCache;
import com.base.basic.domain.entity.v1.Role;
import com.base.basic.domain.entity.v1.Scheduled;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

public interface RoleService {

    PageInfo<Role> pageList(PageParmaters pageParmaters, Role searchBody);

    Role add(Role role);

    Role edit(Role role);

    /**
     * 冻结
     * @return
     */
    Boolean enable(Long roleId);

    /**
     * 解冻
     * @return
     */
    Boolean disabled(Long roleId);
}
