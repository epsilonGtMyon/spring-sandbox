package epsilongtmyon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SamlSandboxApplication {
	
	//https://spring.pleiades.io/spring-security/reference/servlet/saml2/login/overview.html

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SamlSandboxApplication.class, args);
	}

}
