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

		//http.addFilterAt で起きる問題を解決するために
		//JsonBodyLoginConfigurer を作成し、そちらを適用する。
		http.apply(new JsonBodyLoginConfigurer<>())
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.permitAll()
				.successHandler(new JsonBodyAuthenticationSuccessHandler())
				.failureHandler(new JsonBodyAuthenticationFailureHandler());


		//このやり方だと AbstractAuthenticationFilterConfigurer#configure が呼ばれないので
		//諸々のオブジェクトがセットされない
		//例えば、SessionAuthenticationStrategyが変更されないのでログイン成功時にセッションIDが変更されなかったりする。
		//JsonBodyUsernamePasswordAuthenticationFilter f = new JsonBodyUsernamePasswordAuthenticationFilter();
		//http.addFilterAt(f, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
				.withUser("user01").password("{noop}test01").roles("hoge")
				.and()
				.withUser("user02").password("{noop}test02").roles("hoge");
	}

}
