package com.example.mfasandbox2.app.common.security.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class MyUserDetails implements MfaUserDetails {

	private final String username;
	private final String password;
	private final String mfaAuthCode;
	private final List<GrantedAuthority> authorities;

	public MyUserDetails(String username, String password, String mfaAuthCode) {
		this(username, password, mfaAuthCode, new ArrayList<GrantedAuthority>());
	}

	public MyUserDetails(String username, String password, String mfaAuthCode, List<GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.mfaAuthCode = mfaAuthCode;
		this.authorities = authorities;
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
	public void addAuthorities(String... authorities) {
		Arrays.stream(authorities).map(SimpleGrantedAuthority::new).forEach(a -> this.authorities.add(a));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
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
