package epsilongtmyon.app.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import epsilongtmyon.lib.mvc.LibPathMatchCustomizer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home/index");
		registry.addViewController("/sandbox01/").setViewName("sandbox01/index");
		registry.addViewController("/sandboxA/").setViewName("sandboxA/index");
	}
	
	// Beanの上書き
	@Bean
	LibPathMatchCustomizer libPathMatchCustomizer() {
		return (configurer) -> {
			// Controllerのパスにプレフィックスをつける
			// ここではパッケージで分類した
			// predicate を実装する際だが、BasicErrorControllerがここに入ってくるので
			// 無条件にtrueにしてしまうとエラー用のControllerまでプレフィックスがついてしまうので注意
	
			final String commonPackagePrefix = "epsilongtmyon.app.endpoint.";
			configurer.addPathPrefix("api", cls -> {
				String name = cls.getPackage().getName();
				return name.startsWith(commonPackagePrefix + "api.");
			});
	
			configurer.addPathPrefix("api2", cls -> {
				String name = cls.getPackage().getName();
				return name.startsWith(commonPackagePrefix + "api2.");
			});
		};
	}
}
