package epsilongtmyon.lib.txtoken;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import epsilongtmyon.lib.txtoken.generator.TxTokenGenerator;
import epsilongtmyon.lib.txtoken.interceptor.RequestTxTokenExtractor;
import epsilongtmyon.lib.txtoken.interceptor.TxTokenInterceptor;
import epsilongtmyon.lib.txtoken.interceptor.TxTokenInterceptorDelegate;
import epsilongtmyon.lib.txtoken.interceptor.impl.DefaultTxTokenInterceptorDelegate;
import epsilongtmyon.lib.txtoken.logic.TxTokenService;
import epsilongtmyon.lib.txtoken.logic.jdbc.JdbcTxTokenService;
import epsilongtmyon.lib.txtoken.logic.jdbc.repository.JdbcTxTokenRepository;
import epsilongtmyon.lib.window.WindowIdResolver;

@AutoConfiguration
@EnableConfigurationProperties(TxTokenProperties.class)
public class TxTokenAutoConfiguration {

	@Bean
	WebMvcConfigurer txTokenConfigurer(TxTokenInterceptorDelegate delegate) {
		return new WebMvcConfigurer() {

			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				TxTokenInterceptor interceptor = new TxTokenInterceptor(delegate);
				registry.addInterceptor(interceptor);
			}
		};
	}

	@Bean
	@ConditionalOnMissingBean(TxTokenGenerator.class)
	TxTokenGenerator txTokenGenerator() {
		return new TxTokenGenerator();
	}

	@Bean
	@ConditionalOnMissingBean(RequestTxTokenExtractor.class)
	RequestTxTokenExtractor requestTxTokenExtractor(TxTokenProperties props) {
		return new RequestTxTokenExtractor(props);
	}

	@Bean
	@ConditionalOnMissingBean(TxTokenInterceptorDelegate.class)
	DefaultTxTokenInterceptorDelegate guardDuplicateRequestInterceptorDelegate(
			TxTokenProperties props,
			RequestTxTokenExtractor tokenExtractor,
			TxTokenService txTokenService) {
		return new DefaultTxTokenInterceptorDelegate(props, tokenExtractor, txTokenService);
	}

	// ---------------

	@Configuration
	public static class JdbcTxTokenAutoConfiguration {

		@Bean
		@ConditionalOnMissingBean(TxTokenService.class)
		JdbcTxTokenService jdbcTxTokenService(
				JdbcTxTokenRepository repository,
				TxTokenGenerator tokenGenerator,
				WindowIdResolver windowIdResolver) {
			return new JdbcTxTokenService(repository, tokenGenerator, windowIdResolver);
		}

		@Bean
		@ConditionalOnMissingBean(JdbcTxTokenRepository.class)
		JdbcTxTokenRepository JdbcTxTokenRepository(
				JdbcClient jdbcClient,
				TxTokenProperties props) {
			return new JdbcTxTokenRepository(jdbcClient, props);
		}
	}
}
