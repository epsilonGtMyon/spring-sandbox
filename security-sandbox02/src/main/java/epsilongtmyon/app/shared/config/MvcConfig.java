package epsilongtmyon.app.shared.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		mapToIndexView(registry, "login", "home", "sandbox01");
	}

	private void mapToIndexView(ViewControllerRegistry registry, String... paths) {
		for (String path : paths) {

			String p = "/" + path;
			String viewName = path + "/index";

			registry.addViewController(p).setViewName(viewName);
		}
	}
}
