package com.mcp.semi.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mcp.semi.common.interceptor.UserInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	UserInterceptor userInterceptor;

	// localhost:8080 입력시 localhost:8080/dokky/main로 리다이렉트
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/dokky/main");
		registry.addRedirectViewController("/dokky", "/dokky/main");
		registry.addRedirectViewController("/dokky/", "/dokky/main");
	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(userInterceptor)
//		.addPathPatterns("/dokky/**")
//		.excludePathPatterns(
//				"/dokky/signin",
//				"/dokky/signup",
//				"/dokky/logout",
//				"/dokky/user/**",
//				"/dokky/main",
//				"/dokky/detail",
//				"/dokky/getBoard.do",
//				"/dokky/getBoardByNo.do",
//				"/dokky/putBoardHit.do",
//				"/detail/**");
//	}
//	
	

}
