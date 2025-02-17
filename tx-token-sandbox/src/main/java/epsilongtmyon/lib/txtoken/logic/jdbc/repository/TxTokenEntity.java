package epsilongtmyon.lib.txtoken.logic.jdbc.repository;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TxTokenEntity implements Serializable {

	/** ユニークID */
	private String clientUniqueId;

	/** ウィンドウのID */
	private String windowId;

	/** セッションID */
	private String sessionId;

	/** トークン */
	private String tokenValue;

	/** 作成日時 */
	private LocalDateTime createdAt;

	public String getClientUniqueId() {
		return clientUniqueId;
	}

	public void setClientUniqueId(String clientUniqueId) {
		this.clientUniqueId = clientUniqueId;
	}

	public String getWindowId() {
		return windowId;
	}

	public void setWindowId(String windowId) {
		this.windowId = windowId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	// ----------------

}
