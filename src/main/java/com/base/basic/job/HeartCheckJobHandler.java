package com.base.basic.job;

import com.base.basic.app.service.DbCacheService;
import com.base.common.cache.ConfigCache;
import com.base.common.cache.CronCache;
import com.base.common.cache.ServerClusterCache;
import com.base.common.util.http.RestfulUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Component
public class HeartCheckJobHandler implements SchedulingConfigurer {
    private Logger logger = LoggerFactory.getLogger(HeartCheckJobHandler.class);

    @Autowired
    private DbCacheService dbCacheService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                logger.info("心跳检测");
                Set<String> addressSet = ServerClusterCache.getServerCluster().keySet();
                for(String address:addressSet){
                    int code = RestfulUtil.httpGet("http://" + address + "/cluster/heart-check", null, null, RestfulUtil.UTF8_CHARSET).getCode();
                    if(200 == code){
                        ServerClusterCache.serverUp(address);
                    }else {
                        ServerClusterCache.serverDown(address);
                    }
                }
            }
        };

        Trigger trigger = new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 任务触发，动态获取任务的执行周期
                String heartCheckCron = null == CronCache.getCron().get("heartCheckCron") ? dbCacheService.cron().get("heartCheckCron") : CronCache.getCron().get("heartCheckCron");
                CronTrigger trigger = new CronTrigger(heartCheckCron);
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        };
        scheduledTaskRegistrar.addTriggerTask(task, trigger);
    }
}
