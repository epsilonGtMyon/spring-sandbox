package epsilongtmyon.app.common.security;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.StringUtils;
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
 * 
 * 
 * 
 * 参考
 * ・OAuth2LoginConfigurer#init
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

					c.authorizationEndpoint(aec -> {
						// OAuth2AuthorizationRequestRedirectFilter の挙動設定

						// OAuth2LoginConfigurer#getAuthorizationRequestResolver() を参考
						String authorizationRequestBaseUri = OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI;
						DefaultOAuth2AuthorizationRequestResolver authorizationRequestResolver = new DefaultOAuth2AuthorizationRequestResolver(
								clientRegistrationRepository, authorizationRequestBaseUri);

						// カスタマイザーをセットしてIdPへのリクエスト時にクエリパラメータ追加
						// これがOIDCの仕様として許容されるのかは別の話
						authorizationRequestResolver.setAuthorizationRequestCustomizer(b -> {
							HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
									.getRequestAttributes()).getRequest();

							b.additionalParameters(params -> {
								// 固定値
								params.put("myParam1", "hello");
								// リクエストから値を取得
								params.put("myParam2", request.getHeader("host"));
							});
						});
						aec.authorizationRequestResolver(authorizationRequestResolver);
					});

					c.userInfoEndpoint(uiec -> {

						// OidcAuthorizationCodeAuthenticationProvider の挙動設定

						// ユーザー情報のマッピングまわりに関係するところ
						OidcUserService oidcUserService = new OidcUserService();
						oidcUserService.setOidcUserMapper(SecurityConfig::getUser);
						uiec.oidcUserService(oidcUserService);
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

	// OidcUserService.oidcUserMapper フィールドの初期値である OidcUserRequestUtils#getUserを参考
	private static OidcUser getUser(OidcUserRequest userRequest, OidcUserInfo userInfo) {
		Set<GrantedAuthority> authorities = new LinkedHashSet<>();
		ClientRegistration.ProviderDetails providerDetails = userRequest.getClientRegistration().getProviderDetails();
		String userNameAttributeName = providerDetails.getUserInfoEndpoint().getUserNameAttributeName();
		if (StringUtils.hasText(userNameAttributeName)) {
			authorities.add(new OidcUserAuthority(userRequest.getIdToken(), userInfo, userNameAttributeName));
		} else {
			authorities.add(new OidcUserAuthority(userRequest.getIdToken(), userInfo));
		}
		OAuth2AccessToken token = userRequest.getAccessToken();
		for (String scope : token.getScopes()) {
			authorities.add(new SimpleGrantedAuthority("SCOPE_" + scope));
		}
		if (StringUtils.hasText(userNameAttributeName)) {
			return new DefaultOidcUser(authorities, userRequest.getIdToken(), userInfo, userNameAttributeName);
		}
		return new DefaultOidcUser(authorities, userRequest.getIdToken(), userInfo);
	}
}
