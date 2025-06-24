package epsilongtmyon.app.common.security;

import org.opensaml.saml.saml2.core.Assertion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.saml2.provider.service.authentication.OpenSaml4AuthenticationProvider;
import org.springframework.security.saml2.provider.service.authentication.Saml2Authentication;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, MyUserDetailsService userDetailsService)
			throws Exception {
		http.formLogin(AbstractHttpConfigurer::disable);
		http.httpBasic(AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests(r -> {

			r.requestMatchers("/sandbox01/**").authenticated();
			r.anyRequest().permitAll();
		});

		http.saml2Login(saml2 -> {
			saml2
					.authenticationManager(new ProviderManager(createAuthenticationProvider(userDetailsService)));
			
			// 本当はfailureHandlerで設定して原因別に遷移させたほうがいいだろうけど、いったん雑にURLを指定
			saml2.failureUrl("/home?usernotfound");
		});
		
		// failureUrlで遷移しようとすると
		// Saml2LoginConfigurerでDefaultLoginPageGeneratingFilterが登録されており
		// failureUrlの遷移先がisErrorPageメソッドでエラーページと判定されて、このフィルターでページが生成される。
		// 今回は遷移先のページは実在するものを表示したいので、とりあえず無効にしておく
		// ※failureHandlerで設定してたらisErrorPageメソッドでエラーページと判定されることはなくなる。
		http.removeConfigurer(DefaultLoginPageConfigurer.class);

		// メタデータを公開するならば
		//		http.saml2Metadata(
		//				Customizer.withDefaults());

		// SLO使わない場合
		http.logout(c -> {
			c.logoutSuccessUrl("/home");
		});
		

		return http.build();
	}

	// https://docs.spring.io/spring-security/reference/6.5/servlet/saml2/login/authentication.html#servlet-saml2login-opensamlauthenticationprovider-userdetailsservice
	private OpenSaml4AuthenticationProvider createAuthenticationProvider(MyUserDetailsService userDetailsService) {

		OpenSaml4AuthenticationProvider authenticationProvider = new OpenSaml4AuthenticationProvider();
		authenticationProvider.setResponseAuthenticationConverter(responseToken -> {
			Saml2Authentication authentication = OpenSaml4AuthenticationProvider
					.createDefaultResponseAuthenticationConverter()
					.convert(responseToken);
			Assertion assertion = responseToken.getResponse().getAssertions().get(0);
			String username = assertion.getSubject().getNameID().getValue();
			MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(username);
			return new MySaml2Authentication(userDetails, authentication);
		});
		return authenticationProvider;
	}

	@Bean
	MyUserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}
}
