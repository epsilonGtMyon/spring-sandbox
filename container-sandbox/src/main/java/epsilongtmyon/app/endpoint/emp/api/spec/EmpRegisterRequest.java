package epsilongtmyon.app.endpoint.emp.api.spec;

import java.io.Serializable;

import epsilongtmyon.app.endpoint.emp.bl.io.EmpRegisterParam;
import jakarta.validation.constraints.NotBlank;

public class EmpRegisterRequest implements Serializable {

	@NotBlank
	private String empId;

	private String firstName;

	private String familyName;

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

	public EmpRegisterParam toParam() {

		EmpRegisterParam param = new EmpRegisterParam();
		param.setEmpId(getEmpId());
		param.setFirstName(getFirstName());
		param.setFamilyName(getFamilyName());
		return param;

	}

}
