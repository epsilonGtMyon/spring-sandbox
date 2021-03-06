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

/*
 * SessionAttributesHandlerというクラスがポイント
 *
 * RequestMappingHandlerAdapterクラスで作られる
 * (ハンドラーのBeanType つまりControllerのこと？　をキーとしたConcurrentHashMapでキャッシュされてる)
 * getModelFactoryメソッドでModelFactory を作成するときに
 * SessionAttributesHandlerも作成されてるModelFactory に渡されている
 *
 * [読込]
 * SessionAttributesHandler#retrieveAttributes
 * SessionAttributesHandler#retrieveAttribute
 * で行われる
 *
 * ModelFactory#initModelの中で などで呼び出されて
 * ModelAndViewContainer に追加される
 * これが引数の解決時にも使われる
 *
 *
 * [書込]
 * SessionAttributesHandler#storeAttributes
 * SessionAttributesHandler#cleanupAttributes
 * で行われる
 *
 * ハンドラーメソッドの実行後にModelFactory#updateModel経由で呼び出される
 * SessionStatusがcompleteになっていれば knownAttributeNames にあるものがsessionから削除され
 * そうでなければModelAndViewContainerのdefaultModel(リダイレクトじゃない方)のものから
 * @SessionAtributesで指定している セッションへの保存対象のものが 保存されるようになっている。
 *
 * knownNamesには
 * アノテーションのnamesの他にisHandlerSessionAttribute呼び出し時にも追加が行われている
 *
 * (予想)
 * セッションに保存する属性の名前は注意したほうがよさそう、
 * ・loginUserとかグローバルで使われている名前にしたら complete時に消されそう...
 * ・complete呼ぶ前に メニューから別画面に遷移したときも
 *   別画面の@SessionAttributeの属性とバッティングしたら危ない気がする..
 * DefaultSessionAttributeStore#getAttributeNameInSession をオーバーライドして工夫すればいいのかな
 *   requestのattributeにControllerのBean名を保存するようにしておいてそれをプレフィックスにするとか
 *
 *
 *
 * セッションへの保存は
 * SessionAttributeStore というインターフェース経由で行われるようになっているので
 * HttpServletRequest以外にも保存はできそう
 *
 *
 *
 *
 * ---------------------
 * @SessionAttribute
 * @RequestAttribute
 * とは別
 * これは AbstractNamedValueMethodArgumentResolver(HandlerMethodArgumentResolver)
 * 経由で引数解決するだけの属性
 */
@Controller
@RequestMapping("sandbox01")
@SessionAttributes(types = Sandbox01Session.class)
public class Sandbox01Controller {

	private static final String INDEX_VIEW = "sandbox01/index";
	private static final String STEP2_VIEW = "sandbox01/step2";
	private static final String STEP3_VIEW = "sandbox01/step3";

	@GetMapping({ "", "index" })
	public String index(Sandbox01Session session, Model model) {
		Sandbox01Step01Form form = new Sandbox01Step01Form();
		form.setFamilyName(session.getFamilyName());
		form.setFirstName(session.getFirstName());
		model.addAttribute(form);
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
	public String post03(Sandbox01Session session,
			//SessionStatusMethodArgumentResolver というハンドラが使われる
			//これはModelAndViewContainerからgetSessionStatusを実行しているだけ
			SessionStatus sessionStatus) {
		System.out.println(session);

		sessionStatus.setComplete();
		return "redirect:";
	}
}
