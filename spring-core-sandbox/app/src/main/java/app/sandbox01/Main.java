package app.sandbox01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();

		main.start();
	}

	private void start() {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"app/sandbox01/application.xml")) {

			Hoge hoge = context.getBean(Hoge.class);
			System.out.println(hoge);

			Hoge2 hoge2 = context.getBean(Hoge2.class);
			System.out.println(hoge2);
		}
	}
}
