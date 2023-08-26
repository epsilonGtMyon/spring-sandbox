package epsilongtmyon.app.runner;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import epsilongtmyon.lib.autoconfigure.config.EnvContains2AutoConfiguration.EnvContains2Hello;

@Component
public class EnvContains2Runner implements CommandLineRunner {

	private final Optional<EnvContains2Hello> envContains2Hello;

	public EnvContains2Runner(Optional<EnvContains2Hello> envContains2Hello) {
		super();
		this.envContains2Hello = envContains2Hello;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(getClass());

		envContains2Hello.ifPresent((b) -> {
			System.out.println(b.hello());
		});
	}
}
