package com.base.common.autoconfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.base"})
public class BaseWebAutoConfiguration {
    public BaseWebAutoConfiguration() {
    }
}
