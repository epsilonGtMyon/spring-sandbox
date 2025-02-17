package epsilongtmyon.lib.txtoken.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * トランザクショントークン周りの処理を行う{@link HandlerInterceptor}
 */
public class TxTokenInterceptor implements HandlerInterceptor {

	private final TxTokenInterceptorDelegate delegate;

	public TxTokenInterceptor(TxTokenInterceptorDelegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return this.delegate.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		this.delegate.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		this.delegate.afterCompletion(request, response, handler, ex);
	}
}
