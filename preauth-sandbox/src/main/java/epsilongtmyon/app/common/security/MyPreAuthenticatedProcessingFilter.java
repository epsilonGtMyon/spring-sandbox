package epsilongtmyon.app.common.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * 事前認証済みの情報をつかって認証をするフィルター
 */
public class MyPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
	
	private static Logger logger = LoggerFactory.getLogger(MyPreAuthenticatedProcessingFilter.class);

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		String remoteUser = request.getRemoteUser();
		logger.info("remoteUser = {}", remoteUser);
		return remoteUser;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		// nullを返さないほうがいいらしい
		// 他の実装クラスはN/Aを返していたので参考に
		return "N/A";
	}

}
