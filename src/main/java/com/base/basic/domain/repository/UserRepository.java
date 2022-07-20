package com.base.basic.domain.repository;

import com.base.basic.domain.entity.v0.IamUser;
import com.base.common.util.excel.EasyOperaInterface;

import java.util.List;

public interface UserRepository extends EasyOperaInterface {
    List<IamUser> list(IamUser dto);

    IamUser detail(Long userId);

    IamUser insert(IamUser iamUser);

    List<IamUser> batchInsert(List<IamUser> iamUsers);

    IamUser update(IamUser iamUser);

    void batchDelete(List<IamUser> iamUsers);
}
