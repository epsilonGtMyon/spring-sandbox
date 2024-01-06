package epsilongtmyon.app.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import epsilongtmyon.app.common.web.mvc.interceptor.SandboxInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	//WebMvcConfigurationSupport#getInterceptorsから呼ばれている
	// ここでConversionServiceExposingInterceptor, ResourceUrlProviderExposingInterceptorが設定される
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//addInterceptorsPattern1(registry);
		addInterceptorsPattern2(registry);
	}
	
	private void addInterceptorsPattern1(InterceptorRegistry registry) {
		//registry.addInterceptor(new SkipHandlerInterceptor());
		
		// orderを指定することができる、 デフォルトは0
		// 詳細はInterceptorRegistrationを参照
		registry.addInterceptor(new SandboxInterceptor("interceptor 1")).order(5);
		registry.addInterceptor(new SandboxInterceptor("interceptor 2")).order(4);
		

		registry.addInterceptor(new SandboxInterceptor("include")).addPathPatterns("/sandbox01/get/**");
		registry.addInterceptor(new SandboxInterceptor("exclude")).excludePathPatterns("/sandbox01/get/**", "/error/**");
	}
	
	//-----------------------------------------------
	private void addInterceptorsPattern2(InterceptorRegistry registry) {
		
		HandlerInterceptorRegistrationCustomizer customizer = handlerInterceptorRegistrationCustomizer();
		
		
		customizer.customize(registry, new SandboxInterceptor("interceptor1"));
		customizer.customize(registry, new SandboxInterceptor("interceptor2"));
		customizer.customize(registry, new SandboxInterceptor("include"));
		customizer.customize(registry, new SandboxInterceptor("exclude"));
		
		
	}
	
	@Bean
	HandlerInterceptorRegistrationCustomizer handlerInterceptorRegistrationCustomizer() {
		return new HandlerInterceptorRegistrationCustomizer() {

			@Override
			public void customize(InterceptorRegistry registry, HandlerInterceptor interceptor) {
				// とりあえず必ず登録するように
				InterceptorRegistration registration = registry.addInterceptor(interceptor);
				
				if(interceptor instanceof SandboxInterceptor sInterceptor) {
					String value = sInterceptor.getValue();
					
					switch(value) {
						case "interceptor1"-> registration.order(2);
						case "include"-> registration.addPathPatterns("/sandbox01/get/**");
						case "exclude"-> registration.excludePathPatterns("/error/**");
					}
				}
			}
		};
	}
}
