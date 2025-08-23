package epsilongtmyon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import epsilongtmyon.app.sandbox.sandbox03.Sandbox03Entry;

@SpringBootApplication
public class Boot3Mybatis3SandboxApplication {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext applicationContext = SpringApplication
				.run(Boot3Mybatis3SandboxApplication.class, args)) {
			
			
//			applicationContext.getBean(Sandbox01Entry.class).execute();
//			applicationContext.getBean(Sandbox02Entry.class).execute();
			applicationContext.getBean(Sandbox03Entry.class).execute();
			
			

		}
	}

}
