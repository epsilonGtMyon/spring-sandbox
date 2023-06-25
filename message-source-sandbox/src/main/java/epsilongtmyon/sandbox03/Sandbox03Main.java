package epsilongtmyon.sandbox03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractMessageSource;

import epsilongtmyon.sandbox03.app.Sandbox03Service;

/**
 * {@link AbstractMessageSource#setCommonMessages(java.util.Properties)} を使って共通メッセージの挙動確認
 */
public class Sandbox03Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox03Configuration.class)) {

			Sandbox03Service service = context.getBean(Sandbox03Service.class);

			service.doService();

		}
	}
}
