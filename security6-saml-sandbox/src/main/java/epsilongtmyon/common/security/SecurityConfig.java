package epsilongtmyon.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(r -> {

			r.requestMatchers("/sandbox01/**").authenticated();
			r.anyRequest().permitAll();
		});

		// loginPageの設定はいらないよね
		http.saml2Login(Customizer.withDefaults());
		
		
		// メタデータを公開するならば
//		http.saml2Metadata(
//				Customizer.withDefaults());
		
		
		// SLO使わない場合
		http.logout(c -> {
			c.logoutSuccessUrl("/home");
		});
		
		
		http.formLogin(AbstractHttpConfigurer::disable);
		http.httpBasic(AbstractHttpConfigurer::disable);

		return http.build();
	}
}
