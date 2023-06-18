package epsilongtmyon.app.common.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// JeeConfigurerを参考に
public class MyPreAuthenticatedProcessingFilterConfigurer<H extends HttpSecurityBuilder<H>>
		extends AbstractHttpConfigurer<MyPreAuthenticatedProcessingFilterConfigurer<H>, H> {

	private AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService;

	@Override
	public void init(H http) {
		PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
		authenticationProvider.setPreAuthenticatedUserDetailsService(getUserDetailsService());
		authenticationProvider = postProcess(authenticationProvider);

		http.authenticationProvider(authenticationProvider)
				.setSharedObject(AuthenticationEntryPoint.class, new Http403ForbiddenEntryPoint());
	}

	@Override
	public void configure(H http) {
		MyPreAuthenticatedProcessingFilter preAuthFilter = getFilter(http.getSharedObject(AuthenticationManager.class));

		http.addFilterAfter(preAuthFilter, UsernamePasswordAuthenticationFilter.class);
	}

	private AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> getUserDetailsService() {
		return (this.authenticationUserDetailsService != null) ? this.authenticationUserDetailsService
				: new PreAuthenticatedGrantedAuthoritiesUserDetailsService();
	}

	// フィルターを作成
	private MyPreAuthenticatedProcessingFilter getFilter(AuthenticationManager authenticationManager) {
		MyPreAuthenticatedProcessingFilter preAuthFilter = new MyPreAuthenticatedProcessingFilter();
		preAuthFilter.setAuthenticationManager(authenticationManager);
		
		// このMatcherはもしかしたらPreAuthenticatedProcessingRequestMatcherも見ておいたほうがいいかも
		preAuthFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/", "GET"));

		return preAuthFilter;
	}

	/**
	 * AuthenticationUserDetailsServiceを設定
	 * 
	 * @param authenticationUserDetailsService
	 * @return
	 */
	public MyPreAuthenticatedProcessingFilterConfigurer<H> authenticationUserDetailsService(
			AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService) {
		this.authenticationUserDetailsService = authenticationUserDetailsService;
		return this;
	}
}
