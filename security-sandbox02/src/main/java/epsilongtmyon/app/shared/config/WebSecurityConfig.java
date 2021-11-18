package epsilongtmyon.app.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import epsilongtmyon.app.shared.security.MyAccessDeniedHandler;
import epsilongtmyon.app.shared.security.MyAuthenticationEntryPoint;
import epsilongtmyon.app.shared.security.MyUserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled  = true) //メソッドセキュリティ使うため
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

		/*
		 * 認証認可のエラーハンドリング
		 *
		 * 認可はAbstractSecurityInterceptor あたり
		 * これはFilterSecurityInterceptor, MethodSecurityInterceptorの親クラス
		 * ここからAccessDecisionManagerの実装クラスに委譲する、
		 * デフォルトはAffirmativeBased
		 *
		 * そこで認可の条件に応じたチェックがされる
		 * チェックはAccessDecisionVoter による投票でその結果に応じて決定される
		 *
		 * そのあたりでAuthenticationExceptionだったりAccessDeniedExceptionだったりが投げられる
		 * 例外はそれより前のフィルタExceptionTranslationFilterで処理され
		 * AuthenticationExceptionだったらAuthenticationEntryPointがつかわれて
		 * AccessDeniedExceptionだったらAccessDeniedHandlerが使われる
		 *
		 * ただ
		 * セッションが切れた状態でPOSTリクエストを投げたらCsrfFilterでMissingCsrfTokenExceptionがAccessDeniedHandlerにわたされるので
		 * セッション切れた対策には2か所で対応必要
		 *
		 */
		http.exceptionHandling(exceptionHandling -> {

		    //formLoginでloginPageなどを設定している場合は
		    //LoginUrlAuthenticationEntryPointが使われる
			exceptionHandling.authenticationEntryPoint(new MyAuthenticationEntryPoint());

			exceptionHandling.accessDeniedHandler(new MyAccessDeniedHandler());
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
