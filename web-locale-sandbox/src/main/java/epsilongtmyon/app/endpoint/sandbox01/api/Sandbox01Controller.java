package epsilongtmyon.app.endpoint.sandbox01.api;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import epsilongtmyon.app.common.locale.CurrentRequestLocaleProvider;
import epsilongtmyon.app.endpoint.sandbox01.api.spec.Hello1Response;
import epsilongtmyon.app.endpoint.sandbox01.api.spec.Hello2Response;
import epsilongtmyon.app.endpoint.sandbox01.api.spec.Hello3Response;
import epsilongtmyon.app.endpoint.sandbox01.api.spec.SetLocaleRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sandbox01")
public class Sandbox01Controller {

	private final MessageSource messageSource;

	private final LocaleResolver localeResolver;

	private final CurrentRequestLocaleProvider currentRequestLocaleProvider;

	public Sandbox01Controller(
			MessageSource messageSource,
			LocaleResolver localeResolver,
			CurrentRequestLocaleProvider currentRequestLocaleProvider) {
		super();
		this.messageSource = messageSource;
		this.localeResolver = localeResolver;
		this.currentRequestLocaleProvider = currentRequestLocaleProvider;
	}
	//-------------------------------------

	@GetMapping()
	public ModelAndView plain() {
		ModelAndView mv = new ModelAndView("redirect:/sandbox01/");
		return mv;
	}

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("sandbox01/index");
		return mv;
	}
	
	//-------------------------------------

	@GetMapping("/hello1")
	public Hello1Response hello1() {
		final String message = messageSource.getMessage("sandbox01.hello", null, null);
		Hello1Response resp = new Hello1Response();
		resp.setMessage(message);
		return resp;
	}

	// 引数のLocaleは
	// ServletRequestMethodArgumentResolverでLocaleが解決される。
	// RequestContextUtils経由で LocaleResolverが使われる。
	@GetMapping("/hello2")
	public Hello2Response hello2(Locale locale) {
		final String message = messageSource.getMessage("sandbox01.hello", null, locale);
		Hello2Response resp = new Hello2Response();
		resp.setMessage(message);
		return resp;
	}

	@GetMapping("/hello3")
	public Hello3Response hello3() {
		// 自作のクラスからリクエストに紐づくロケールの取得
		Locale locale = currentRequestLocaleProvider.getCurrentRequestLocale();

		final String message = messageSource.getMessage("sandbox01.hello", null, locale);
		Hello3Response resp = new Hello3Response();
		resp.setMessage(message);
		return resp;
	}

	//-------------------------------------

	@PostMapping("setLocale")
	public ResponseEntity<?> setLocale(@RequestBody SetLocaleRequest req, HttpServletRequest request,
			HttpServletResponse response) {

		// ロケールの設定
		// LocaleChangeInterceptor を参考
		Locale locale = StringUtils.parseLocale(req.getLang());
		localeResolver.setLocale(request, response, locale);

		return ResponseEntity.ok().build();
	}

}
