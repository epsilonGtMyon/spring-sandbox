package epsilongtmyon.app.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
 * 〇設定のクラス
 * OAuth2ClientProperties
 * 
 * 〇認証の流れ
 * 
 * AuthenticationEntryPointで
 * /oauth2/authorization/my-keycloak
 * にリダイレクトする。
 * 
 * その後
 * OAuth2AuthorizationRequestRedirectFilterでIdP側へのリダイレクトが行われる。
 * 
 * IdP側での認証後は
 * /login/oauth2/code/my-keycloak
 * にリダイレクト
 * 
 * このリクエストは
 * OAuth2LoginAuthenticationFilter
 * で認証を進める。
 * 
 * ここで前回の保存してる内容と突き合わせて、正しいレスポンスであることを検証している？
 * 
 * 認証はAuthenticationManager(ProviderManager)でされている。
 * ・OAuth2LoginAuthenticationProvider はスキップされた
 * ・OidcAuthorizationCodeAuthenticationProvider ← これがIdPに向かっていろいろやってる。
 * 
 * Principalの部分を作ってるのは OidcUserService
 * 内部にはDefaultOAuth2UserServiceとかがある。
 * あとはOidcUserService#setOidcUserMapperあたりが変換カスタマイズできそうかも
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// memo:
	// OAuth2ClientProperties
	// OAuth2AuthorizationRequestRedirectFilter こっちでリダイレクト
	// OAuth2LoginAuthenticationFilter こっちで返ってきたやつを受け取っていろいろ
	
	// Provider:
	// OAuth2LoginAuthenticationProvider
	// OidcAuthorizationCodeAuthenticationProvider こっちで認証
	
	// ユーザー情報まわり
	//  OidcUserServiceが起点
	//    そこから DefaultOAuth2UserServiceでユーザー情報取得のエンドポイントにアクセスしDefaultOAuth2Userを返す
	//  それをOidcUserRequestUtils::getUserを使ってDefaultOidcUserを返している。

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
				.oauth2Login(c -> {

					// 認証通ったあとどうするか
					c.defaultSuccessUrl("/home", true);
					

				})

				.oauth2Client(Customizer.withDefaults())

				.authorizeHttpRequests(r -> {
					r.requestMatchers("/home").permitAll();
					r.anyRequest().authenticated();
				})

		;
		return http.build();
	}
}
