package epsilongtmyon.app.common.security;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
	SecurityFilterChain securityFilterChain(HttpSecurity http,
			ClientRegistrationRepository clientRegistrationRepository) throws Exception {

		http
				.oauth2Login(c -> {

					// 認証通ったあとどうするか
					// これがないと遷移しようとしたページに?continueという感じになる。
					c.defaultSuccessUrl("/home", true);

					c.authorizationEndpoint(ace -> {
						// OAuth2AuthorizationRequestRedirectFilter の挙動設定

						// OAuth2LoginConfigurer#getAuthorizationRequestResolver() を参考
						String authorizationRequestBaseUri = OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI;
						DefaultOAuth2AuthorizationRequestResolver authorizationRequestResolver = new DefaultOAuth2AuthorizationRequestResolver(
								clientRegistrationRepository, authorizationRequestBaseUri);
						
						// カスタマイザーをセットしてIdPへのリクエスト時にクエリパラメータ追加
						// これがOIDCの仕様として許容されるのかは別の話
						authorizationRequestResolver.setAuthorizationRequestCustomizer(b -> {
							HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
							
							b.additionalParameters(params -> {
								// 固定値
								params.put("myParam1", "hello");
								// リクエストから値を取得
								params.put("myParam2", request.getHeader("host"));
							});
						});
						ace.authorizationRequestResolver(authorizationRequestResolver);
					});

				});

		http
				.oauth2Client(Customizer.withDefaults());

		http.logout(c -> {
			// このアプリ内でのログアウト後の遷移先
			c.logoutSuccessUrl("/afterlogout").permitAll();
		});

		http
				.authorizeHttpRequests(r -> {
					r.requestMatchers("/home").permitAll();
					r.requestMatchers("/home2").permitAll();
					r.anyRequest().authenticated();
				});
		return http.build();
	}
}
