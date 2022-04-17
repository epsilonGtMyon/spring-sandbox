package app.sandbox21.defaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Sandbox21DefaultConfig {

	@Bean
	public Sandbox21Xxxx sandbox21Xxxx() {
		return new Sandbox21Xxxx();
	}
}
