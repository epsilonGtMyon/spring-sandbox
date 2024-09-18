package epsilongtmyon.sandbox02;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Sandbox02Main {

	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(Sandbox02Main.class);

	public static void main(String[] args) {
		args = new String[] { "--batchId=B003" };
		SpringApplication.run(Sandbox02Main.class, args);
	}

}
