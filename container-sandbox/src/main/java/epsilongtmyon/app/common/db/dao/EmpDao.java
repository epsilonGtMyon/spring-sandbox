package epsilongtmyon.app.common.db.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import epsilongtmyon.app.common.db.entity.Emp;

@Repository
public class EmpDao {

	private final JdbcClient client;

	public EmpDao(JdbcClient client) {
		super();
		this.client = client;
	}

	public List<Emp> findAll() {

		final String sql = """
				select
				   EMP_ID
				  ,FIRST_NAME
				  ,FAMILY_NAME
				  ,CREATED_AT
				  ,UPDATED_AT
				from
				  EMP
				order by
				  EMP_ID

								""";

		return client

				.sql(sql)

				.query(Emp.class)

				.list();

	}

	public Optional<Emp> findByPk(String empId) {
		final String sql = """
				select
				   EMP_ID
				  ,FIRST_NAME
				  ,FAMILY_NAME
				  ,CREATED_AT
				  ,UPDATED_AT
				from
				  EMP
				where
				   EMP_ID = :empId
				""";

		return client.sql(sql)

				.param("empId", empId)

				.query(Emp.class)

				.optional();
	}

	public int insert(Emp emp) {
		final String sql = """
				insert into EMP (
				   EMP_ID
				  ,FIRST_NAME
				  ,FAMILY_NAME
				  ,CREATED_AT
				  ,UPDATED_AT
				) values (
				   :empId
				  ,:firstName
				  ,:familyName
				  ,:createdAt
				  ,:updatedAt
				)
				""";

		return client.sql(sql)

				.paramSource(emp)

				.update();
	}

	public int update(Emp emp) {
		final String sql = """
				update EMP set
				   FIRST_NAME  = :firstName
				  ,FAMILY_NAME = :familyName
				  ,UPDATED_AT  = :updatedAt
				where
				   EMP_ID      = :empId
				""";

		return client.sql(sql)

				.paramSource(emp)

				.update();
	}

}
