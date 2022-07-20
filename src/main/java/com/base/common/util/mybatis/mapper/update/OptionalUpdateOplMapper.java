package com.base.common.util.mybatis.mapper.update;

import com.base.common.util.mybatis.helper.OptionalHelper;
import com.base.common.util.mybatis.provider.UpdateExpandProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.Arrays;

/**
 * @author gaoyang
 * 根据ID主键更新指定字段
 */
@RegisterMapper
public interface OptionalUpdateOplMapper<T> {
    /**
     * Optimistic Lock 指定更新 字段 (基于乐观锁版本控制)
     * @param record
     * @return
     */
    @UpdateProvider(type = UpdateExpandProvider.class, method = "dynamicSQL")
    int updateOptionalOpl(T record);

    default int updateOptionalOpl(T record, String... optionals){
        OptionalHelper.optional(Arrays.asList(optionals));
        return updateOptionalOpl(record);
    }
}
