package epsilongtmyon.shared.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import epsilongtmyon.shared.handler.interceptor.MyHandlerInterceptor;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new MyHandlerInterceptor())
		//これを呼ぶとMappedInterceptorにラップされる
		//			.addPathPatterns("/sandbox01/*")
		;
	}

}
