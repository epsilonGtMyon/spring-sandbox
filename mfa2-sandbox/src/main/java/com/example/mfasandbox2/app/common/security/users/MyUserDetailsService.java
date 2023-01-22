package com.example.mfasandbox2.app.common.security.users;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

	private final Map<String, MyUserDetails> users = buildUsers();

	private static Map<String, MyUserDetails> buildUsers() {
		return List.of(
				new MyUserDetails("user01", "{noop}pass01", "000001"),
				new MyUserDetails("user02", "{noop}pass02", "000002"),
				new MyUserDetails("user03", "{noop}pass03", "")).stream()
				.collect(Collectors.toMap(x -> x.getUsername(), x -> x));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUserDetails user = users.get(username);
		if (user != null) {
			return user;
		}
		throw new UsernameNotFoundException("username");
	}

}
