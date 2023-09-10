package epsilongtmyon.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/sandbox01/**").setViewName("sandbox01/index");
		registry.addViewController("/sandbox02/**").setViewName("sandbox02/index");
	}

}
