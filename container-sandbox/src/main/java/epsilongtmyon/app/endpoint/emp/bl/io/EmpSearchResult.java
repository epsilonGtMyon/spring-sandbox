package epsilongtmyon.app.endpoint.emp.bl.io;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class EmpSearchResult {

	private List<EmpSearchResultDetail> details;

	public List<EmpSearchResultDetail> getDetails() {
		return details;
	}

	public void setDetails(List<EmpSearchResultDetail> details) {
		this.details = details;
	}

	// --------------------------

	public static class EmpSearchResultDetail implements Serializable {

		private String empId;

		private String firstName;

		private String familyName;

		private Timestamp createdAt;

		private Timestamp updatedAt;

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

		public Timestamp getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}

		public Timestamp getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Timestamp updatedAt) {
			this.updatedAt = updatedAt;
		}

	}
}
