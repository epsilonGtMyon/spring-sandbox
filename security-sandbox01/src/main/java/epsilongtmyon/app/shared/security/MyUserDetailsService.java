package epsilongtmyon.app.shared.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*
 * DaoAuthenticationProvider から利用される
 * コンストラクタで setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
 * が使われている
 *
 * これを使うとユーザー名/パスワード認証については任意のソースを利用できるみたい
 */
public class MyUserDetailsService implements UserDetailsService {

	//とりあえずテスト的にパスワードは平文
	private Map<String, MyUser> db = List.of(
			new MyUser("user01", "abc"),
			new MyUser("user02", "def")

	).stream().collect(Collectors.toUnmodifiableMap(x -> x.userId, x -> x));

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser myUser = db.get(username);
		if (myUser == null) {
			return null;
		}
		//平文にしているので{noop}をつける
		return new User(myUser.userId, "{noop}" + myUser.passsword, new ArrayList<>());
	}
}
