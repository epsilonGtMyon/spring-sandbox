package epsilongtmyon.app.endpoint.sandbox;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import epsilongtmyon.app.endpoint.sandbox.spec.Sandbox01Request;
import epsilongtmyon.lib.ControllerDateHolder;

@RequestMapping("/sandbox01")
@Controller
public class Sandbox01Controller {

	private final ControllerDateHolder controllerDateHolder;

	public Sandbox01Controller(ControllerDateHolder controllerDateHolder) {
		super();
		this.controllerDateHolder = controllerDateHolder;
	}

	@GetMapping("/")
	public String index() {
		return "sandbox01/index";
	}

	@GetMapping("helloGet")
	@ResponseBody
	public String helloGet() {
		controllerDateHolder.update();
		throw new RuntimeException("例外!");
	}

	@PostMapping("helloPost")
	@ResponseBody
	public String helloPost() {
		controllerDateHolder.update();
		throw new RuntimeException("例外!");
	}

	//---

	@GetMapping("helloGetWithValidation")
	@ResponseBody
	public String helloGetWithValidation(@Validated Sandbox01Request request) {
		controllerDateHolder.update();
		return "hello";
	}

	@PostMapping("helloPostWithValidation")
	@ResponseBody
	public String helloPostWithValidation(@Validated @RequestBody Sandbox01Request request) {
		controllerDateHolder.update();
		return "hello";
	}
}
