package epsilongtmyon.app.shared.security;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
	protected static final Log logger = LogFactory.getLog(MyAccessDeniedHandler.class);

	private final AccessDeniedHandlerImpl delegate = new AccessDeniedHandlerImpl();
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (response.isCommitted()) {
			logger.trace("Did not write to response since already committed");
			return;
		}

		if (accessDeniedException instanceof MissingCsrfTokenException) {
			// セッション切れた時にCsrfFilterで起きる

			if (isAjax(request)) {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.addHeader("X-Reason", "1");
			} else {
				this.redirectStrategy.sendRedirect(request, response, "/login");
			}

			return;
		}

		//それ以外

		if (isAjax(request)) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.addHeader("X-Reason", "2");
		} else {
			delegate.handle(request, response, accessDeniedException);
		}

	}

	private static boolean isAjax(HttpServletRequest request) {
		return Objects.equals(request.getHeader("X-Requested-With"), "XMLHttpRequest");
	}

}
