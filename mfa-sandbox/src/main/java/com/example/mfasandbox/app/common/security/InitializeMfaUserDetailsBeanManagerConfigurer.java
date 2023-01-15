package com.example.mfasandbox.app.common.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

// MfaDaoAuthenticationProvider をProviderManagerに登録するためのもの
// この方法が正しいのかは要検討

@Configuration
@Order(InitializeMfaUserDetailsBeanManagerConfigurer.DEFAULT_ORDER)
public class InitializeMfaUserDetailsBeanManagerConfigurer extends GlobalAuthenticationConfigurerAdapter {
	static final int DEFAULT_ORDER = Ordered.LOWEST_PRECEDENCE - 5000 - 1;

	private final ApplicationContext context;

	/**
	 * @param context
	 */
	InitializeMfaUserDetailsBeanManagerConfigurer(ApplicationContext context) {
		this.context = context;
	}

//	@Override
//	public void init(AuthenticationManagerBuilder auth) throws Exception {
//		auth.apply(new InitializeMfaUserDetailsManagerConfigurer());
//	}
	

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		if (auth.isConfigured()) {
			return;
		}
		UserDetailsService userDetailsService = getBeanOrNull(UserDetailsService.class);
		if (userDetailsService == null) {
			return;
		}
		PasswordEncoder passwordEncoder = getBeanOrNull(PasswordEncoder.class);
		UserDetailsPasswordService passwordManager = getBeanOrNull(UserDetailsPasswordService.class);
		MfaDaoAuthenticationProvider provider = new MfaDaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		if (passwordEncoder != null) {
			provider.setPasswordEncoder(passwordEncoder);
		}
		if (passwordManager != null) {
			provider.setUserDetailsPasswordService(passwordManager);
		}
		provider.afterPropertiesSet();
		auth.authenticationProvider(provider);
	}

	/**
	 * @return a bean of the requested class if there's just a single registered
	 * component, null otherwise.
	 */
	private <T> T getBeanOrNull(Class<T> type) {
		String[] beanNames = InitializeMfaUserDetailsBeanManagerConfigurer.this.context.getBeanNamesForType(type);
		if (beanNames.length != 1) {
			return null;
		}
		return InitializeMfaUserDetailsBeanManagerConfigurer.this.context.getBean(beanNames[0], type);
	}

//	@Configuration
//	class InitializeMfaUserDetailsManagerConfigurer extends GlobalAuthenticationConfigurerAdapter {
//
//		@Override
//		public void configure(AuthenticationManagerBuilder auth) throws Exception {
//			if (auth.isConfigured()) {
//				return;
//			}
//			UserDetailsService userDetailsService = getBeanOrNull(UserDetailsService.class);
//			if (userDetailsService == null) {
//				return;
//			}
//			PasswordEncoder passwordEncoder = getBeanOrNull(PasswordEncoder.class);
//			UserDetailsPasswordService passwordManager = getBeanOrNull(UserDetailsPasswordService.class);
//			MfaDaoAuthenticationProvider provider = new MfaDaoAuthenticationProvider();
//			provider.setUserDetailsService(userDetailsService);
//			if (passwordEncoder != null) {
//				provider.setPasswordEncoder(passwordEncoder);
//			}
//			if (passwordManager != null) {
//				provider.setUserDetailsPasswordService(passwordManager);
//			}
//			provider.afterPropertiesSet();
//			auth.authenticationProvider(provider);
//		}
//
//		/**
//		 * @return a bean of the requested class if there's just a single registered
//		 * component, null otherwise.
//		 */
//		private <T> T getBeanOrNull(Class<T> type) {
//			String[] beanNames = InitializeMfaUserDetailsBeanManagerConfigurer.this.context.getBeanNamesForType(type);
//			if (beanNames.length != 1) {
//				return null;
//			}
//			return InitializeMfaUserDetailsBeanManagerConfigurer.this.context.getBean(beanNames[0], type);
//		}
//
//	}

}
