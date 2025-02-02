package epsilongtmyon.app.endpoint.sandbox01;

import java.util.Map;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import epsilongtmyon.app.common.security.endpoint.sandbox01.CsrfTokenProvider;


@RestController
@RequestMapping("/sandbox01")
public class Sandbox01Controller {

	private final CsrfTokenProvider tokenProvider;

	public Sandbox01Controller(CsrfTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("sandbox01/index");
	}

	@GetMapping("get")
	public Map<String, String> get() {
		return Map.of("value", "get");
	}

	@PostMapping("post")
	public Map<String, String> post() {
		return Map.of("value", "post");
	}

	@GetMapping("getCsrfToken")
	public Map<String, String> getCsrfToken() {
		CsrfToken csrfToken = tokenProvider.getToken();

		String headerName = csrfToken.getHeaderName();
		String token = csrfToken.getToken();
		return Map.of(
				"headerName", headerName,
				"token", token);

	}
}
