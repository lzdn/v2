package com.xuexi.v2;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@Configuration
@ComponentScan(value = "com.xuexi.v2") // 默认扫描是当前包下的路径
@EnableAutoConfiguration
public  class AppV2 extends org.springframework.boot.web.servlet.support.SpringBootServletInitializer {
	
	// 增加此代码是因为 com.xuexi.conf.LogAspect
	// 会用到RequestContextHolder，不然会报空指针。springMVC则是通过配置文件配置
	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(1024L * 1024L);
        factory.setFileSizeThreshold(0);
       // factory.set
        return factory.createMultipartConfig();
    }
    
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppV2.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AppV2.class, args);
	}
	

}
