package epsilongtmyon.lib.mvc;

import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;

@FunctionalInterface
public interface LibPathMatchCustomizer {

	void customize(PathMatchConfigurer configurer);
}
