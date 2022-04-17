package app.sandbox13;

import app.sandbox13.annotation.First12;

public class Sandbox13Hoge {
	private final Sandbox13 sandbox13;

	public Sandbox13Hoge(@First12 Sandbox13 sandbox13) {
		this.sandbox13 = sandbox13;
	}

	public void execute() {
		System.out.println(sandbox13.getName());
	}

}
