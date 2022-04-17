package app.sandbox21;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import app.sandbox21.defaults.Sandbox21DefaultConfig;
import app.sandbox21.defaults.Sandbox21Xxxx;
import app.sandbox21.foo.Sandbox21FooConfig;
import app.sandbox21.hoge.Sandbox21HogeConfig;

public class Sandbox21Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.getEnvironment().setActiveProfiles("hoge");
			context.register(Sandbox21DefaultConfig.class, Sandbox21HogeConfig.class, Sandbox21FooConfig.class);
			context.refresh();

			Sandbox21Xxxx xxxx = context.getBean(Sandbox21Xxxx.class);
			xxxx.xxx();

			Sandbox21Executor executor = context.getBean(Sandbox21Executor.class);
			System.out.println(executor.execute());

		}
	}
}
