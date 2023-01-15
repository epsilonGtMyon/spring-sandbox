package com.example.mfasandbox.app.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.mfasandbox.app.common.security.AjaxLoginConfigurer;
import com.example.mfasandbox.app.common.security.JsonBodyAuthenticationFailureHandler;
import com.example.mfasandbox.app.common.security.JsonBodyAuthenticationSuccessHandler;
import com.example.mfasandbox.app.common.security.users.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.authorizeRequests()
				.mvcMatchers("/login/**", "/script/**", "/style/**").permitAll()
				.anyRequest().authenticated()

		;

		http.apply(new AjaxLoginConfigurer<>())

				.loginProcessingUrl("/login")
				.successHandler(new JsonBodyAuthenticationSuccessHandler())
				.failureHandler(new JsonBodyAuthenticationFailureHandler())

		;

//		http.authenticationProvider(mfaDaoAuthenticationProvider())

		;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public MyUserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

//	@Bean
//	MfaDaoAuthenticationProvider mfaDaoAuthenticationProvider() {
//		return new MfaDaoAuthenticationProvider();
//	}

}
