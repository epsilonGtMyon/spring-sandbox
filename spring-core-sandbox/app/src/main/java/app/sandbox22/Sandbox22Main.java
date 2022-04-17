package app.sandbox22;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

public class Sandbox22Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		ConfigurableEnvironment env = context.getEnvironment();
		MutablePropertySources propertySources = env.getPropertySources();
		propertySources.addLast(new Sandbox22PropertySource());

		String x = env.getProperty("sandbox22");
		System.out.println(x);
		String x2 = env.getProperty("sandbox222");
		System.out.println(x2);


		String k1 = env.getProperty("key1");
		System.out.println(k1);

	}
}
