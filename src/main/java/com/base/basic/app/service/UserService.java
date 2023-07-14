package com.base.basic.app.service;

import com.base.basic.domain.vo.v0.CurrentUserVO;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;
import com.base.basic.domain.entity.v0.IamUser;

import java.util.List;

public interface UserService {
    CurrentUserVO currentUser();

    PageInfo<IamUser> pageList(PageParmaters pageParmaters, IamUser dto);

    IamUser detail(Long userId);

    /**
     * 创建
     * @param iamUser
     * @return
     */
    IamUser insert(IamUser iamUser);

    /**
     * 批量创建用户
     * @param iamUsers
     * @return
     */
    List<IamUser> batchInsert(List<IamUser> iamUsers);

    /**
     * 重置密码
     * @param userId
     * @param password
     * @return
     */
    Boolean resetPassword(Long userId, String password);

    /**
     * 冻结
     * @return
     */
    Boolean enable(Long userId);

    /**
     * 解冻
     * @return
     */
    Boolean disabled(Long userId);

    /**
     * 更新用户信息
     * @param iamUser
     * @return
     */
    IamUser update(IamUser iamUser);

    /**
     * 批量删除用户
     * @param iamUsers
     */
    void batchDelete(List<IamUser> iamUsers);
}
