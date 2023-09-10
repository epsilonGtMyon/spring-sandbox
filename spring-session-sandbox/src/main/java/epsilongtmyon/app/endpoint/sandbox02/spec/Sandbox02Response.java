package epsilongtmyon.app.endpoint.sandbox02.spec;

import jakarta.servlet.http.HttpSession;

public class Sandbox02Response {

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

	public static Sandbox02Response fromSession(HttpSession session) {
		Sandbox02Response resp = new Sandbox02Response();
		resp.setValue01((String)session.getAttribute("value01"));
		resp.setValue02((String)session.getAttribute("value02"));
		return resp;
	}

}
