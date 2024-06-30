package epsilongtmyon.app.endpoint.home;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import epsilongtmyon.app.common.security.filter.SecurityContextSavingMarker;
import epsilongtmyon.app.common.security.user.MyUser;

@RestController
@RequestMapping("/home")
public class HomeController {

	private final SecurityContextSavingMarker securityContextSavingMarker;

	public HomeController(SecurityContextSavingMarker securityContextSavingMarker) {
		this.securityContextSavingMarker = securityContextSavingMarker;
	}

	/**
	 * 画面表示
	 * @return ビュー名
	 */
	@GetMapping("")
	public ModelAndView index() {
		return new ModelAndView("home/index");
	}

	/**
	 * 永続化ありでカウントアップ
	 * @param myUser ユーザー情報
	 * @return カウント
	 */
	@PostMapping("countUpWithSaving")
	public int countUpWithSaving(@AuthenticationPrincipal MyUser myUser) {
		securityContextSavingMarker.markToSave();
		int count = myUser.countUp();
		return count;
	}

	/**
	 * 
	 * 永続化なしでカウントアップ
	 * @param myUser ユーザー情報
	 * @return カウント
	 */
	@PostMapping("countUpWithoutSaving")
	public int countUpWithoutSaving(@AuthenticationPrincipal MyUser myUser) {
		int count = myUser.countUp();
		return count;
	}

	/**
	 * 現在のカウントを返す
	 * @param myUser ユーザー情報
	 * @return カウント
	 */
	@GetMapping("currentCount")
	public int currentCount(@AuthenticationPrincipal MyUser myUser) {
		return myUser.getCount();
	}
}
