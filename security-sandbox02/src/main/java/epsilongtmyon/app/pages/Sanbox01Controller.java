package epsilongtmyon.app.pages;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("sandbox01")
@RestController
public class Sanbox01Controller {

	//こんな感じでメソッドの実行できる条件を記述できる
	//毎回書くのが面倒な場合はメタアノテーションを作ることもできる。
	@PreAuthorize("hasRole('x')")
	@GetMapping("hello")
	public String hello() {
		return "hello";
	}

	@PreAuthorize("hasRole('x')")
	@PostMapping("hello2")
	public String hello2() {
		return "hello2";
	}
}
