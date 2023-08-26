package epsilongtmyon.app.runner;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import epsilongtmyon.lib.autoconfigure.config.BooleanFalseAutoconfiguration.BooleanFalseHello;
import epsilongtmyon.lib.autoconfigure.config.BooleanTrueAutoconfiguration.BooleanTrueHello;

@Component
public class BooleanRunner implements CommandLineRunner {

	private final Optional<BooleanTrueHello> booleanTrueHello;
	private final Optional<BooleanFalseHello> booleanFalseHello;

	public BooleanRunner(Optional<BooleanTrueHello> booleanTrueHello, Optional<BooleanFalseHello> booleanFalseHello) {
		super();
		this.booleanTrueHello = booleanTrueHello;
		this.booleanFalseHello = booleanFalseHello;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(getClass());

		booleanTrueHello.ifPresent((b) -> {
			System.out.println(b.hello());
		});

		booleanFalseHello.ifPresent((b) -> {
			System.out.println(b.hello());
		});
	}

}
