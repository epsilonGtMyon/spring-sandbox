package app.sandbox06;

public class Sandbox06Singleton {

	private Sandbox06Prototype sandbox06Prototype;

	private Sandbox06IfPrototype sandbox06IfPrototype;

	public Sandbox06Prototype getSandbox06Prototype() {
		return sandbox06Prototype;
	}

	public void setSandbox06Prototype(Sandbox06Prototype sandbox06Prototype) {
		this.sandbox06Prototype = sandbox06Prototype;
	}

	public Sandbox06IfPrototype getSandbox06IfPrototype() {
		return sandbox06IfPrototype;
	}

	public void setSandbox06IfPrototype(Sandbox06IfPrototype sandbox06IfPrototype) {
		this.sandbox06IfPrototype = sandbox06IfPrototype;
	}

	public void execute() {
		System.out.println("---sandbox06Prototype---");
		sandbox06Prototype.execute();
		sandbox06Prototype.execute();

		System.out.println("---sandbox06IfPrototype---");
		sandbox06IfPrototype.execute();
		sandbox06IfPrototype.execute();
	}

}
