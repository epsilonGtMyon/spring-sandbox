package epsilongtmyon.app.sandbox02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import epsilongtmyon.shared.UserAgent;

@Controller
@RequestMapping("sandbox02")
public class Sandbox02Controller {

	private static final String VIEW_NAME = "sandbox02/index";

	@GetMapping
	public String index() {
		return VIEW_NAME;
	}

	@GetMapping("get01")
	public String get01(Model model, UserAgent userAgent) {

		if (userAgent != null) {
			model.addAttribute(userAgent);
		}
		return VIEW_NAME;
	}

	@PostMapping("post01")
	public String post01(Model model, UserAgent userAgent) {

		if (userAgent != null) {
			model.addAttribute(userAgent);
		}
		return VIEW_NAME;
	}
}
