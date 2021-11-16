package epsilongtmyon.app.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import epsilongtmyon.app.shared.security.MyUserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests(authorize -> {
			//https://docs.spring.io/spring-security/site/docs/5.5.3/reference/html5/#jc-httpsecurity

			authorize
					// hasRoleにするとRole_XXX という権限をもっていたらOKになる
					// 認可のチェックはFilterSecurityInterceptorというフィルタで行われる
					// 権限チェックエラーの場合はAccessDeniedExceptionが投げられる
					.mvcMatchers("/sandbox01").hasRole("XXX")
					.anyRequest().authenticated();
		});


		// 認証設定
		http.formLogin()

				.loginPage("/login").permitAll()

				.loginProcessingUrl("/login").permitAll()
				//成功したら/homeに遷移
				.defaultSuccessUrl("/home", true)

		;
	}


	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}
}
