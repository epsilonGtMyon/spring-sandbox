package epsilongtmyon.sandbox03.app;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;

@Component
public class Sandbox03Service {

	private final MessageSource messageSource;

	public Sandbox03Service(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	public void doService() {

		// 通常メッセージから取得
		String message01 = messageSource.getMessage("message01",
				new Object[] { new DefaultMessageSourceResolvable("item01") }, null);
		System.out.println(message01);

		// 通常にはなく、共通からメッセージから取得
		String commonMessage01 = messageSource.getMessage("common.message01",
				new Object[] { new DefaultMessageSourceResolvable("common.item01") }, null);
		System.out.println(commonMessage01);
	}

}
