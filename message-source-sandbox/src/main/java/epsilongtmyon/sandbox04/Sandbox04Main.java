package epsilongtmyon.sandbox04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import epsilongtmyon.sandbox04.app.Sandbox04Service;

/**
 * {@link ResourceBundleMessageSource} を使って挙動確認
 */
public class Sandbox04Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox04Configuration.class)) {

			Sandbox04Service service = context.getBean(Sandbox04Service.class);

			service.doService();

		}
	}
}
