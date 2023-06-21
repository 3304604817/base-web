package com.base.basic.app.service.impl;

import com.base.basic.app.service.DbCacheService;
import com.base.basic.domain.entity.v1.DbCache;
import com.base.basic.infra.constant.CacheConstants;
import com.base.basic.infra.mapper.DbCacheMapper;
import com.base.common.cache.ConfigCache;
import com.base.common.cache.DbPreCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

        Map<String, String> config = new ConcurrentHashMap<>();
        for(DbCache cache:dbCaches){
            config.put(cache.getCacheKey(), cache.getCacheValue());
        }
        ConfigCache.setConfig(config);
    }
}
