package epsilongtmyon.app.page.sandbox01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sandbox01")
public class Sandbox01Controller {

	@GetMapping
	public String index() {
		return "sandbox01/index";
	}
}
