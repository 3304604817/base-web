package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.DbCache;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

public interface DbCacheService {

    PageInfo<DbCache> pageList(PageParmaters pageParmaters, DbCache searchBody);

    DbCache update(DbCache dbCache);

    void delete(DbCache dbCache);

    /**
     * 刷新所有缓存
     */
    void all();

    /**
     * 刷新数据库前缀缓存
     */
    void dbPrefix();

    /**
     * 刷新配置缓存
     */
    void config();
}
