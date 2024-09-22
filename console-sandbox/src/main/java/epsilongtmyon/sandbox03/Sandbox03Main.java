package epsilongtmyon.sandbox03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sandbox03Main {

	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(Sandbox03Main.class);

	public static void main(String[] args) {
		args = new String[] {
				"--value01=abc,def",
				"--intValue01=2",
				"--listValue01=aa01",
				"--listValue01=aa02",

				"--nested01.nestValue01=abcde",
				"--nested01.nestBoolValue01=true",

				"--nestedList[0].nestValue01=nest01",
				"--nestedList[0].nestIntValue01=1",
				"--nestedList[2].nestValue01=nest02",
				"--nestedList[2].nestIntValue01=2",

		};
		SpringApplication.run(Sandbox03Main.class, args);
	}
}
