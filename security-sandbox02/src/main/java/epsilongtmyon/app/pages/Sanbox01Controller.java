package epsilongtmyon.app.pages;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("sandbox01")
@RestController
public class Sanbox01Controller {

	@GetMapping("hello")
	public String hello() {
		return "hello";
	}

	@PostMapping("hello2")
	public String hello2() {
		return "hello2";
	}
}
