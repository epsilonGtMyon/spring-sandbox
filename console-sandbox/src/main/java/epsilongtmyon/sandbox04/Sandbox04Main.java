package epsilongtmyon.sandbox04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import epsilongtmyon.common.CommonRoot;

@SpringBootApplication(scanBasePackageClasses = { CommonRoot.class, Sandbox04Main.class })
public class Sandbox04Main {

	public static void main(String[] args) {
		SpringApplication.run(Sandbox04Main.class, args);
	}
}
