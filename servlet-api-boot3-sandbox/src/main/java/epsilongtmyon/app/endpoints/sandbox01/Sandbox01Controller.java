package epsilongtmyon.app.endpoints.sandbox01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sandbox01")
public class Sandbox01Controller {

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
