package com.example.mfasandbox2.app.common.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 認証結果をJSON本文で返す {@link AuthenticationFailureHandler} の実装
 */
public class JsonBodyAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());

		final ObjectMapper mapper = new ObjectMapper();

		JsonBodyAuthenticationResult r = new JsonBodyAuthenticationResult();
		r.authenticationResult = false;

		PrintWriter pw = response.getWriter();
		pw.print(mapper.writeValueAsString(r));
	}
}
