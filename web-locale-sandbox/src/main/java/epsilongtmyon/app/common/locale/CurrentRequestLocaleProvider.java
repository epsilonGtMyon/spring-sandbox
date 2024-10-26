package epsilongtmyon.app.common.locale;

import java.util.Locale;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class CurrentRequestLocaleProvider {

	private final HttpServletRequest request;

	private final LocaleResolver localeResolver;

	public CurrentRequestLocaleProvider(HttpServletRequest request, LocaleResolver localeResolver) {
		super();
		this.request = request;
		this.localeResolver = localeResolver;
	}

	public Locale getCurrentRequestLocale() {
		return localeResolver.resolveLocale(request);
	}

}
