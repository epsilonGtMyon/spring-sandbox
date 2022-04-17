package app.sandbox20.sub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Sandbox20SubConfiguration {

	@Bean
	public Sandbox20Foo sandbox20Foo() {
		return new Sandbox20Foo();
	}
}
