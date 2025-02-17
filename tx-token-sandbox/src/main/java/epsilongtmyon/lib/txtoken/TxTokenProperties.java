package epsilongtmyon.lib.txtoken;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "myapp.tx-token")
public class TxTokenProperties {

	/** トークンをリクエストパラメータで送信するときに使用する名前 */
	private String requestParameterName = "_txToken";

	/** トークンをリクエストヘッダで送信するときに使用する名前 */
	private String requestHeaderName = "X-Tx-Token";

	/** トークンをレスポンスヘッダで送信するときに使用する名前 */
	private String responseHeaderName = "X-Tx-Token";

	private TxTokenJdbc jdbc = new TxTokenJdbc();

	// ------------------------------------------------

	public String getRequestParameterName() {
		return requestParameterName;
	}

	public void setRequestParameterName(String requestParameterName) {
		this.requestParameterName = requestParameterName;
	}

	public String getRequestHeaderName() {
		return requestHeaderName;
	}

	public void setRequestHeaderName(String requestHeaderName) {
		this.requestHeaderName = requestHeaderName;
	}

	public String getResponseHeaderName() {
		return responseHeaderName;
	}

	public void setResponseHeaderName(String responseHeaderName) {
		this.responseHeaderName = responseHeaderName;
	}

	public TxTokenJdbc getJdbc() {
		return jdbc;
	}

	public void setJdbc(TxTokenJdbc jdbc) {
		this.jdbc = jdbc;
	}

	public static class TxTokenJdbc {

		private String findTokenSql = """
				select
				   CLIENT_UNIQUE_ID
				  ,WINDOW_ID
				  ,SESSION_ID
				  ,TOKEN_VALUE
				  ,CREATED_AT
				from
				  TX_TOKEN
				where
				   CLIENT_UNIQUE_ID = :clientUniqueId
				and WINDOW_ID       = :windowId
				for update
				""";

		private String removeTokenSql = """
				delete
				from
				  TX_TOKEN
				where
				   CLIENT_UNIQUE_ID = :clientUniqueId
				and WINDOW_ID       = :windowId
				""";

		private String storeTokenSql = """
				insert into TX_TOKEN (
				   CLIENT_UNIQUE_ID
				  ,WINDOW_ID
				  ,SESSION_ID
				  ,TOKEN_VALUE
				  ,CREATED_AT
				) values (
				   :clientUniqueId
				  ,:windowId
				  ,:sessionId
				  ,:tokenValue
				  ,:createdAt
				)
				""";

		public String getFindTokenSql() {
			return findTokenSql;
		}

		public void setFindTokenSql(String findTokenSql) {
			this.findTokenSql = findTokenSql;
		}

		public String getRemoveTokenSql() {
			return removeTokenSql;
		}

		public void setRemoveTokenSql(String removeTokenSql) {
			this.removeTokenSql = removeTokenSql;
		}

		public String getStoreTokenSql() {
			return storeTokenSql;
		}

		public void setStoreTokenSql(String storeTokenSql) {
			this.storeTokenSql = storeTokenSql;
		}

	}
}
