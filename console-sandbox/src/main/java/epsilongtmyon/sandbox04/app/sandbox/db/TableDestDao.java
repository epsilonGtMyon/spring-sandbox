package epsilongtmyon.sandbox04.app.sandbox.db;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TableDestDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public TableDestDao(NamedParameterJdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
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

		final Map<String, ?> param = Collections.emptyMap();
		final RowMapper<TableDest> rowMapper = new BeanPropertyRowMapper<>(TableDest.class);

		return jdbcTemplate.query(sql, param, rowMapper);
	}

	public int insert(TableDest entity) {
		final String sql = """
				insert into TABLE_DEST(ID, MESSAGE) values (   :id, :message);
								""";

		final BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(entity);

		return jdbcTemplate.update(sql, param);
	}
}
