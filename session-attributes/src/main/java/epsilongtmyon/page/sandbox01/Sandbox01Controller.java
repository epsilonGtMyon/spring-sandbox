package epsilongtmyon.page.sandbox01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import epsilongtmyon.page.sandbox01.form.Sandbox01Step01Form;
import epsilongtmyon.page.sandbox01.form.Sandbox01Step02Form;

@Controller
@RequestMapping("sandbox01")
@SessionAttributes(types = Sandbox01Session.class)
public class Sandbox01Controller {

	private static final String INDEX_VIEW = "sandbox01/index";
	private static final String STEP2_VIEW = "sandbox01/step2";
	private static final String STEP3_VIEW = "sandbox01/step3";

	@GetMapping({ "", "index" })
	public String index(Sandbox01Session session, Model model) {
		model.addAttribute(new Sandbox01Step01Form());
		return INDEX_VIEW;
	}

	@PostMapping("post01")
	public String post01(Sandbox01Session session, Model model, Sandbox01Step01Form form) {
		session.setFamilyName(form.getFamilyName());
		session.setFirstName(form.getFirstName());

		model.addAttribute(new Sandbox01Step02Form());
		return STEP2_VIEW;
	}

	@PostMapping("post02")
	public String post02(Sandbox01Session session, Sandbox01Step02Form form) {
		session.setHobby(form.getHobby());
		return STEP3_VIEW;
	}

	@PostMapping("post03")
	public String post03(Sandbox01Session session, SessionStatus sessionStatus) {
		System.out.println(session);

		sessionStatus.setComplete();
		return "redirect:";
	}
}
