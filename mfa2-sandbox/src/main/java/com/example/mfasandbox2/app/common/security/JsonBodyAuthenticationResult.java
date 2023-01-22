package com.example.mfasandbox2.app.common.security;

import java.io.Serializable;

public class JsonBodyAuthenticationResult implements Serializable {

	private static final long serialVersionUID = 1L;

	public boolean authenticationResult;

	public boolean mfaRequired;

}
