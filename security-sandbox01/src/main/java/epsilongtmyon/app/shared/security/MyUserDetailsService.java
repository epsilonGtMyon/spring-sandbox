package epsilongtmyon.app.shared.security;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*
 * DaoAuthenticationProvider から利用される
 * コンストラクタで setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
 * が使われている
 *
 * これを使うとユーザー名/パスワード認証については任意のソースを利用できるみたい
 */
public class MyUserDetailsService implements UserDetailsService, UserDetailsPasswordService {

	//とりあえずテスト的にパスワードは平文
	private final ConcurrentHashMap<String, MyUser> db = new ConcurrentHashMap<>(
			Map.of(
					"user01", new MyUser("user01", "{noop}abc"),
					"user02", new MyUser("user02", "{noop}def")
			));

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser myUser = db.get(username);
		if (myUser == null) {
			return null;
		}
		return new User(myUser.userId, myUser.passsword, new ArrayList<>());
	}

	/*
	 * UserDetailsPasswordServiceの実装クラスがコンテナに登録されていた場合
	 * DaoAuthenticationProvider#createSuccessAuthenticationから呼ばれる
	 *
	 * パスワードのアップグレードが必要になった場合、このメソッドがコールされる
	 */
	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		MyUser myUser = db.get(user.getUsername());
		db.put(myUser.userId, new MyUser(myUser.userId, newPassword));
		return new User(myUser.userId, myUser.passsword, new ArrayList<>());
	}
}
