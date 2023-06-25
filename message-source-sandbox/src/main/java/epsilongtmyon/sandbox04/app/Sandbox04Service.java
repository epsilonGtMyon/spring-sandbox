package epsilongtmyon.sandbox04.app;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;

@Component
public class Sandbox04Service {

	private final MessageSource messageSource;

	public Sandbox04Service(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	public void doService() {

		// 1番目のリソースに存在する場合はそれが使われる
		String message01 = messageSource.getMessage("message01",
				new Object[] { new DefaultMessageSourceResolvable("item01") }, null);
		System.out.println(message01);

		// 1番目のリソースに存在しない場合は2番目のリソースが使われる
		String message03 = messageSource.getMessage("message03",
				new Object[] { new DefaultMessageSourceResolvable("item03") }, null);
		System.out.println(message03);
	}

}
