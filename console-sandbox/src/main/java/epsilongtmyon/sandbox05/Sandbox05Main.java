package epsilongtmyon.sandbox05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import epsilongtmyon.common.CommonRoot;

@SpringBootApplication(scanBasePackageClasses = { CommonRoot.class, Sandbox05Main.class })
public class Sandbox05Main {

	public static void main(String[] args) {
		SpringApplication.run(Sandbox05Main.class, args);
	}
}
