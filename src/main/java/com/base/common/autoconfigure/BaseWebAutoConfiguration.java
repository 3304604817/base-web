package com.base.common.autoconfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@ComponentScan({"com.base"})
@MapperScan(value = {"com.base.basic.infra.mapper"})
public class BaseWebAutoConfiguration {
    public BaseWebAutoConfiguration() {
    }
}
