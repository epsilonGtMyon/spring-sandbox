package epsilongtmyon.common.db;

import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class TableDestDao {

	private final JdbcClient jdbcClient;

	public TableDestDao(JdbcClient jdbcClient) {
		super();
		this.jdbcClient = jdbcClient;
	}

	public List<TableDest> findAll() {
		final String sql = """
				select
				    ID
				   ,MESSAGE
				from
				   TABLE_DEST
				order by
				   ID
								""";
		return jdbcClient.sql(sql)
				.query(TableDest.class)
				.list();
	}

	public int insert(TableDest entity) {
		final String sql = """
				insert into TABLE_DEST(ID, MESSAGE) values (   :id, :message);
								""";

		final BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(entity);

		return jdbcClient.sql(sql)
				.paramSource(param)
				.update();
	}
}
