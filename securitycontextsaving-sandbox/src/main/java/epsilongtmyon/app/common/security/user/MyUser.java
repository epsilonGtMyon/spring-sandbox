package epsilongtmyon.app.common.security.user;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUser implements UserDetails {

	private final String username;
	private final String password;
	private final List<? extends GrantedAuthority> authorities;

	private int count = 0;

	public MyUser(String username, String password) {
		this(username, password, Collections.emptyList());
	}

	public MyUser(String username, String password, List<? extends GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public int countUp() {
		count++;
		return count;
	}

	//----------------

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public int getCount() {
		return count;
	}

}
