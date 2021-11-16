package epsilongtmyon.app.shared.security;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

	private final ConcurrentHashMap<String, MyUser> db = new ConcurrentHashMap<>(
			Map.of(
					"user01", MyUser.of("user01", "{noop}abc", Arrays.asList("ROLE_XXX", "ROLE_YYY")),
					"user02", new MyUser("user02", "{noop}def")));

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser myUser = db.get(username);
		if (myUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return myUser;
	}

}
