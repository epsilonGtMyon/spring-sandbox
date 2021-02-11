package epsilongtmyon.page.sandbox03;

public interface Sandbox03Hoge {

	void doHoge();

	public static class Sandbox03HogeImpl1 implements Sandbox03Hoge {

		private String value01;

		private String value02;

		@Override
		public void doHoge() {
			System.out.println("sandbox03 hoge1");
		}

		public String getValue01() {
			return value01;
		}

		public void setValue01(String value01) {
			this.value01 = value01;
		}

		public String getValue02() {
			return value02;
		}

		public void setValue02(String value02) {
			this.value02 = value02;
		}

		@Override
		public String toString() {
			return "Sandbox03HogeImpl1 [value01=" + value01 + ", value02=" + value02 + "]";
		}

	}

	public static class Sandbox03HogeImpl2 implements Sandbox03Hoge {

		@Override
		public void doHoge() {
			System.out.println("sandbox03 hoge2");
		}

	}
}
