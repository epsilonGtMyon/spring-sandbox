package app.sandbox12;

public class Sandbox12Hoge {

	private final Sandbox12 sandbox12;

	public Sandbox12Hoge(Sandbox12 sandbox12) {
		this.sandbox12 = sandbox12;
	}

	public void execute() {
		String hello = sandbox12.hello();
		System.out.println(hello);
	}

}
