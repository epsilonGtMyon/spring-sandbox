package epsilongtmyon.app.shared.config.security.authentication;

import java.io.IOException;
import java.io.Serializable;
import java.io.UncheckedIOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON形式のリクエストのボディを使う{@link UsernamePasswordAuthenticationFilter}フィルタ
 */
public class JsonBodyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		UsernamePasswordAuthenticationToken authRequest = createToken(request, response);
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private UsernamePasswordAuthenticationToken createToken(HttpServletRequest request, HttpServletResponse response) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			final AuthRequestBody readValue = mapper.readValue(request.getInputStream(), AuthRequestBody.class);
			final String username = readValue.username;
			final String password = readValue.password;

			return new UsernamePasswordAuthenticationToken(username, password);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}

	static class AuthRequestBody implements Serializable {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public String username;

		public String password;
	}
}
