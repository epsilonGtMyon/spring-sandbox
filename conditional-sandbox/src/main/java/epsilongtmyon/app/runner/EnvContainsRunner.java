package epsilongtmyon.app.runner;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import epsilongtmyon.lib.autoconfigure.config.EnvContainsAutoConfiguration.EnvContainsHello;

@Component
public class EnvContainsRunner implements CommandLineRunner {

	private final Optional<EnvContainsHello> envContainsHello;

	public EnvContainsRunner(Optional<EnvContainsHello> envContainsHello) {
		super();
		this.envContainsHello = envContainsHello;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(getClass());

		envContainsHello.ifPresent((b) -> {
			System.out.println(b.hello());
		});
	}

}
