package app.sandbox33;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import app.sandbox33.bean.Sandbox33Hoge;

@Service
public class Sandbox33Service {

	private final Validator validator;

	public Sandbox33Service(Validator validator) {
		super();
		this.validator = validator;
	}

	public void execute() {

		Sandbox33Hoge hoge = new Sandbox33Hoge();
		Set<ConstraintViolation<Sandbox33Hoge>> validate = validator.validate(hoge);
		System.out.println(validate);
	}

}
