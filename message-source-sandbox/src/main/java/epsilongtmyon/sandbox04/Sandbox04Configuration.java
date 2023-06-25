package epsilongtmyon.sandbox04;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@ComponentScan
@Configuration
public class Sandbox04Configuration {

	@Bean
	MessageSource messageSource() {
		return createResourceBundleMessageSource();
	}

	/**
	 * ResourceBundleMessageSourceを返す
	 * @return
	 */
	private static ResourceBundleMessageSource createResourceBundleMessageSource() {
		//取得時の挙動は ResourceBundleMessageSource#resolveCodeWithoutArguments を見ればわかるかも
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());

		// 先頭のものからメッセージを解決していく 兄弟階層のイメージ
		messageSource.addBasenames("messages", "messages2");

		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

}
