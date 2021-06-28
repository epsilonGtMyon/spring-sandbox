package epsilongtmyon.app.page.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginPageController {

	@GetMapping
	public String init() {
		return "login/index";
	}
}
