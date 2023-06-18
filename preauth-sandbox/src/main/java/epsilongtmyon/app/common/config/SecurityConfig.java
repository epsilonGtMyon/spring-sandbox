package epsilongtmyon.app.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import epsilongtmyon.app.common.security.MyAuthenticationEntryPoint;
import epsilongtmyon.app.common.security.MyPreAuthenticatedProcessingFilterConfigurer;
import epsilongtmyon.app.common.security.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authz -> {
			//エラーページ
			authz.requestMatchers(new AntPathRequestMatcher("/unauthenticated", "GET")).permitAll();

			// それ以外は認証済
			authz.anyRequest().authenticated();
		});

		// デフォルトはHttpSecurityConfigurationあたりでされているっぽい
		http.apply(new MyPreAuthenticatedProcessingFilterConfigurer<HttpSecurity>())
				.authenticationUserDetailsService(myUserDetailsService());

		// 例外ハンドラ
		// (ExceptionTranslationFilterで使われる)
		http.exceptionHandling(exceptionHandling -> {
			exceptionHandling.authenticationEntryPoint(new MyAuthenticationEntryPoint());
		});

		return http.build();
	}

	@Bean
	MyUserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}
}
