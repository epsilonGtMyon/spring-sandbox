package epsilongtmyon.common.db;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TableSrcDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public TableSrcDao(NamedParameterJdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public Stream<TableSrc> findAllAsStream() {
		final String sql = """
				select
				    ID
				   ,MESSAGE
				from
				   TABLE_SRC
				order by
				   ID
								""";

		final Map<String, ?> param = Collections.emptyMap();
		final RowMapper<TableSrc> rowMapper = new BeanPropertyRowMapper<>(TableSrc.class);

		return jdbcTemplate.queryForStream(sql, param, rowMapper);
	}

	public List<TableSrc> findAll() {

		final String sql = """
				select
				    ID
				   ,MESSAGE
				from
				   TABLE_SRC
				order by
				   ID
								""";

		final Map<String, ?> param = Collections.emptyMap();
		final RowMapper<TableSrc> rowMapper = new BeanPropertyRowMapper<>(TableSrc.class);

		return jdbcTemplate.query(sql, param, rowMapper);
	}
}
