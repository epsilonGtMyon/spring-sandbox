package com.example.mfasandbox.app.common.security;

import java.io.IOException;
import java.io.Serializable;
import java.io.UncheckedIOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;


public class MfaUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		UsernamePasswordAuthenticationToken authRequest = createToken(request, response);
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private MfaAuthenticationToken createToken(HttpServletRequest request, HttpServletResponse response) {
		final ObjectMapper mapper = new ObjectMapper();
		final AuthRequestBody readValue;
		try {
			readValue = mapper.readValue(request.getInputStream(), AuthRequestBody.class);
			final String username = readValue.username;
			final String password = readValue.password;
			final String authCode = readValue.authCode;

			return new MfaAuthenticationToken(username, password, authCode);
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
		
		public String authCode;
	}
}
