package epsilongtmyon.shared.type;

public enum Flag {

	ON("1"),

	OFF("0"),

	;

	public final String value;

	private Flag(String value) {
		this.value = value;
	}

	public static Flag of(String text) {
		if (text == null) {
			return null;
		}

		for (var v : values()) {
			if (text.equals(v.value)) {
				return v;
			}
		}

		throw new IllegalArgumentException(text);
	}

}
