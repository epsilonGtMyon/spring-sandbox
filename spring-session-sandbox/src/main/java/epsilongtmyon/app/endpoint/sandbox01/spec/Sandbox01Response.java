package epsilongtmyon.app.endpoint.sandbox01.spec;

import epsilongtmyon.app.endpoint.sandbox01.Sandbox01Session;

public class Sandbox01Response {

	private String value01;

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

	public static Sandbox01Response fromSession(Sandbox01Session session) {
		Sandbox01Response resp = new Sandbox01Response();
		resp.setValue01(session.getValue01());
		resp.setValue02(session.getValue02());
		return resp;
	}

}
