package com.tft.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 作用在于让 Spring Boot根据应用所声明的依赖来对 Spring 框架进行自动配置
 * 这个注解告诉Spring Boot根据添加的jar依赖猜测你想如何配置Spring。
 */
@SpringBootApplication			//注解标准是springBoot的启动类
@EnableAutoConfiguration
//定义扫描范围，默认是扫描启动类同级目录下的，此处将boot放到了单独的包里面，所以需要指定扫描包
@ComponentScan(basePackages={"com.tft.**.controller","com.tft.**.impl","com.tft.**.config", "com.tft.**.socket","com.tft.**.Interceptor"})

/*****springBoot jpa相关注解 start****/
@EnableJpaRepositories(basePackages = {"com.tft.**.dao","com.tft.**.dao.impl"},
		repositoryImplementationPostfix = "Impl")
@EntityScan(basePackages = {"com.tft.**.bean"})
/*****springBoot jpa相关注解 end****/

//servlet扫描注解
@ServletComponentScan(basePackages = {"com.tft.**.servlet"})
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
}
