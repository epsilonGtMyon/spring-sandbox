package app.sandbox17;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;

//scopedProxyでプロキシの方法を指定
@Configuration
@ComponentScan(basePackageClasses = Sandbox17Main.class, scopedProxy = ScopedProxyMode.INTERFACES)
public class Sandbox17Main {

	public static void main(String[] args) {

		Sandbox17Main main = new Sandbox17Main();
		main.start();
	}

	private void start() {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Sandbox17Main.class)) {

			Sandbox17Hoge hoge = context.getBean(Sandbox17Hoge.class);
			hoge.execute();
			hoge.execute();
		}
	}
}
