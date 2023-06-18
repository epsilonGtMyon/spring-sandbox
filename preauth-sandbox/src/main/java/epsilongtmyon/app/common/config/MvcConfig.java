package epsilongtmyon.app.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index/index");
		registry.addViewController("/page1").setViewName("page1/index");

		// エラーページ
		registry.addViewController("/unauthenticated").setViewName("errors/unauthenticated");
	}
}
