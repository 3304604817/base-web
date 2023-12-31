package com.base.common.runner;

import com.base.basic.app.service.DbCacheService;
import com.base.basic.app.service.ServerClusterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class InitializeCacheRunner implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(InitializeCacheRunner.class);

    @Autowired
    private DbCacheService dbCacheService;
    @Autowired
    private ServerClusterService serverClusterService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dbCacheService.all();
        serverClusterService.refresh();
        logger.info("初始化缓存完成");
    }
}
