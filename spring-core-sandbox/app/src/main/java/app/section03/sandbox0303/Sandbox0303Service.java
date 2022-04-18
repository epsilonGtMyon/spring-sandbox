package app.section03.sandbox0303;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import app.section03.sandbox0303.bean.Sandbox0303Hoge;

@Service
public class Sandbox0303Service {

	private final Validator validator;

	public Sandbox0303Service(Validator validator) {
		super();
		this.validator = validator;
	}

	public void execute() {

		Sandbox0303Hoge hoge = new Sandbox0303Hoge();
		Set<ConstraintViolation<Sandbox0303Hoge>> validate = validator.validate(hoge);
		System.out.println(validate);
	}

}
