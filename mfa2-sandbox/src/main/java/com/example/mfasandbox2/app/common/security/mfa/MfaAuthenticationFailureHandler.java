package com.example.mfasandbox2.app.common.security.mfa;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 多要素認証失敗時のハンドラ
 */
public interface MfaAuthenticationFailureHandler {

	/**
	 * 多要素認証失敗時の処理を行います。
	 * @param request リクエスト
	 * @param response レスポンス
	 * @throws IOException
	 */
	void onMfaAuthenticationFailure(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
