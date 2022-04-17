package app.sandbox21.hoge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("hoge")
public class Sandbox21HogeConfig {

	@Bean
	public Sandbox21HogeExecutor sandbox21HogeExecutor() {
		return new Sandbox21HogeExecutor();
	}
}
