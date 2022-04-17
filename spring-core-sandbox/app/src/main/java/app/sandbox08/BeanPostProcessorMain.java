package app.sandbox08;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanPostProcessorMain {


	public static void main(String[] args) {
		BeanPostProcessorMain main = new BeanPostProcessorMain();
		main.start();
	}

	private void start() {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app/sandbox08/application.xml")) {


		}
	}
}
