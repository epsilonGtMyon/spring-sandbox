package epsilongtmyon.sandbox03;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.StaticMessageSource;

@ComponentScan
@Configuration
public class Sandbox03Configuration {

	@Bean
	MessageSource messageSource() {
		return createStaticMessageSource();
	}

	private static Properties createCommonProperties() {
		Properties p = new Properties();
		p.put("common.message01", "共通メッセージ1[{0}]");
		p.put("common.message02", "共通メッセージ2[{0}]");

		p.put("common.item01", "共通アイテム01");
		p.put("common.item02", "共通アイテム02");

		return p;
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

		Properties commonProperties = createCommonProperties();
		messageSource.setCommonMessages(commonProperties);

		// これをtrueにするとメッセージが見つからなかったときに例外を返すのではなくコードを返す
		messageSource.setUseCodeAsDefaultMessage(true);

		return messageSource;
	}

}
