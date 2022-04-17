package app.sandbox06;

public interface Sandbox06IfPrototype {

	void execute();

	public static class Sandbox06IfPrototypeImpl implements Sandbox06IfPrototype {

		public Sandbox06IfPrototypeImpl() {
			System.out.println(getClass());
		}

		public void execute() {
			System.out.println(this + ": execute");
		}
	}
}
