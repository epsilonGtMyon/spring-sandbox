package epsilongtmyon.app.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import epsilongtmyon.app.shared.security.MyUserDetailsService;

/*
 * @EnableWebSecurityをつけて
 * WebSecurityConfigurerAdapter をつける
 *
 * DelegatingFilterProxy というFilterからFilterChainProxyが呼ばれる
 * ここがセキュリティ関連のスタート地点
 *
 * そこからリクエストに適した SecurityFilterChainが適用される
 * SecurityFilterChainの中にはセキュリティ用のフィルターがまとめられている。
 *
 * 直接Filterのパイプラインに組み込まれてないことで
 * ・URLパターンでのマッチング以外もできるようになる
 * ・単一のエントリーポイントにしておくとデバッグしやすい
 * などの利点があるらしい
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * これをオーバーライドして設定する
	 *
	 * Remember-Meメモ
	 *
	 * rememberMe()を呼ぶと有効化される
	 * ログイン成功時にトークン発行がされるみたい(ユーザー/パスワードの認証だとsuccessfulAuthentication)
	 * AbstractRememberMeServices#rememberMeRequested を見たらわかるがリクエストのパラメータにremember-meがあってtrueになる値であれば みなすらしい
	 * 細かい設定はRememberMeConfigurer
	 *
	 *
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//ここの細かい設定方法は よくわからんのでこれからの課題
		http
				.authorizeRequests()
				.antMatchers("/api/public/*").permitAll()
				.anyRequest().authenticated()

				.and()
				.formLogin()
					.loginPage("/login").permitAll()
				.and()

					.rememberMe()

		//loginPageを省略した場合は
		//DefaultLoginPageGeneratingFilter でログインページが生成される

		;

		//BASIC認証するとき
		//http.httpBasic();

	}

	@Bean
	public MyUserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}
}
