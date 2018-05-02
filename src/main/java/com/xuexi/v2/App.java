package com.xuexi.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextListener;

import com.xuexi.v2.conf.WebProperties;


@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableConfigurationProperties(WebProperties.class)
// @EnableAutoConfiguration
@ComponentScan(value = "com.xuexi")
 
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	// 增加此代码是因为 com.xuexi.conf.LogAspect
	// 会用到RequestContextHolder，不然会报空指针。springMVC则是通过配置文件配置
	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
}
