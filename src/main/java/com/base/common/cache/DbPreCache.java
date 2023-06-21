package com.base.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库前缀缓存
 */
public class DbPreCache {
    /**
     * 存储数据库表前缀信息
     * key 表名
     * value 库名
     */
    private static Map<String, String> prepare = new ConcurrentHashMap<>();

    public static Map<String, String> getPrepare() {
        return prepare;
    }

    public static void setPrepare(Map<String, String> prepare) {
        DbPreCache.prepare = prepare;
    }
}
