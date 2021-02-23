package epsilongtmyon.page.sandbox01;

import java.io.Serializable;

public class Sandbox01Session implements Serializable {

	private static final long serialVersionUID = 1L;

	//-----------------------
	//step01

	/** 姓 */
	private String familyName;

	/** 名 */
	private String firstName;

	//-----------------------
	//step02

	/** 趣味 */
	private String hobby;

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

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "Sandbox01Session [familyName=" + familyName + ", firstName=" + firstName + ", hobby=" + hobby + "]";
	}

}
