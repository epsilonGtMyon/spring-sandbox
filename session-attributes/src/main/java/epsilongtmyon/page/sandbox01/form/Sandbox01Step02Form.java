package epsilongtmyon.page.sandbox01.form;

import java.io.Serializable;

public class Sandbox01Step02Form implements Serializable {

	/** 趣味 */
	private String hobby;

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "Sandbox01Step02Form [hobby=" + hobby + "]";
	}

}
