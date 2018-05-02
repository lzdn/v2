package com.xuexi.v2.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Autowired
	private SystemIntercepter systemIntercepter;

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 将所有/static/** 访问都映射到classpath:/static/ 目录下
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(systemIntercepter);
	}
}
