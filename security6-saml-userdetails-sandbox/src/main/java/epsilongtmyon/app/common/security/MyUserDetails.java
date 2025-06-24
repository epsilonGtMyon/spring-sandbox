package epsilongtmyon.app.common.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * ユーザー情報
 */
public class MyUserDetails implements UserDetails, Serializable {

	private String username;

	private List<? extends GrantedAuthority> authorities;

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	// -------

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthorities(List<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	// -------

	public static MyUserDetails of(String username, List<? extends GrantedAuthority> authorities) {
		var user = new MyUserDetails();
		user.setUsername(username);
		user.setAuthorities(authorities);
		return user;
	}

	public static MyUserDetails of(String username) {
		return of(username, Collections.emptyList());
	}

}
