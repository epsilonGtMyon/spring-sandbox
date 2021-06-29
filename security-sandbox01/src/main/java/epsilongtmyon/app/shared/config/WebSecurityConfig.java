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
	 * -------------------
	 * Remember-Meメモ
	 *
	 * rememberMe()を呼ぶと有効化される
	 * ログイン成功時にcookieが発行がされるみたい
	 * (ユーザー/パスワードの認証だとsuccessfulAuthenticationで
	 * でRememberMeServices#loginSuccess)
	 * AbstractRememberMeServices#rememberMeRequested を見たらわかるがリクエストのパラメータにremember-meがあってtrueになる値であれば みなすらしい
	 * 細かい設定はRememberMeConfigurer
	 *
	 * セッション切れてからRememberMeで再ログインする処理は
	 * RememberMeAuthenticationFilterで行われる
	 * RememberMeServices#autoLogin で AuthenticationManagerに渡すようのtokenが作られる
	 * つくり方はRememberMeServicesの実装による
	 *
	 * 認証はRememberMeAuthenticationProviderでキーハッシュの比較をするだけ
	 *
	 * RememberMeServices
	 * ・TokenBasedRememberMeServices
	 * デフォルトはこっち (PersistentTokenRepositoryがセットされてないならこちらが使われる)
	 * onLoginSuccessでusername、有効期限、ハッシュ済パスワード、キーからMD5を使って署名を生成しcookieにセット
	 * autoLoginの時はcookieのユーザー名をもとにUserDetailsServiceからUserDetailsを取得して
	 * 照合とかをする感じ
	 *
	 * ・PersistentTokenBasedRememberMeServices
	 * (PersistentTokenRepositoryをセットした場合はこちらが使われる)
	 * TokenBasedRememberMeServicesだとユーザー名やパスワード(のハッシュ)の断片的な情報がcookieに含まれるが
	 * こちらはそうではない
	 * onLoginSuccess でPersistentTokenRepository にトークンとしてユーザー名などを保存するが
	 * そのキー情報にはランダムに生成した値を使いcookieにはそれをセットする
	 * autoLoginの時にはcookieのキー情報をもとにPersistentTokenRepositoryからトークンを取得し
	 * トークのユーザー名を使ってUserDetailsServiceからUserDetailsを取得する
	 * という流れ
	 *
	 * PersistentTokenRepositoryの実装は
	 * ・InMemoryTokenRepositoryImpl(インメモリ)
	 * ・JdbcTokenRepositoryImpl(データベース)
	 * が用意されている。
	 * -------------------
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
