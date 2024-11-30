package epsilongtmyon.common.db;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.JdbcClient.MappedQuerySpec;
import org.springframework.stereotype.Repository;

@Repository
public class TableSrcDao {

	private final JdbcClient jdbcClient;

	public TableSrcDao(JdbcClient jdbcClient) {
		super();
		this.jdbcClient = jdbcClient;
	}

	public Stream<TableSrc> findAllAsStream() {
		return queryAllAsStream()
				.stream();

	}

	public List<TableSrc> findAll() {

		return queryAllAsStream()
				.list();
	}

	private MappedQuerySpec<TableSrc> queryAllAsStream() {

		final String sql = """
				select
				    ID
				   ,MESSAGE
				from
				   TABLE_SRC
				order by
				   ID
								""";
		return jdbcClient.sql(sql)
				.query(TableSrc.class);
	}
}
