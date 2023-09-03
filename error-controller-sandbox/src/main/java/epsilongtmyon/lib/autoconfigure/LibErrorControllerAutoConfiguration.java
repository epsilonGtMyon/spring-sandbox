package epsilongtmyon.lib.autoconfigure;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

import epsilongtmyon.lib.ControllerDateHolder;
import epsilongtmyon.lib.error.LibErrorController;

@AutoConfiguration(before = ErrorMvcAutoConfiguration.class)
public class LibErrorControllerAutoConfiguration {

	@Bean
	LibErrorController libErrorController(
			ErrorAttributes errorAttributes,
			ObjectProvider<ErrorViewResolver> errorViewResolvers,
			ControllerDateHolder controllerDateHolder,
			ServerProperties serverProperties) {
		return new LibErrorController(errorAttributes, serverProperties.getError(),
				errorViewResolvers.orderedStream().toList(), controllerDateHolder);
	}

	@Bean
	@RequestScope // スコープはこちらにつける
	ControllerDateHolder controllerDateHolder() {
		return new ControllerDateHolder();
	}
}
