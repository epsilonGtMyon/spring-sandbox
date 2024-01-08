package epsilongtmyon.app.common.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Orderedインターフェースもしくは@Orderでハンドリング順序を設定できる。何もないときはOrdered.LOWEST_PRECEDENCEっぽい？
@Component
public class MyLogOnlyHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(MyLogOnlyHandlerExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		log.info("resolveException: {}", request.getRequestURI());
		return null;
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 1;
	}

}
