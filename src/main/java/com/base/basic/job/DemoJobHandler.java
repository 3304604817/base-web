package com.base.basic.job;

import com.alibaba.fastjson.JSON;
import com.base.basic.domain.entity.v1.AuditLog;
import com.base.basic.infra.mapper.AuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
        System.out.println("执行调度任务");
    }
}
