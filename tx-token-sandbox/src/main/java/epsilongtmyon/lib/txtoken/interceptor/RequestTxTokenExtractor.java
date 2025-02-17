package epsilongtmyon.lib.txtoken.interceptor;

import epsilongtmyon.lib.txtoken.TxTokenProperties;
import jakarta.servlet.http.HttpServletRequest;

/**
 * リクエストからトランザクショントークンの抽出を行うクラス
 */
public class RequestTxTokenExtractor {

	private final TxTokenProperties props;

	public RequestTxTokenExtractor(TxTokenProperties props) {
		super();
		this.props = props;
	}

	/**
	 * トランザクショントークンを抽出します。
	 * @param request リクエスト
	 * @return トランザクショントークン
	 */
	public String extractTxToken(HttpServletRequest request) {

		// リクエストパラメータから
		String requestParamTxToken = request.getParameter(props.getRequestParameterName());
		if (requestParamTxToken != null && !requestParamTxToken.isEmpty()) {
			return requestParamTxToken;
		}

		// なければリクエストヘッダから
		String requestHeaderTxToken = request.getHeader(props.getRequestHeaderName());
		return requestHeaderTxToken;

	}
}
