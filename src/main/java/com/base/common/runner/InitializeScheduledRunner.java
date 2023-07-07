package com.base.common.runner;

import com.base.basic.domain.entity.v1.Scheduled;
import com.base.basic.infra.mapper.ScheduledMapper;
import com.base.common.scheduled.CronTaskRegistrar;
import com.base.common.scheduled.SchedulingRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Order(3)
@Component
public class InitializeScheduledRunner implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(InitializeScheduledRunner.class);

    @Autowired
    @SuppressWarnings("all")
    private ScheduledMapper scheduledMapper;
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /**
         * 加载可运行定时任务
         */
        // 可运行定时任务
        Scheduled param = new Scheduled();
        param.setStatus(true);
        List<Scheduled> canRunScheduleds = scheduledMapper.select(param);
        for(Scheduled scheduled:canRunScheduleds){
            SchedulingRunnable task = new SchedulingRunnable(scheduled.getBeanName(), scheduled.getParam());
            cronTaskRegistrar.addCronTask(task, scheduled.getCron());
        }
        logger.info("定时任务 {} 加载完毕", canRunScheduleds.stream().map(Scheduled::getScheduledName).collect(Collectors.toList()));
    }
}
