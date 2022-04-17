package app.sandbox10;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanMain {

	public static void main(String[] args) {
		FactoryBeanMain main = new FactoryBeanMain();
		main.start();
	}

	private void start() {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"app/sandbox10/application.xml")) {

			var hoge1 = context.getBean(Sandbox10Hoge.class);
			System.out.println(hoge1);
			var hoge2 = context.getBean(Sandbox10Hoge.class);
			System.out.println(hoge2);
			var foo1 = context.getBean(Sandbox10Foo.class);
			System.out.println(foo1);
			var foo2 = context.getBean(Sandbox10Foo.class);
			System.out.println(foo2);

			System.out.println("-------------");
			var xHoge = context.getBean("sandbox10Hoge");
			System.out.println(xHoge);
			var xHogeFactory = context.getBean("&sandbox10Hoge");
			System.out.println(xHogeFactory);
		}
	}
}
