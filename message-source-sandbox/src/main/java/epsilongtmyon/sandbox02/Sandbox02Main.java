package epsilongtmyon.sandbox02;

import org.springframework.context.HierarchicalMessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import epsilongtmyon.sandbox02.app.Sandbox02Service;

/**
 * {@link HierarchicalMessageSource} を使って親子関係のメッセージ確認
 */
public class Sandbox02Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox02Configuration.class)) {

			Sandbox02Service service = context.getBean(Sandbox02Service.class);

			service.doService();

		}
	}
}
