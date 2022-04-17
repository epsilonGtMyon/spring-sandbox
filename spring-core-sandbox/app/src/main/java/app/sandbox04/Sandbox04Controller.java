package app.sandbox04;

public class Sandbox04Controller {

	private final Sandbox04Service service;

	public Sandbox04Controller(Sandbox04Service service) {
		this.service = service;
	}

	public void execute() {
		System.out.println(service);
	}
}
