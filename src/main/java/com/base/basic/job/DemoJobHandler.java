package com.base.basic.job;

import com.base.common.cache.DbPreCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yang.gao
 * @description
 * @date 2022/7/28 17:48
 */
@Component
public class DemoJobHandler {
    private Logger logger = LoggerFactory.getLogger(DemoJobHandler.class);

    @Scheduled(cron = "*/30 * * * * ?")
    public void run() {
        logger.info("执行调度任务");
//
//        Map<String, String> prepare = DbPreCache.getPrepare();
//
//
//        prepare.put("1", "2");
//
//        DbPreCache.setPrepare(prepare);
    }
}
