package app.section03.sandbox0303.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class Sandbox0303Xxx {

	public boolean validate(String value) {
		return Objects.equals(value, "xxx");
	}
}
