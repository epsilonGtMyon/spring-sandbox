package epsilongtmyon.app.common.security.filter;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;

/**
 * {@link SecurityContextSavingFilter}で保存を行うべきかどうかの情報を保持するサポートクラス 
 */
public class SecurityContextSavingMarker {

	@Autowired
	protected HttpServletRequest request;

	public void markToSave() {
		markToSave(request);
	}

	public static void markToSave(HttpServletRequest request) {
		request.setAttribute(SecurityContextSavingFilter.SHOULD_SAVING, true);
	}

	public boolean isMakedToSave() {
		return isMakedToSave(request);
	}

	public static boolean isMakedToSave(HttpServletRequest request) {
		Boolean maked = (Boolean) request.getAttribute(SecurityContextSavingFilter.SHOULD_SAVING);
		return maked != null && maked.booleanValue();
	}

}
