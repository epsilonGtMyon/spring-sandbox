package app.sandbox20.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import app.sandbox20.sub.Sandbox20Foo;
import app.sandbox20.sub.Sandbox20SubConfiguration;

@Configuration
@Import(Sandbox20SubConfiguration.class)
public class Sandbox20PrimaryConfig {

	@Bean
	public Sandbox20Hoge sandbox20Hoge(Sandbox20Foo sandbox20Foo) {
		return new Sandbox20Hoge(sandbox20Foo);
	}
}
