package com.base.basic.app.service;

import com.base.basic.domain.vo.v0.CurrentUserVO;
import com.github.pagehelper.PageInfo;
import com.base.basic.domain.entity.v0.IamUser;

import java.util.List;

public interface UserService {
    CurrentUserVO currentUser();

    PageInfo<IamUser> list(IamUser dto, int page, int size);

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
