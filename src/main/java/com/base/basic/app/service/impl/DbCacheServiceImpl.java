package com.base.basic.app.service.impl;

import com.base.basic.app.service.DbCacheService;
import com.base.basic.domain.entity.v1.DbCache;
import com.base.basic.infra.constant.CacheConstants;
import com.base.basic.infra.mapper.DbCacheMapper;
import com.base.common.cache.ConfigCache;
import com.base.common.cache.DbPreCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class DbCacheServiceImpl implements DbCacheService {

    @Autowired
    @SuppressWarnings("all")
    private DbCacheMapper dbCacheMapper;

    @Override
    public void all(){
        this.dbPrefix();
        this.config();
    }

    @Override
    public void dbPrefix(){
        DbCache dbCache = new DbCache();
        dbCache.setCacheType(CacheConstants.cacheType.DB_PREFIX);
        List<DbCache> dbCaches = dbCacheMapper.select(dbCache);

        Map<String, String> prepare = new ConcurrentHashMap<>();
        for(DbCache cache:dbCaches){
            prepare.put(cache.getCacheKey(), cache.getCacheValue());
        }
        DbPreCache.setPrepare(prepare);
    }

    @Override
    public void config(){
        DbCache dbCache = new DbCache();
        dbCache.setCacheType(CacheConstants.cacheType.CONFIG);
        List<DbCache> dbCaches = dbCacheMapper.select(dbCache);

        /**
         * 查是否有初始化默认配置
         * 有不做操作，无则新建
         */
        List<DbCache> defaultCaches = new ArrayList<>(8);
        Map<String, String> exitDefaultCaches = dbCaches.stream().collect(Collectors.toMap(DbCache::getCacheKey, DbCache::getCacheValue));
        if(null == exitDefaultCaches.get("apiAudit")){
            defaultCaches.add(new DbCache(CacheConstants.cacheType.CONFIG, "apiAudit", "1", "是否开启API请求日志：1开启，0关闭"));
        }
        defaultCaches.stream().forEach(defaultCache->{
            dbCacheMapper.insertSelective(defaultCache);
        });

        if(defaultCaches.size() > 0){
            dbCaches.addAll(defaultCaches);
        }
        Map<String, String> config = new ConcurrentHashMap<>();
        for(DbCache cache:dbCaches){
            config.put(cache.getCacheKey(), cache.getCacheValue());
        }
        ConfigCache.setConfig(config);
    }
}
