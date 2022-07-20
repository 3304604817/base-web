package com.base.basic.domain.repository;

import com.base.basic.domain.entity.v1.DataDictionary;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

/**
 * 数据字典资源库
 */
public interface DataDictionaryRepository {

    /**
     * 分页获取数据字典
     */
    PageInfo<DataDictionary> pageList(DataDictionary parameters, PageParmaters pageParmaters);
}
