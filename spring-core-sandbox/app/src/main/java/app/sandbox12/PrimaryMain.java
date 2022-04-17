package app.sandbox12;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PrimaryMain {

	public static void main(String[] args) {
		PrimaryMain main = new PrimaryMain();
		main.start();

	}

	private void start() {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"app/sandbox12/application.xml")) {
			Sandbox12Hoge hoge = context.getBean(Sandbox12Hoge.class);
			hoge.execute();

			Sandbox12 bean = context.getBean(Sandbox12.class);
			System.out.println(bean.hello());
		}
	}
}
