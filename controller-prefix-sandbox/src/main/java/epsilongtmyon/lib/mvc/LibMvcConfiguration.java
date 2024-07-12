package epsilongtmyon.lib.mvc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LibMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {

		// PathMatchConfigurer#addPathPrefix で
		// Controllerのパスにプレフィックスをつける
		libPathMatchCustomizer().customize(configurer);
		
		// ↑メソッド呼び出しでもConditionalOnMissingBeanが効くみたい..
	}

	// libとしては 共通でapiをつけるようにした(ErrorController以外)
	@Bean
	@ConditionalOnMissingBean
	LibPathMatchCustomizer libPathMatchCustomizer() {
		return (configurer) -> {
			configurer.addPathPrefix("/api", clazz -> {
				if (ErrorController.class.isAssignableFrom(clazz)) {
					return false;
				}

				return true;
			});
		};
	}

}
