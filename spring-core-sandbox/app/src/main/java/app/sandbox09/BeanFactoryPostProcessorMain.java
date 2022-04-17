package app.sandbox09;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactoryPostProcessorMain {

	public static void main(String[] args) {
		BeanFactoryPostProcessorMain main = new BeanFactoryPostProcessorMain();
		main.start();

	}

	private void start() {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"app/sandbox09/application.xml")) {
			
			
		}
	}
}
