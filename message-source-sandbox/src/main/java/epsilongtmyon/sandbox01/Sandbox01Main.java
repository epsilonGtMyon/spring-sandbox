package epsilongtmyon.sandbox01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.StaticMessageSource;

import epsilongtmyon.sandbox01.app.Sandbox01Service;

/**
 * {@link StaticMessageSource}の挙動確認
 */
public class Sandbox01Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox01Configuration.class)) {

			Sandbox01Service service = context.getBean(Sandbox01Service.class);

			service.doService();

		}
	}
}
