package epsilongtmyon.app.common.security.user;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserService implements UserDetailsService {

	private final ConcurrentHashMap<String, MyUser> users = new ConcurrentHashMap<>();

	public MyUserService() {
		users.put("user01", new MyUser("user01", "{noop}001"));
		users.put("user02", new MyUser("user02", "{noop}002"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser myUser = users.get(username);
		if (myUser == null) {
			throw new UsernameNotFoundException(username + " not found.");
		}

		return myUser;
	}

}
