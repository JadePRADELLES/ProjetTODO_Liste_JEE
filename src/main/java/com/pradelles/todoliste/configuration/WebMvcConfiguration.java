package com.pradelles.todoliste.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("connection");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("styleBootstrap/jquery/**").addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.3.1-1/");
		registry.addResourceHandler("styleBootstrap/popper/**").addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/1.14.1/umd/");
		registry.addResourceHandler("styleBootstrap/bootstrap/**").addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/4.1.1/");
	}

}
