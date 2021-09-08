package epsilongtmyon.pages.sandbox01.spec;

import javax.validation.constraints.NotEmpty;

public class Sandbox01Request {

	@NotEmpty
	private String value01;

	public String getValue01() {
		return value01;
	}

	public void setValue01(String value01) {
		this.value01 = value01;
	}

}
