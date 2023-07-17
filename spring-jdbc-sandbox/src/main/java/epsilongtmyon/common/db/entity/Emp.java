package epsilongtmyon.common.db.entity;

public class Emp extends AbstractEntity {

	private String empId;

	private String firstName;

	private String familyName;

	private String bloodType;

	private String note;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", firstName=" + firstName + ", familyName=" + familyName + ", bloodType="
				+ bloodType + ", note=" + note + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
