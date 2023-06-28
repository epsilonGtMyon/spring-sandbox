package epsilongtmyon.app.common.servlet.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//SpringBootだとFilterを@Beanや@ComponentでBean登録しておくことで ServletContextInitializerBeansで自動的にFilterとして適用される
@Component
public class OrderX01Filter extends OncePerRequestFilter implements Ordered {

	private static final Logger logger = LoggerFactory.getLogger(OrderX01Filter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		logger.info("{}: begin", getClass().getSimpleName());
		filterChain.doFilter(request, response);
	}

	@Override
	public int getOrder() {
		return 300;
	}

}
