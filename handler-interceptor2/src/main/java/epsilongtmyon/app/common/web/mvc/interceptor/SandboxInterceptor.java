package epsilongtmyon.app.common.web.mvc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SandboxInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(SandboxInterceptor.class);

	private final String value;

	public SandboxInterceptor(String value) {
		super();
		this.value = value;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle: {}", value);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle: {}", value);
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("afterCompletion: {}", value);
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	public String getValue() {
		return this.value;
	}

}
