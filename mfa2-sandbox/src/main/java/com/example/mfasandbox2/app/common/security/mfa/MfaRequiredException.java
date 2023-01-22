package com.example.mfasandbox2.app.common.security.mfa;

import org.springframework.security.core.AuthenticationException;


/**
 * 多要素認証が必要を表現する例外
 */
public class MfaRequiredException extends AuthenticationException{

	public MfaRequiredException(String msg) {
		super(msg);
	}

	public MfaRequiredException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
