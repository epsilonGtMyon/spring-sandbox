package epsilongtmyon.app.shared.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import epsilongtmyon.app.shared.config.security.authentication.JsonBodyAuthenticationFailureHandler;
import epsilongtmyon.app.shared.config.security.authentication.JsonBodyAuthenticationSuccessHandler;
import epsilongtmyon.app.shared.config.security.authentication.JsonBodyLoginConfigurer;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated();

		http.apply(new JsonBodyLoginConfigurer<>())
				.loginProcessingUrl("/api/login")
				.successHandler(new JsonBodyAuthenticationSuccessHandler())
				.failureHandler(new JsonBodyAuthenticationFailureHandler());

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
				.withUser("user01").password("{noop}test01").roles("hoge")
				.and()
				.withUser("user02").password("{noop}test02").roles("hoge");
	}

}
