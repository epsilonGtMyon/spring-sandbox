package epsilongtmyon.app.page.sandbox02;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/sandbox02")
public class Sandbox02Controller {
	private static final Logger logger = LoggerFactory.getLogger(Sandbox02Controller.class);

	/*
	 * SecurityContextHolder.getContext()で取り出す
	 */
	@GetMapping("index01")
	public Object index01() {
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		final Authentication authentication = securityContext.getAuthentication();

		final String name = authentication.getName();
		final Object credentials = authentication.getCredentials();
		final Object details = authentication.getDetails();
		final Object principal = authentication.getPrincipal();
		final boolean authenticated = authentication.isAuthenticated();

		//これはtoken
		logger.info("authentication = {}", authentication);
		logger.info("name = {}", name);
		logger.info("credentials = {}", credentials);
		logger.info("details = {}", details);
		logger.info("principal = {}", principal);
		//trueが入ってくるが..
		logger.info("isAuthenticated = {}", authenticated);
		return authentication;
	}

	/*
	 * PrincipalMethodArgumentResolverが使われて
	 * HttpServletRequest#getUserPrincipalが使わる
	 *
	 * ログインしていないときはnull
	 */
	@GetMapping("index02")
	public Object index02(Principal principal) {
		return principal;
	}

	@GetMapping("index03")
	public Object index03(@CurrentSecurityContext() Object hoge) {
		return hoge;
	}

	/*
	 * @CurrentSecurityContextと比べるとこっちはPrincipalを
	 * AuthenticationPrincipalArgumentResolverで取り出していた
	 */
	@GetMapping("index04")
	public Object index04(@AuthenticationPrincipal Object hoge) {
		return hoge;
	}
}
