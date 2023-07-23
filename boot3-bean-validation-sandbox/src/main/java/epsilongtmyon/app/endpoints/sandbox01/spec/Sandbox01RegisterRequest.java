package epsilongtmyon.app.endpoints.sandbox01.spec;

import epsilongtmyon.app.common.validation.annotation.HalfAlphaNum;
import epsilongtmyon.app.common.validation.annotation.HalfNum;

public class Sandbox01RegisterRequest {

	@HalfNum
	private String value01;

	@HalfAlphaNum
	private String value02;

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

}
