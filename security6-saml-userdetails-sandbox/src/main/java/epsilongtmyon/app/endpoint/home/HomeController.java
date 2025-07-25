package epsilongtmyon.app.endpoint.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("home/index");
	}
}
