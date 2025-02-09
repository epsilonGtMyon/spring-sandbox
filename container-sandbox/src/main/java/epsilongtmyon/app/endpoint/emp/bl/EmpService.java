package epsilongtmyon.app.endpoint.emp.bl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.app.common.db.dao.EmpDao;
import epsilongtmyon.app.common.db.entity.Emp;
import epsilongtmyon.app.endpoint.emp.bl.io.EmpRegisterParam;
import epsilongtmyon.app.endpoint.emp.bl.io.EmpSearchResult;
import epsilongtmyon.app.endpoint.emp.bl.io.EmpSearchResult.EmpSearchResultDetail;

@Service
@Transactional
public class EmpService {

	private final EmpDao empDao;

	public EmpService(EmpDao empDao) {
		super();
		this.empDao = empDao;
	}

	public EmpSearchResult search() {

		List<EmpSearchResultDetail> details = empDao.findAll()
				.stream()

				.map(emp -> {
					EmpSearchResultDetail d = new EmpSearchResultDetail();
					d.setEmpId(emp.getEmpId());
					d.setFirstName(emp.getFirstName());
					d.setFamilyName(emp.getFamilyName());
					d.setCreatedAt(emp.getCreatedAt());
					d.setUpdatedAt(emp.getUpdatedAt());
					return d;
				}).toList();

		EmpSearchResult result = new EmpSearchResult();
		result.setDetails(details);
		return result;

	}

	public void register(EmpRegisterParam param) {

		Timestamp now = new Timestamp(System.currentTimeMillis());
		Emp emp = empDao.findByPk(param.getEmpId()).orElse(null);
		boolean insert = emp == null;
		if (insert) {
			emp = new Emp();
			emp.setEmpId(param.getEmpId());
			emp.setCreatedAt(now);
		}

		emp.setFirstName(param.getFirstName());
		emp.setFamilyName(param.getFamilyName());
		emp.setUpdatedAt(now);

		if (insert) {
			empDao.insert(emp);
		} else {
			empDao.update(emp);
		}

	}
}
