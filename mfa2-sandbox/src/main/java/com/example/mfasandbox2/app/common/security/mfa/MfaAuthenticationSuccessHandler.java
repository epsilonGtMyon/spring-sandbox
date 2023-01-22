package com.example.mfasandbox2.app.common.security.mfa;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 多要素認証成功時のハンドラ
 */
public interface MfaAuthenticationSuccessHandler {

	/**
	 * 多要素認証成功時の処理を行います。
	 * @param request リクエスト
	 * @param response レスポンス
	 * @throws IOException
	 */
	void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
