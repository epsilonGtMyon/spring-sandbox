package epsilongtmyon.app.common.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

public interface HandlerInterceptorRegistrationCustomizer {

	void customize(InterceptorRegistry registry, HandlerInterceptor interceptor);
}
