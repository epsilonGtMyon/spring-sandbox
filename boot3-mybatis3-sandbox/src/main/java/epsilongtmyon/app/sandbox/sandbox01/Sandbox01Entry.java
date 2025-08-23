package epsilongtmyon.app.sandbox.sandbox01;

import org.springframework.stereotype.Component;

@Component
public class Sandbox01Entry {

	private final Sandbox01Service service;

	public Sandbox01Entry(Sandbox01Service service) {
		super();
		this.service = service;
	}

	public void execute() {
		service.execute();
	}
}
