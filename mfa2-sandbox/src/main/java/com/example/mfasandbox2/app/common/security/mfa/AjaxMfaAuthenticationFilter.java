package com.example.mfasandbox2.app.common.security.mfa;

import java.io.IOException;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import com.example.mfasandbox2.app.common.security.UsernamePasswordAuthenticationTokens;
import com.example.mfasandbox2.app.common.security.users.MfaUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 多要素認証用のフィルタ
 */
public class AjaxMfaAuthenticationFilter extends GenericFilterBean {

	private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/mfalogin",
			"POST");

	/** このフィルタを適用するリクエストを判定するリクエストマッチャー */
	private RequestMatcher requiresAuthenticationRequestMatcher;

	/** 認証成功時のハンドラ */
	private MfaAuthenticationSuccessHandler mfaAuthenticationSuccessHandler;

	/** 認証失敗時のハンドラ*/
	private MfaAuthenticationFailureHandler mfaAuthenticationFailureHandler;

	public AjaxMfaAuthenticationFilter() {
		setRequiresAuthenticationRequestMatcher(DEFAULT_ANT_PATH_REQUEST_MATCHER);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;

		if (!requiresMfaAuthentication(request, response)) {
			// 多要素認証対象ではない場合はそのまま後続処理を
			chain.doFilter(request, response);
			return;
		}

		if (attemptMfaAuthentication(request, response)) {
			successfulMfaAuthentication(request, response);
		} else {
			unsuccessfulMfaAuthentication(request, response);
		}

	}

	protected boolean requiresMfaAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if (!this.requiresAuthenticationRequestMatcher.matches(request)) {
			return false;
		}

		final SecurityContext context = SecurityContextHolder.getContext();
		final Authentication authentication = context.getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			// 未認証
			// cf:AuthenticationTrustResolverImpl#isAnonymous
			return false;
		}

		if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
			// 想定外のtokenが来ている
			return false;
		}
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		final Set<String> authoritySet = AuthorityUtils.authorityListToSet(authorities);
		if (authoritySet.contains("LOGINED")) {
			// 完全ログイン済み
			return false;
		}

		return true;
	}

	protected boolean attemptMfaAuthentication(HttpServletRequest request, HttpServletResponse response) {
		final SecurityContext context = SecurityContextHolder.getContext();
		final MfaUserDetails user = (MfaUserDetails) context.getAuthentication().getPrincipal();

		final MfaAuthRequestBody mfaAuthRequestBody = extractRequestBody(request);
		final String userInputAuthCode = mfaAuthRequestBody.authCode;

		final String mfaAuthCode = user.getMfaAuthCode();

		// 本来はいろいろ生成の計算とかがあるが、ここでは簡易的に値そのものを使う
		return Objects.equals(userInputAuthCode, mfaAuthCode);
	}

	private MfaAuthRequestBody extractRequestBody(HttpServletRequest request) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(request.getInputStream(), MfaAuthRequestBody.class);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	protected void successfulMfaAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		final SecurityContext context = SecurityContextHolder.getContext();

		final UsernamePasswordAuthenticationToken currentToken = (UsernamePasswordAuthenticationToken) context
				.getAuthentication();

		UsernamePasswordAuthenticationToken newToken = UsernamePasswordAuthenticationTokens.login(currentToken);
		context.setAuthentication(newToken);

		mfaAuthenticationSuccessHandler.onAuthenticationSuccess(request, response);
	}

	protected void unsuccessfulMfaAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		mfaAuthenticationFailureHandler.onMfaAuthenticationFailure(request, response);
	}

	//------------------------------------

	public void setRequiresAuthenticationRequestMatcher(RequestMatcher requestMatcher) {
		this.requiresAuthenticationRequestMatcher = requestMatcher;
	}

	public void setMfaAuthenticationSuccessHandler(MfaAuthenticationSuccessHandler mfaAuthenticationSuccessHandler) {
		this.mfaAuthenticationSuccessHandler = mfaAuthenticationSuccessHandler;
	}

	public void setMfaAuthenticationFailureHandler(MfaAuthenticationFailureHandler mfaAuthenticationFailureHandler) {
		this.mfaAuthenticationFailureHandler = mfaAuthenticationFailureHandler;
	}

	static class MfaAuthRequestBody implements Serializable {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public String authCode;
	}

}
