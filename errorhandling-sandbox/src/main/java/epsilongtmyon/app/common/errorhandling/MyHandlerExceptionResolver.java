package epsilongtmyon.app.common.errorhandling;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import epsilongtmyon.app.common.exception.My03Exception;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// DispatcherServlet#processHandlerExceptionから呼ばれる
@Component
public class MyHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(MyHandlerExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		log.info("resolveException: {}", request.getRequestURI());
		if (ex instanceof My03Exception subEx) {
			response.setContentType("text/plain;charset=utf-8");
			
			try {
				PrintWriter pw = response.getWriter();
				pw.println(ex.getMessage());
				ex.printStackTrace(pw);
				return new ModelAndView();
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		}

		return null;
	}

	@Override
	public int getOrder() {
		// 例外によってはHandlerExceptionResolverCompositeのDefaultHandlerExceptionResolverがハンドリングされることがあるので
		// Orderをつけてそれより手前にする。
		return -10;
	}

}
