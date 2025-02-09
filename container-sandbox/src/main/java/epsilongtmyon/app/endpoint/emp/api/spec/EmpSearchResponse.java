package epsilongtmyon.app.endpoint.emp.api.spec;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import epsilongtmyon.app.endpoint.emp.bl.io.EmpSearchResult;
import epsilongtmyon.app.endpoint.emp.bl.io.EmpSearchResult.EmpSearchResultDetail;

public class EmpSearchResponse implements Serializable {

	private List<EmpSearchResponseDetail> details;

	public List<EmpSearchResponseDetail> getDetails() {
		return details;
	}

	public void setDetails(List<EmpSearchResponseDetail> details) {
		this.details = details;
	}
	
	public static EmpSearchResponse fromResult(EmpSearchResult result) {
		EmpSearchResponse r = new EmpSearchResponse();
		
		List<EmpSearchResponseDetail> details = result.getDetails().stream().map(EmpSearchResponseDetail::fromResult).toList();
		r.setDetails(details);
		return r;
	}
	
	// -----------------------------------------

	public static class EmpSearchResponseDetail implements Serializable {

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
		
		public static EmpSearchResponseDetail fromResult(EmpSearchResultDetail resultDetail) {
			EmpSearchResponseDetail d = new EmpSearchResponseDetail();
			d.setEmpId(resultDetail.getEmpId());
			d.setFirstName(resultDetail.getFirstName());
			d.setFamilyName(resultDetail.getFamilyName());
			d.setCreatedAt(resultDetail.getCreatedAt());
			d.setUpdatedAt(resultDetail.getUpdatedAt());
			return d;
		}
	}
}
