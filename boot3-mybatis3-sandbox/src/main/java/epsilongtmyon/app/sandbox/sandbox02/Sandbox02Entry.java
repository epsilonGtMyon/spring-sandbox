package epsilongtmyon.app.sandbox.sandbox02;

import org.springframework.stereotype.Component;

@Component
public class Sandbox02Entry {

	private final Sandbox02Service service;

	public Sandbox02Entry(Sandbox02Service service) {
		super();
		this.service = service;
	}

	public void execute() {
		service.execute();
	}
}
