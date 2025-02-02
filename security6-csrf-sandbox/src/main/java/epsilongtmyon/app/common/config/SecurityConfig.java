package epsilongtmyon.app.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//
		http.csrf(csrf -> {
			//デフォルトはXorCsrfTokenRequestAttributeHandlerで
			//トークンは一つの値を使うがトークン生成の要求があると、ランダムな要素を付与して毎回異なった値が生成される
			//異なった値を返すが以前に返した値tokenも引き続き利用は可能なので、並列にリクエストを投げても問題にはならない 
			
			// ↓CsrfTokenRequestAttributeHandlerをセットすると以前と同様に生のTokenをクライアントへ返却する。
			//csrf.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler());
			
		});

		// 認可不要なので全部スルー
		http.authorizeHttpRequests(authorizeHttpRequests -> {
			authorizeHttpRequests.anyRequest().permitAll();
		});

		return http.build();
	}

}
