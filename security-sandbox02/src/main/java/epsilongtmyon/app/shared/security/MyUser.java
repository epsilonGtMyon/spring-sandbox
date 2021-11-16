package epsilongtmyon.app.shared.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final String username;

	private final String password;

	private final List<? extends GrantedAuthority> authorities;

	public MyUser(String username, String password) {
		this(username, password, Collections.emptyList());
	}

	public MyUser(String username, String password, List<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.authorities = Collections.unmodifiableList(authorities);
	}

	public static <T extends GrantedAuthority> MyUser of(String username, String password,
			List<String> stringAuthorities) {

		//シンプルなGrantedAuthorityの実装がSimpleGrantedAuthority。
		//単純に文字列をラップしただけ
		List<SimpleGrantedAuthority> authorities = stringAuthorities.stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return new MyUser(username, password, authorities);
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
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//ここで権限を返すようにする。
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
