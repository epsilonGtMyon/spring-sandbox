package epsilongtmyon.sandbox.sandbox01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import epsilongtmyon.sandbox.sandbox01.app.Sandbox01Config;
import epsilongtmyon.sandbox.sandbox01.app.service.Sandbox01Service;

public class Sandbox01Main {

	public static void main(String[] args) {
		Sandbox01Main main = new Sandbox01Main();
		main.start();
	}

	private void start() {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox01Config.class)) {

			Sandbox01Service service = context.getBean(Sandbox01Service.class);
			service.execute();
		}
	}
}
