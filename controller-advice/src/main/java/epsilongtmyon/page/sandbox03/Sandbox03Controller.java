package epsilongtmyon.page.sandbox03;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import epsilongtmyon.page.sandbox03.Sandbox03Hoge.Sandbox03HogeImpl1;

@Controller
@RequestMapping("sandbox03")
public class Sandbox03Controller {

	private static final String VIEW_NAME = "sandbox03/index";

	@GetMapping
	public String index() {
		return VIEW_NAME;
	}

	@GetMapping("get01")
	@ResponseBody
	public Sandbox03Response01 get01(@RequestParam("value01") Optional<String> value01) {
		System.out.println(value01);

		Sandbox03Response01 resp = new Sandbox03Response01();
		value01.ifPresent(resp::setValue01);
		return resp;
	}



	@PostMapping("post01")
	@ResponseBody
	public String post01(
			//RequestResponseBodyMethodProcessorが使われるのでRequestAdviceが使われる
			@RequestBody Sandbox03HogeImpl1 hogetImpl1) {
		System.out.println(hogetImpl1);
		return "xxx";
	}

}
