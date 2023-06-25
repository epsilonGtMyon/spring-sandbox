package epsilongtmyon.sandbox02;

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
public class Sandbox02Configuration {

	@Bean
	MessageSource messageSource() {
		return createHierarchicalStaticMessageSource();
	}
	
	private static StaticMessageSource createParentStaticMessageSource() {
		Locale locale = Locale.getDefault();

		Map<String, String> m = new HashMap<>();
		m.put("message01", "parentメッセージ1[{0}]");
		m.put("message02", "parentメッセージ2[{0}]");
		m.put("message03", "parentメッセージ3[{0}]");

		m.put("item01", "parentアイテム01");
		m.put("item02", "parentアイテム02");
		m.put("item03", "parentアイテム03");

		StaticMessageSource messageSource = new StaticMessageSource();
		messageSource.addMessages(m, locale);

		// これをtrueにするとメッセージが見つからなかったときに例外を返すのではなくコードを返す
		messageSource.setUseCodeAsDefaultMessage(true);

		return messageSource;
	}

	/**
	 * 親子階層ありのStaticMessageSource
	 * 
	 * 子をみてなければ親を見る
	 * @return
	 */
	private static MessageSource createHierarchicalStaticMessageSource() {
		StaticMessageSource parent = createParentStaticMessageSource();

		Locale locale = Locale.getDefault();

		Map<String, String> m = new HashMap<>();
		m.put("message01", "cメッセージ1[{0}]");

		m.put("item01", "cアイテム01");

		StaticMessageSource messageSource = new StaticMessageSource();
		messageSource.setParentMessageSource(parent);
		messageSource.addMessages(m, locale);

		// これをtrueにするとメッセージが見つからなかったときに例外を返すのではなくコードを返す
		messageSource.setUseCodeAsDefaultMessage(true);

		return messageSource;
	}
}
