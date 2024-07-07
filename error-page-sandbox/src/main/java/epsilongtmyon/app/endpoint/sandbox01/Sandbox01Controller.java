package epsilongtmyon.app.endpoint.sandbox01;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/sandbox01")
public class Sandbox01Controller {

	@GetMapping("/")
	public String hello() {
		return "sandbox01/index";
	}
	
	// ------------
	// 400

	@GetMapping("/st400")
	public String st400() {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/st404")
	public String st404() {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	// ------------
	// 500

	@GetMapping("/st500")
	public String st500() {
		throw new RuntimeException("500です");
	}

	@GetMapping("/st503")
	public String st503() {
		throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
	}
}
