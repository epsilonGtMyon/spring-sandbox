package app.sandbox21.foo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("foo")
public class Sandbox21FooConfig {

	@Bean
	public Sandbox21FooExecutor sandbox21FooExecutor() {
		return new Sandbox21FooExecutor();
	}
}
