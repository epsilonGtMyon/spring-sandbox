package app.sandbox33.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class Sandbox33Xxx {

	public boolean validate(String value) {
		return Objects.equals(value, "xxx");
	}
}
