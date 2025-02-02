package epsilongtmyon.app.common.security.endpoint.sandbox01;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

/**
 * CSRFトークンの取得を行う
 */
@Component
public class CsrfTokenProvider {

	private final HttpServletRequest request;

	public CsrfTokenProvider(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public CsrfToken getToken() {
		return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
	}

}
