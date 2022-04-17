package app.sandbox29;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Sandbox29Config {

	@Bean
	public Sandbox29Hoge sandbox29Hoge() {
		return new Sandbox29Hoge();
	}
}
