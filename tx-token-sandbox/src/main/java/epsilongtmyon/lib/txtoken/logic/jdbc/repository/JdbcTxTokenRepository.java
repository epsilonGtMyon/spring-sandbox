package epsilongtmyon.lib.txtoken.logic.jdbc.repository;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import epsilongtmyon.lib.txtoken.TxTokenProperties;

@Repository
public class JdbcTxTokenRepository {

	private final JdbcClient jdbcClient;

	private final TxTokenProperties props;

	public JdbcTxTokenRepository(
			JdbcClient jdbcClient,
			TxTokenProperties props) {
		super();
		this.jdbcClient = jdbcClient;
		this.props = props;
	}

	public TxTokenEntity findToken(String clientUniqueId, String windowId) {

		return jdbcClient.sql(props.getJdbc().getFindTokenSql())

				.param("clientUniqueId", clientUniqueId)

				.param("windowId", windowId)

				.query(TxTokenEntity.class)

				.stream().findFirst().orElse(null);
	}

	public void removeToken(String clientUniqueId, String windowId) {

		jdbcClient.sql(props.getJdbc().getRemoveTokenSql())

				.param("clientUniqueId", clientUniqueId)

				.param("windowId", windowId)

				.update()

		;
	}

	public void storeToken(TxTokenEntity tokenEntity) {

		jdbcClient.sql(props.getJdbc().getStoreTokenSql())

				.paramSource(tokenEntity)

				.update()

		;

	}
}
