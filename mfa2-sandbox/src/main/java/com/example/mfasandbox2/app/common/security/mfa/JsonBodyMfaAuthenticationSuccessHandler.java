package com.example.mfasandbox2.app.common.security.mfa;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonBodyMfaAuthenticationSuccessHandler implements MfaAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setStatus(HttpServletResponse.SC_OK);

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());

		final ObjectMapper mapper = new ObjectMapper();

		JsonBodyMfaAuthenticationResult r = new JsonBodyMfaAuthenticationResult();
		r.success = true;

		PrintWriter pw = response.getWriter();
		pw.print(mapper.writeValueAsString(r));
	}

}
