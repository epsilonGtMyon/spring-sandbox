package epsilongtmyon.app.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import epsilongtmyon.app.common.handlerinterceptor.My01HandlerInterceptor;
import epsilongtmyon.app.common.handlerinterceptor.My02HandlerInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new My01HandlerInterceptor());
		//↓/errorの時は 適用しない
		registry.addInterceptor(new My02HandlerInterceptor()).excludePathPatterns("/error/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/sandbox01/").setViewName("sandbox01/index");
	}

}
