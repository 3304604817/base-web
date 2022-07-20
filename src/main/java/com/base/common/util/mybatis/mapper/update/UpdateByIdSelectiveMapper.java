package com.base.common.util.mybatis.mapper.update;

import com.base.common.util.mybatis.provider.UpdateExpandProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * @author gaoyang
 * 根据ID更新，剔除 tk.mybatis Version 版本控制
 */
@RegisterMapper
public interface UpdateByIdSelectiveMapper<T> {

    @UpdateProvider(
            type = UpdateExpandProvider.class,
            method = "dynamicSQL"
    )
    int updateByIdSelective(T var1);
}
