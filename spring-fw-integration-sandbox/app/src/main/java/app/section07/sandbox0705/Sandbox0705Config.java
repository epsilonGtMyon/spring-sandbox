package app.section07.sandbox0705;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
public class Sandbox0705Config {

	@Bean
	public Sandbox0705Hoge sandbox0705Hoge() {
		return new Sandbox0705Hoge();
	}
}
