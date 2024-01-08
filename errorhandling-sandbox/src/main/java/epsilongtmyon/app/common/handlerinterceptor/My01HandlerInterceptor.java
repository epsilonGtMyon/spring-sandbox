package epsilongtmyon.app.common.handlerinterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class My01HandlerInterceptor implements HandlerInterceptor {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(My01HandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("preHandle: {}", request.getRequestURI());
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
