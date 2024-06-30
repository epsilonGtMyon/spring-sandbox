package epsilongtmyon.app.common.security.filter;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * {@link SecurityContextSavingFilter} の設定を行うクラス。
 *  
 * @param <H>
 */
public class SecurityContextSavingFilterConfigurer<H extends HttpSecurityBuilder<H>>
		extends AbstractHttpConfigurer<SecurityContextSavingFilterConfigurer<H>, H> {

	@Override
	public void configure(H http) {
		SecurityContextRepository securityContextRepository = getBuilder()
				.getSharedObject(SecurityContextRepository.class);

		SecurityContextSavingFilter savingFilter = new SecurityContextSavingFilter(securityContextRepository);
		http.addFilterAfter(savingFilter, SecurityContextHolderFilter.class);
	}
}
