package epsilongtmyon.app.common.bean;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.valves.ValveBase;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * 組み込みTomcatをカスタマイズするクラス
 */
@Component
public class TomcatFactoryCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		// TomcatのValveを使ってPrincipalを埋め込む
		factory.addEngineValves(new DummyPrincipalValve());
	}

	/**
	 * ダミーのPrincipalを埋め込むValve
	 */
	public static class DummyPrincipalValve extends ValveBase {

		@Override
		public void invoke(Request request, Response response) throws IOException, ServletException {
			putDummyPrincipal(request);
			getNext().invoke(request, response);
		}

		private void putDummyPrincipal(Request request) {
			if (request.getPrincipal() != null) {
				// 既に埋め込まれているので何もしない
				return;
			}

			GenericPrincipal principal = new GenericPrincipal("dummyUser", null, List.of("dummyRole"));
			request.setUserPrincipal(principal);
		}
	}

}
