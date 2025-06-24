package epsilongtmyon.app.common.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 適当な実装
 */
public class MyUserDetailsService implements UserDetailsService {

	private final ConcurrentHashMap<String, MyUserDetails> db = new ConcurrentHashMap<>(
			Map.of(
					"u0001@example.com", MyUserDetails.of("u0001"),
					"u0002@example.com", MyUserDetails.of("u0002"),
					"u0003@example.com", MyUserDetails.of("u0003")));

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		MyUserDetails userDetails = db.get(username);
		if (userDetails == null) {
			throw new UsernameNotFoundException("User " + username + " not found.");
		}

		return userDetails;
	}

}
