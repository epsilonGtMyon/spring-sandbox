package epsilongtmyon.app.endpoint.sandbox02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.app.endpoint.sandbox02.spec.Sandbox02Response;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/sandbox02")
@RestController
public class Sandbox02Controller {

	private final HttpSession session;

	public Sandbox02Controller(HttpSession session) {
		super();
		this.session = session;
	}

	
	
	@GetMapping("/hello1")
	public String hello1() {
		String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
		// flushModeがIMMEDIATEならこのタイミングで反映されてそう
		// ON_SAVEならレスポンスコミットの時っぽい
		session.setAttribute("value01", now);
		return "hello";
	}

	@GetMapping("/hello2")
	public String hello2() {
		String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
		session.setAttribute("value02", now);
		return "hello";
	}

	@GetMapping("/get")
	public Sandbox02Response get() {
		return Sandbox02Response.fromSession(session);
	}

}
