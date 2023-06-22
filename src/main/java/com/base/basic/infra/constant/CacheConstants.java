package com.base.basic.infra.constant;

public interface CacheConstants {

    /**
     * 缓存类型
     */
    interface cacheType {
        // 数据库前缀缓存
        String DB_PREFIX = "dbPrefix";
        // 常规配置缓存
        String CONFIG = "config";
        // 定时任务缓存
        String CRON = "cron";
    }
}
