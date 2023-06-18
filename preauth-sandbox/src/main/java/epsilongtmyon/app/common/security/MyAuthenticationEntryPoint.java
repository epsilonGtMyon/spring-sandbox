package epsilongtmyon.app.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

/**
 * 未認証時に行われる処理
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static Logger logger = LoggerFactory.getLogger(MyAuthenticationEntryPoint.class);

	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.info("commence!");

		this.redirectStrategy.sendRedirect(request, response, "/unauthenticated");
	}

}
