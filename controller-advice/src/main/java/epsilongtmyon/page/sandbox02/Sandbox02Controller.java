package epsilongtmyon.page.sandbox02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sandbox02")
public class Sandbox02Controller {

	private static final String VIEW_NAME = "sandbox02/index";

	@GetMapping
	public String index() {
		return VIEW_NAME;
	}

	@GetMapping("get01")
	public String get01() throws Sandbox02AException {
		throw new Sandbox02AException();
	}

	@GetMapping("get02")
	public String get02() throws Sandbox02BException {
		throw new Sandbox02BException();
	}
}
