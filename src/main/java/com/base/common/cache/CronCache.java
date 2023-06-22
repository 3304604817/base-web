package com.base.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CronCache {
    /**
     * 存储配置缓存信息
     */
    private static Map<String, String> cron = new ConcurrentHashMap<>();

    public static Map<String, String> getCron() {
        return cron;
    }

    public static void setCron(Map<String, String> cron) {
        CronCache.cron = cron;
    }
}
