package epsilongtmyon.lib.txtoken.interceptor.impl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import epsilongtmyon.lib.txtoken.TxTokenAttribute;
import epsilongtmyon.lib.txtoken.TxTokenProperties;
import epsilongtmyon.lib.txtoken.annotation.TxToken;
import epsilongtmyon.lib.txtoken.annotation.TxToken.TxTokenAction;
import epsilongtmyon.lib.txtoken.exception.TxTokenInvalidException;
import epsilongtmyon.lib.txtoken.interceptor.RequestTxTokenExtractor;
import epsilongtmyon.lib.txtoken.interceptor.TxTokenInterceptorDelegate;
import epsilongtmyon.lib.txtoken.logic.TxTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DefaultTxTokenInterceptorDelegate implements TxTokenInterceptorDelegate {

	private static Logger logger = LoggerFactory.getLogger(DefaultTxTokenInterceptorDelegate.class);

	private final TxTokenProperties txTokenProps;

	private final RequestTxTokenExtractor tokenExtractor;

	private final TxTokenService txTokenService;

	public DefaultTxTokenInterceptorDelegate(
			TxTokenProperties txTokenProps,
			RequestTxTokenExtractor tokenExtractor,
			TxTokenService txTokenService) {
		super();
		this.txTokenProps = txTokenProps;
		this.tokenExtractor = tokenExtractor;
		this.txTokenService = txTokenService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!(handler instanceof HandlerMethod handlerMethod)) {
			return true;
		}
		logger.info("preHandle {}", request.getRequestURI());

		final String alreadyToken = (String) request.getAttribute(TxTokenAttribute.TOKEN_REQUEST_ATTRIBUTE_KEY);
		if (alreadyToken != null) {
			// 既にトークン発行済ならこのリクエストでは検証は必要ない
			logger.info("既に適用済み {}", request.getRequestURI());
			
			final String responseHeaderName = txTokenProps.getResponseHeaderName();
			if (!response.containsHeader(responseHeaderName)) {
				response.addHeader(responseHeaderName, alreadyToken);
			}
			
			return true;
		}

		final TxTokenAction action = resolveAction(handlerMethod);
		if (!action.shouldValidate()) {
			return true;
		}

		logger.info("トランザクショントークンの検証を行います。URL={}", request.getRequestURI());

		final String requestToken = tokenExtractor.extractTxToken(request);
		final String storedToken = txTokenService.findAndRemoveToken(request, handlerMethod, action);

		if (!validateToken(requestToken, storedToken)) {
			String tokenErrorMessage = "TxToken invalied. requestToken=%s, storedToken=%s".formatted(requestToken,
					storedToken);
			logger.error(tokenErrorMessage);
			throw new TxTokenInvalidException(tokenErrorMessage);
		}

		return true;
	}

	/**
	 * トークンの検証
	 * 
	 * @param requestToken
	 * @param storedToken
	 * @return
	 */
	protected boolean validateToken(String requestToken, String storedToken) {
		if (requestToken == null && storedToken == null) {
			return false;
		}
		return Objects.equals(requestToken, storedToken);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle {}", request.getRequestURI());
		publishToken(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("afterCompletion {}", request.getRequestURI());
		if (ex instanceof TxTokenInvalidException) {
			// トークン例外の時は保存処理が動くと、既存の処理とぶつかる可能性がある。
			return;
		}
		publishToken(request, response, handler);
	}

	/**
	 * トークンの発行
	 * @param request
	 * @param response
	 * @param handler
	 * @throws Exception
	 */
	protected void publishToken(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!(handler instanceof HandlerMethod handlerMethod)) {
			return;
		}

		final String responseHeaderName = txTokenProps.getResponseHeaderName();
		if (response.containsHeader(responseHeaderName)) {
			// 既にトークン発行済
			return;
		}

		final String alreadyToken = (String) request.getAttribute(TxTokenAttribute.TOKEN_REQUEST_ATTRIBUTE_KEY);
		if (alreadyToken != null) {
			// ありえるのかな？
			response.addHeader(responseHeaderName, alreadyToken);
			return;
		}

		TxTokenAction action = resolveAction(handlerMethod);
		if (!action.shouldPublish()) {
			return;
		}

		logger.info("トランザクショントークンの発行を行います。URL={}", request.getRequestURI());

		final String generatedToken = txTokenService.storeToken(request, handlerMethod, action);
		response.addHeader(responseHeaderName, generatedToken);

		// 既に発行済であるかの検証用にリクエストにもセットしておく
		request.setAttribute(TxTokenAttribute.TOKEN_REQUEST_ATTRIBUTE_KEY, generatedToken);
	}

	/**
	 * どのアクションの種類かを解決
	 * @param handlerMethod ハンドラメソッド
	 * @return アクションの種類
	 */
	protected TxTokenAction resolveAction(HandlerMethod handlerMethod) {
		TxToken ann = handlerMethod.getMethodAnnotation(TxToken.class);
		return ann == null ? TxTokenAction.IGNORE : ann.value();
	}

}
