package com.example.mfasandbox.app.common.security.users;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;

public class MyUserDetails implements MfaUserDetails {

	private final String username;
	private final String password;
	private final String mfaAuthCode;

	public MyUserDetails(String username, String password, String mfaAuthCode) {
		this.username = username;
		this.password = password;
		this.mfaAuthCode = mfaAuthCode;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getMfaAuthCode() {
		return mfaAuthCode;
	}

	//------------

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
