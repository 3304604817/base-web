package com.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableOpenApi  // 开启 swagger3
@EnableScheduling
@SpringBootApplication
@MapperScan(value = {"com.base.basic.infra.mapper", "com.base.srm.infra.mapper"}) // Mybatis Mapper接口文件位置，不用写@Mapper注解
public class BasicApplication {
	private static Logger logger = LoggerFactory.getLogger(BasicApplication.class);

	public static void main(String[] args) {
		try{
			SpringApplication app = new SpringApplication(BasicApplication.class);
			app.run(args);
			logger.info("swagger3 {}", "http://localhost/swagger-ui/index.html#/");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
