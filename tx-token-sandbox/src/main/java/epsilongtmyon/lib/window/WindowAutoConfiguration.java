package epsilongtmyon.lib.window;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class WindowAutoConfiguration {

	@Bean
	WindowIdResolver windowIdResolver() {
		return new WindowIdResolver();
	}
}
