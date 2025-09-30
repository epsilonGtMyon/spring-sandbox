package epsilongtmyon.app.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.with(new MySecurityConfigurerAdapter(), c -> {
			// Thread.dumpStack();
			
			c.setHoge("HogeHoge");
		});

		return http.build();
	}
	
	
	@Bean
	MySecurityBeanPostProcessor mySecurityBeanPostProcessor() {
		return new MySecurityBeanPostProcessor();
	}
}
