package epsilongtmyon.page.sandbox01.form;

import java.io.Serializable;

public class Sandbox01Step01Form implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 姓 */
	private String familyName;

	/** 名 */
	private String firstName;

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "Sandbox01Step01Form [familyName=" + familyName + ", firstName=" + firstName + "]";
	}

}
