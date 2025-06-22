package epsilongtmyon.app.endpoint.sandbox01;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sandbox01")
public class Sandbox01Controller {

	@GetMapping({ "", "/" })
	public ModelAndView index() {

		SecurityContext context = SecurityContextHolder.getContext();
		System.out.println(context);

		Authentication authentication = context.getAuthentication();
		if (authentication instanceof Saml2Authentication saml2Auth) {
			Object principal = saml2Auth.getPrincipal();
			if (principal instanceof Saml2AuthenticatedPrincipal saml2Principal) {
				String name = saml2Principal.getName();
				Map<String,List<Object>> attributes = saml2Principal.getAttributes();
				List<String> sessionIndexes = saml2Principal.getSessionIndexes();
				
				System.out.println(name);
				System.out.println(attributes);
				System.out.println(sessionIndexes);
			}
		}

		return new ModelAndView("sandbox01/index");
	}
}
