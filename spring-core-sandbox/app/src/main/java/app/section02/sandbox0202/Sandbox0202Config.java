package app.section02.sandbox0202;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Sandbox0202Config {

	@Bean
	public Sandbox0202Hoge sandbox29Hoge() {
		return new Sandbox0202Hoge();
	}
}
