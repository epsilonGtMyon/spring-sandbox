package com.example.mfasandbox.app.common.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


/**
 * 多要素認証用の{@link UsernamePasswordAuthenticationToken}
 * 
 * ユーザーが入力した認証コードを追加で持たせている
 *
 */
public class MfaAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String authCode;

	public MfaAuthenticationToken(Object principal, Object credentials, String authCode) {
		super(principal, credentials);
		this.authCode = authCode;
	}

	public MfaAuthenticationToken(Object principal, Object credentials, String authCode,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		this.authCode = authCode;
	}
	
	
	public String getAuthCode() {
		return authCode;
	}
	

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		authCode = null;
	}

}
