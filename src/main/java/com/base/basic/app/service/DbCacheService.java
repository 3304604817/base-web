package com.base.basic.app.service;

public interface DbCacheService {

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
