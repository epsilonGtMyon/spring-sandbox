package epsilongtmyon.app.endpoint.sandbox01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sandbox01")
@RestController
public class Sandbox01Controller {

	@GetMapping("/get")
	public String get() {
		return "get";
	}

	@GetMapping("/exception")
	public String exception() {
		throw new RuntimeException("例外");
	}
}
