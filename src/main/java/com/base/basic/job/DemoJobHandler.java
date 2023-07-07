package com.base.basic.job;

import com.base.common.cache.DbPreCache;
import com.base.common.scheduled.runner.ScheduledRunner;
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
@Component("DemoJobHandler")
public class DemoJobHandler implements ScheduledRunner {
    private Logger logger = LoggerFactory.getLogger(DemoJobHandler.class);

    @Override
    public void run(String args) {
        logger.info("定时任务Demo");
    }
}
