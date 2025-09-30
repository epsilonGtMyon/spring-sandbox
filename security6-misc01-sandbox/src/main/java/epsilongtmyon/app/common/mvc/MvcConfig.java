package epsilongtmyon.app.common.mvc;

import java.util.function.Consumer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		Consumer<String> addIndex = name -> {
			final String path = "/" + name;
			final String index = name + "/index";

			registry.addViewController(path).setViewName(index);
		};
		
		
		addIndex.accept("home");

	}

}
