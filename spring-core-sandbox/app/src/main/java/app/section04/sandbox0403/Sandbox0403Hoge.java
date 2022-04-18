package app.section04.sandbox0403;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sandbox0403Hoge {

	private final String defaultLocale;

	public Sandbox0403Hoge(
			//参照することができる。
			@Value("#{systemProperties['user.country']}") String defaultLocale) {
		super();
		this.defaultLocale = defaultLocale;
	}

	public void execute() {
		System.out.println(defaultLocale);
	}

}
