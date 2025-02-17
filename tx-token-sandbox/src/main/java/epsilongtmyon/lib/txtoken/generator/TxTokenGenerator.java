package epsilongtmyon.lib.txtoken.generator;

import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;

/**
 * トランザクショントークン生成器
 */
public class TxTokenGenerator {

	/**
	 * トークンを生成します。
	 * 
	 * @param request リクエスト
	 * @return トークン
	 */
	public String generateToken(HttpServletRequest request) {
		return UUID.randomUUID().toString();
	}
}
