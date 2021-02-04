package epsilongtmyon.page.sandbox01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import epsilongtmyon.page.sandbox01.form.Sandbox01Get01Form;

@Controller
@RequestMapping("sandbox01")
public class Sandbox01Controller {

	private static final String VIEW_NAME = "sandbox01/index";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	@ModelAttribute
	public Sandbox01Get01Form sandbox01Get01Form() {
		return new Sandbox01Get01Form();
	}

	@GetMapping
	public String index(Model model) {
		return VIEW_NAME;
	}

	@GetMapping("get01")
	public String get01(Model model, Sandbox01Get01Form sandbox01Get01Form) {

		System.out.println(sandbox01Get01Form);
		return VIEW_NAME;
	}
}
