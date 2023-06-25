package epsilongtmyon.sandbox01;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.StaticMessageSource;

@ComponentScan
@Configuration
public class Sandbox01Configuration {

	@Bean
	MessageSource messageSource() {
		return createStaticMessageSource();
	}

	/**
	 * StaticMessageSourceを試す
	 * プログラムでメッセージを組み立てることができる。
	 * 
	 * @return
	 */
	private static StaticMessageSource createStaticMessageSource() {
		Locale locale = Locale.getDefault();

		Map<String, String> m = new HashMap<>();
		m.put("message01", "メッセージ1[{0}]");
		m.put("message02", "メッセージ2[{0}]");
		m.put("message03", "メッセージ3[{0}]");

		m.put("item01", "アイテム01");
		m.put("item02", "アイテム02");
		m.put("item03", "アイテム03");

		StaticMessageSource messageSource = new StaticMessageSource();
		messageSource.addMessages(m, locale);

		// これをtrueにするとメッセージが見つからなかったときに例外を返すのではなくコードを返す
		messageSource.setUseCodeAsDefaultMessage(true);

		return messageSource;
	}
}
