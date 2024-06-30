package epsilongtmyon.app.common.security.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * SecurityContextの保存を行うフィルター
 */
public class SecurityContextSavingFilter extends OncePerRequestFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityContextSavingFilter.class);

	public static final String SHOULD_SAVING = SecurityContextSavingFilter.class.getName() + ".SAVING";

	private SecurityContextRepository securityContextRepository;

	private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
			.getContextHolderStrategy();

	public SecurityContextSavingFilter(SecurityContextRepository securityContextRepository) {
		super();
		this.securityContextRepository = securityContextRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} finally {
			if (SecurityContextSavingMarker.isMakedToSave(request)) {
				logger.info("saving");
				SecurityContext context = securityContextHolderStrategy.getContext();
				securityContextRepository.saveContext(context, request, response);
			}
		}

	}

}
