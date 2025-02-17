package epsilongtmyon.lib.window;

import jakarta.servlet.http.HttpServletRequest;

/**
 * ウィンドウIDのリゾルバ
 */
public class WindowIdResolver {

	private String requestName = "_windowId";

	private String requestHeaderName = "X-Window-Id";

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getRequestHeaderName() {
		return requestHeaderName;
	}

	public void setRequestHeaderName(String requestHeaderName) {
		this.requestHeaderName = requestHeaderName;
	}

	/**
	 * リクエストからウィンドウIDを取得する。
	 * @param request リクエスト
	 * @return ウィンドウID
	 */
	public String fromRequest(HttpServletRequest request) {

		final String paramWindowId = request.getParameter(getRequestName());
		if (paramWindowId != null && !paramWindowId.isEmpty()) {
			return paramWindowId;
		}

		final String headerWindowId = request.getHeader(getRequestHeaderName());
		if (headerWindowId != null && !headerWindowId.isEmpty()) {
			return headerWindowId;
		}
		return "global";
	}

}
