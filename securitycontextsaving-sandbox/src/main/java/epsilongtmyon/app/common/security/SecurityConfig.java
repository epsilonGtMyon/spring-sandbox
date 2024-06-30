package epsilongtmyon.app.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import epsilongtmyon.app.common.security.filter.SecurityContextSavingFilterConfigurer;
import epsilongtmyon.app.common.security.filter.SecurityContextSavingMarker;
import epsilongtmyon.app.common.security.user.MyUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		//		http.securityContext(x -> {
		//          // SecurityContextPersistenceFilterを使うときはfalse
		//          // SecurityContextPersistenceFilter の時は必ずしも、sessionなどに保存されるとは限らなかった
		//          // HttpSessionSecurityContextRepository$SaveToSessionResponseWrapperのcontextChangedの条件を見ればわかるが
		//          // 変化した場合の条件に当てはまった場合だけこれが行われる。
		//			x.requireExplicitSave(false);
		//		});

		// -------------

		http.httpBasic(x -> x.disable());
		http.formLogin(x -> {
			x.loginPage("/login").permitAll();
			x.loginProcessingUrl("/login").permitAll();
			x.defaultSuccessUrl("/home", true);
		});

		// -------------
		http
				.authorizeHttpRequests(authorize -> authorize
						.anyRequest().authenticated());

		http.with(new SecurityContextSavingFilterConfigurer<>(), x -> {
		});

		DefaultSecurityFilterChain chain = http.build();
		return chain;
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new MyUserService();
	}

	@Bean
	SecurityContextSavingMarker securityContextSavingMarker() {
		return new SecurityContextSavingMarker();
	}
}
