package com.base.common.autoconfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@ComponentScan({"com.base"})
public class BaseWebAutoConfiguration {
    public BaseWebAutoConfiguration() {
    }
}
