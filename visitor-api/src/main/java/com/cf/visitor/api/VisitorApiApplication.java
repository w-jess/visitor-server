package com.cf.visitor.api;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.cf.support.swagger.EnableJdSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJdSwagger2
@ComponentScan(basePackages = {"com.cf"})
@MapperScan("com.cf.visitor.dao.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class VisitorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitorApiApplication.class, args);
	}

}
