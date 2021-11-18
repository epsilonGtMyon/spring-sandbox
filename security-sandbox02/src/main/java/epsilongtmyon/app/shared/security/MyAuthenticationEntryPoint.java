package epsilongtmyon.app.shared.security;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		//		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");

		if (Objects.equals(request.getHeader("X-Requested-With"), "XMLHttpRequest")) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.addHeader("X-Reason", "1");
			return;
		}

		this.redirectStrategy.sendRedirect(request, response, "/login");
	}

}
