package com.example.mfasandbox2.app.common.security.mfa;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

public class AjaxMfaAuthenticationConfigurer<H extends HttpSecurityBuilder<H>>
		extends AbstractHttpConfigurer<AjaxMfaAuthenticationConfigurer<H>, H> {

	/** このフィルタを適用するリクエストを判定するリクエストマッチャー */
	private RequestMatcher requiresAuthenticationMatcher;

	/** 認証成功時のハンドラ */
	private MfaAuthenticationSuccessHandler mfaAuthenticationSuccessHandler = new JsonBodyMfaAuthenticationSuccessHandler();

	/** 認証失敗時のハンドラ*/
	private MfaAuthenticationFailureHandler mfaAuthenticationFailureHandler = new JsonBodyMfaAuthenticationFailureHandler();

	@Override
	public void configure(H http) {
		final AjaxMfaAuthenticationFilter filter = new AjaxMfaAuthenticationFilter();

		if (requiresAuthenticationMatcher != null) {
			filter.setRequiresAuthenticationRequestMatcher(requiresAuthenticationMatcher);
		}

		filter.setMfaAuthenticationSuccessHandler(mfaAuthenticationSuccessHandler);
		filter.setMfaAuthenticationFailureHandler(mfaAuthenticationFailureHandler);

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

	public AjaxMfaAuthenticationConfigurer<H> requiresAuthenticationMatcher(
			RequestMatcher requiresAuthenticationMatcher) {
		Assert.notNull(requiresAuthenticationMatcher, "requiresAuthenticationMatcher cannot be null");
		this.requiresAuthenticationMatcher = requiresAuthenticationMatcher;
		return this;
	}

	public AjaxMfaAuthenticationConfigurer<H> mfaAuthenticationSuccessHandler(
			MfaAuthenticationSuccessHandler mfaAuthenticationSuccessHandler) {
		Assert.notNull(mfaAuthenticationSuccessHandler, "mfaAuthenticationSuccessHandler cannot be null");
		this.mfaAuthenticationSuccessHandler = mfaAuthenticationSuccessHandler;
		return this;
	}

	public AjaxMfaAuthenticationConfigurer<H> mfaAuthenticationFailureHandler(
			MfaAuthenticationFailureHandler mfaAuthenticationFailureHandler) {
		Assert.notNull(mfaAuthenticationFailureHandler, "mfaAuthenticationFailureHandler cannot be null");
		this.mfaAuthenticationFailureHandler = mfaAuthenticationFailureHandler;
		return this;
	}

}
