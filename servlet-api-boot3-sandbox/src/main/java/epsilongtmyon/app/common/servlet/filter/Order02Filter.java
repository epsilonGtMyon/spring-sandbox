package epsilongtmyon.app.common.servlet.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Order02Filter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(Order02Filter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		logger.info("{}: begin", getClass().getSimpleName());
		filterChain.doFilter(request, response);
	}

}
