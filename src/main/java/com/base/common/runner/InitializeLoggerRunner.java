package com.base.common.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class InitializeLoggerRunner implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(InitializeLoggerRunner.class);

    @Value("${server.port}")
    private String port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("swagger3 {}", "http://localhost:" + port + "/swagger-ui/index.html#/");
    }
}
