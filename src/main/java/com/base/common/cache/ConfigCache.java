package com.base.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigCache {
    /**
     * 存储配置缓存信息
     */
    private static Map<String, String> config = new ConcurrentHashMap<>();

    public static Map<String, String> getConfig() {
        return config;
    }

    public static void setConfig(Map<String, String> config) {
        ConfigCache.config = config;
    }
}
