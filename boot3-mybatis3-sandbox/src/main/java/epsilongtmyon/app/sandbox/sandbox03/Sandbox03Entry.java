package epsilongtmyon.app.sandbox.sandbox03;

import org.springframework.stereotype.Component;

@Component
public class Sandbox03Entry {

	private final Sandbox03Service service;

	public Sandbox03Entry(Sandbox03Service service) {
		super();
		this.service = service;
	}

	public void execute() {
		service.execute();
	}
}
