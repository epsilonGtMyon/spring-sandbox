package epsilongtmyon.shared.handler.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * 1.1.6. Interception
 * 1.11.5. Interceptors
 *
 * ハンドラーメソッドが決定した後の前後処理に使う
 *
 * WebMvcConfigurerのaddInterceptorsで登録できる
 * 対象パス、除外パスを指定した場合は
 * MappedInterceptorでラップされる
 * んで
 * HandlerMappingが作られるときに
 * AbstractHandlerMapping#getHandlerExecutionChain の中で、パスのマッチングでフィルタリングされている
 *
 *
 * 使うときは呼び出される順番は意識しておいたほうがよさげ
 *
 */
public class MyHandlerInterceptor implements HandlerInterceptor {

	/*
	 * DispatcherServletからHandlerExecutionChain経由で呼ばれる
	 * falseを返したら後続処理をしない
	 *
	 * 他のやつを見ると
	 * request.setAttributeで何か設定したりって使い方が多い
	 * ・ConversionServiceつっこんだり
	 * ・ResourceUrlProviderつっこんだり
	 *
	 *
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	/*
	 * DispatcherServletからHandlerExecutionChain経由で呼ばれる
	 *
	 * 例外が発生した場合は呼ばれない
	 *
	 * @ResponseBodyやResponseEntity の場合はすでにレスポンスがコミットされてるので
	 * あまり役に立たない
	 *
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//無理やりviewの名前変えてみたりw
		//modelAndView.setViewName("sandbox02/index");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	/*
	 * DispatcherServletからHandlerExecutionChain経由で呼ばれる
	 *
	 * 例外が発生しても呼ばれる
	 *
	 * クリーンアップとかに使うみたい
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}


}
