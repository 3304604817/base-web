package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v0.IamUser;
import com.base.common.util.mybatis.mapper.SupperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends SupperMapper<IamUser> {
    List<IamUser> list(IamUser dto);

    IamUser detail(@Param("userId") Long userId);
}
