package com.base;

import springfox.documentation.oas.annotations.EnableOpenApi;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableOpenApi  // swagger3   http://localhost:10010/swagger-ui/index.html#/
@SpringBootApplication
@MapperScan(value = {"com.base.basic.infra.mapper", "com.base.srm.infra.mapper"}) // Mybatis Mapper接口文件位置，不用写@Mapper注解
public class BasicApplication {

	public static void main(String[] args) {
		try{
			SpringApplication app = new SpringApplication(BasicApplication.class);
			app.run(args);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
