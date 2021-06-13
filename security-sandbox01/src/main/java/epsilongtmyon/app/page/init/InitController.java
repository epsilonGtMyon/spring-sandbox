package epsilongtmyon.app.page.init;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/init")
public class InitController {

	private final HttpServletRequest request;

	public InitController(HttpServletRequest request) {
		this.request = request;
	}

	@GetMapping()
	public String init() {
		//CSRFトークンを とる方法
		//  CsrfFilterを参照
		// DefaultLoginPageGeneratingFilter ここにも その方法で取得している
		// DefaultLoginPageConfigurer あたりね
		// あとはHttpSessionCsrfTokenRepository
		CsrfToken token = (CsrfToken) this.request.getAttribute(CsrfToken.class.getName());
		return token.getToken();
	}
}
