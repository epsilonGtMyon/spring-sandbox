package epsilongtmyon.app.endpoint.reqattr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import epsilongtmyon.app.endpoint.reqattr.spec.ReqAttrGetResponse;

/*
 * HttpSessionでもなく、SessionScopeでもなくRequestAttributesを使う
 * これだとgetAttributeの時にsetAttributeの対象としてマーキングが行われる。
 * 
 * なるべくSessionScopeのBeanを使ったほうがいいだろうが、既存のHttpSessionのロジックを置き換える場面などではつかえるかも
 * RequestAttributesという名前が露出してしまうが..
 */
@RestController
@RequestMapping("/reqattr")
public class ReqAttrController {

	private static final String REQ_ATTR_SESSION_KEY = ReqAttrController.class.getName() + ".SESSION";

	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("reqattr/index");
	}

	@GetMapping("/countUp")
	public ReqAttrGetResponse countUp() {

		// 取得するだけで、セッションへの書き戻しはリクエスト終了時に自動で行われる。
		final RequestAttributes reqAttr = RequestContextHolder.currentRequestAttributes();
		ReqAttrBean attrBean = (ReqAttrBean) reqAttr.getAttribute(REQ_ATTR_SESSION_KEY,
				RequestAttributes.SCOPE_SESSION);
		if (attrBean == null) {
			attrBean = new ReqAttrBean();
			reqAttr.setAttribute(REQ_ATTR_SESSION_KEY, attrBean, RequestAttributes.SCOPE_SESSION);
		}

		// ここで変更したものはセッションへ反映されている。
		attrBean.access();

		ReqAttrGetResponse resp = new ReqAttrGetResponse();
		resp.setCount(attrBean.getCount());
		resp.setAccessTimes(attrBean.getAccessTimes());
		return resp;
	}
}
