package epsilongtmyon.app.endpoint.sandbox01;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import epsilongtmyon.app.common.security.MyUserDetails;

@RestController
@RequestMapping("/sandbox01")
public class Sandbox01Controller {

	@GetMapping({ "", "/" })
	public ModelAndView index() {

		SecurityContext context = SecurityContextHolder.getContext();
		System.out.println(context);

		Authentication authentication = context.getAuthentication();
		System.out.println(authentication);
		
		Object principal = authentication.getPrincipal();
		System.out.println(principal);
		if (principal instanceof Saml2AuthenticatedPrincipal saml2Principal) {
			String name = saml2Principal.getName();
			Map<String, List<Object>> attributes = saml2Principal.getAttributes();
			List<String> sessionIndexes = saml2Principal.getSessionIndexes();

			System.out.println(name);
			System.out.println(attributes);
			System.out.println(sessionIndexes);
		} else if (principal instanceof MyUserDetails myUserDetails) {
			System.out.println(myUserDetails.getUsername());
			System.out.println(myUserDetails.getAuthorities());
		}

		return new ModelAndView("sandbox01/index");
	}
}
