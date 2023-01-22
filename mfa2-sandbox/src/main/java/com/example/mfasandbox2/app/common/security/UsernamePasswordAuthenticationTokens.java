package com.example.mfasandbox2.app.common.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UsernamePasswordAuthenticationTokens {

	public static UsernamePasswordAuthenticationToken login(UsernamePasswordAuthenticationToken src) {
		final List<GrantedAuthority> newAuthorities = new ArrayList<>(src.getAuthorities());
		newAuthorities.add(new SimpleGrantedAuthority("LOGINED"));

		final UsernamePasswordAuthenticationToken newToken = new UsernamePasswordAuthenticationToken(
				src.getPrincipal(),
				src.getCredentials(),
				newAuthorities);
		newToken.setDetails(src.getDetails());

		return newToken;
	}
}
