package epsilongtmyon.app.common.locale;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * {@link LocaleContextHolder}の内容をログで確認するためのフィルター
 */
public class LocaleContextHolderLoggingFilter extends OncePerRequestFilter {

	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(LocaleContextHolderLoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// この段階でのLocaleはLocaleResolverによって取得されたものではなく 
		// RequestContextFilter によってServletRequest#getLocale によって取得されたロケールとなる。
		// DispatcherServlet#processRequestの中ではLocaleResolverによって取得されたロケールが改めてセットされるので
		// DispatcherServlet以降のHandlerInterceptor, Controller以降の処理はLocaleResolverによるものと同じものとなっている。


		logger.info("before {}", LocaleContextHolder.getLocale());

		filterChain.doFilter(request, response);

		// DispatcherServletを抜けるとそれ以前の状態に戻るので、
		// このタイミングでもbeforeと同じロケールが取得される。
		logger.info("after {}", LocaleContextHolder.getLocale());

	}

}
