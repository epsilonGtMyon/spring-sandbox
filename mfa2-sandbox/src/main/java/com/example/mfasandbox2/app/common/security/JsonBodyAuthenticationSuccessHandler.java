package com.example.mfasandbox2.app.common.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.mfasandbox2.app.common.security.users.MfaUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 認証結果をJSON本文で返す {@link AuthenticationSuccessHandler} の実装
 */
public class JsonBodyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());

		final UsernamePasswordAuthenticationToken currentToken = (UsernamePasswordAuthenticationToken) authentication;

		final MfaUserDetails details = (MfaUserDetails) authentication.getPrincipal();
		final boolean mfaRequired = details.isMfaRequired();

		if (mfaRequired) {
			//
		} else {
			// 認証する必要ないものは ログイン済みのロール追加しておく
			UsernamePasswordAuthenticationToken newToken = UsernamePasswordAuthenticationTokens.login(currentToken);
			SecurityContextHolder.getContext().setAuthentication(newToken);
		}

		final ObjectMapper mapper = new ObjectMapper();

		JsonBodyAuthenticationResult r = new JsonBodyAuthenticationResult();
		r.authenticationResult = true;
		r.mfaRequired = mfaRequired;

		PrintWriter pw = response.getWriter();
		pw.print(mapper.writeValueAsString(r));
	}

}
