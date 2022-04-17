package app.sandbox13;

import app.sandbox13.annotation.Second12;

public class Sandbox13Hoge2 {
	private final Sandbox13 sandbox13;

	public Sandbox13Hoge2(@Second12 Sandbox13 sandbox13) {
		this.sandbox13 = sandbox13;
	}

	public void execute() {
		System.out.println(sandbox13.getName());
	}

}
