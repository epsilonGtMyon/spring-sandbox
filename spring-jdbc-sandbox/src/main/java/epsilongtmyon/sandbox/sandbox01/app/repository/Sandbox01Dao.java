package epsilongtmyon.sandbox.sandbox01.app.repository;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import epsilongtmyon.common.db.entity.Emp;

@Repository
public class Sandbox01Dao {

	private final JdbcTemplate jdbcTemplate;

	public Sandbox01Dao(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public Emp findById(String empId) {
		String sql = """
				select * from EMP where EMP_ID = ?
								""";
		return jdbcTemplate.queryForObject(sql, new DataClassRowMapper<Emp>(Emp.class), empId);
	}

}
